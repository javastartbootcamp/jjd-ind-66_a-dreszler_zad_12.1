import java.io.IOException;
import java.util.zip.DataFormatException;

class Calculator {
    public void printResults(String operationsFile) {
        try {
            System.out.println(calculateAndReturnResults(operationsFile));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void saveResults(String operationsFile) {
        try {
            FileUtils.createFile("results.txt");
            FileUtils.writeToFile("results.txt", calculateAndReturnResults(operationsFile));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private String calculateAndReturnResults(String operationsFile) throws Exception {
        Operation[] operations = null;
        try {
            operations = readOperations(operationsFile);
        } catch (DataFormatException e) {
            throw new DataFormatException(e.getMessage());
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }

        String results = "";
        boolean first = true;
        for (Operation operation : operations) {
            if (!first) {
                results += "\n";
            } else {
                first = false;
            }
            results += operation;
        }

        return results;
    }

    private Operation[] readOperations(String operationsFile) throws Exception {
        Operation[] operationArray = new Operation[FileUtils.countLines(operationsFile)];
        String[] operations = null;
        try {
            operations = parseOperations(operationsFile);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }

        for (int i = 0; i < operations.length; i++) {
            String[] operation = operations[i].split(" ");
            try {
                operationArray[i] = new Operation(Double.parseDouble(operation[0]), operation[1],
                        Double.parseDouble(operation[2]));
            } catch (IndexOutOfBoundsException e) {
                throw new DataFormatException("Nieprawidłowy zapis działań.");
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Do działań można wprowadzać tylko liczby!");
            }
        }
        return operationArray;
    }

    public String[] parseOperations(String operationsFile) throws IOException {
        String operationsFileString = null;
        try {
            operationsFileString = FileUtils.readFile(operationsFile);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
        return operationsFileString.strip().split("\n");
    }
}
