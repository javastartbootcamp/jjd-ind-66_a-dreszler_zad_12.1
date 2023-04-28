import java.io.*;
import java.util.Scanner;

class FileUtils {
    static Operation[] readOperations(String filePath) {
        checkIfFilePathIsNull(filePath);
        Operation[] operations;
        try (
                Scanner input = new Scanner(new File(filePath))
        ) {
            int lines = countLines(filePath);
            operations = new Operation[lines];
            for (int i = 0; input.hasNextLine(); i++) {
                String[] splitOperation = input.nextLine().split(" ");
                operations[i] = fillOperation(splitOperation);
            }
        } catch (FileNotFoundException e) {
            throw new FileReadFailureException("Nie znaleziono pliku: " + filePath, e);
        }

        return operations;
    }

    private static Operation fillOperation(String[] splitOperation) {
        Operation operation = null;
        try {
            operation = new Operation(Double.parseDouble(splitOperation[0]), splitOperation[1],
                    Double.parseDouble(splitOperation[2]));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Nie udało się wczytać operacji: Dopuszczalny format operacji: x operator y");
        } catch (NumberFormatException e) {
            System.err.println("Nie udało się wczytać operacji: jako operand można wprowadzić tylko liczbę");
        } catch (IllegalArgumentException e) {
            System.err.println("Nie udało się wczytać operacji: " + e.getMessage());
        }

        return operation;
    }

    private static int countLines(String filePath) throws FileNotFoundException {
        checkIfFilePathIsNull(filePath);
        int lines = 0;
        try (
                Scanner input = new Scanner(new File(filePath))
        ) {
            while (input.hasNextLine()) {
                input.nextLine();
                lines++;
            }
        } catch (FileNotFoundException e) {
            throw new FileReadFailureException("Nie znaleziono pliku: " + filePath, e);
        }

        return lines;
    }

    static void saveToFile(Operation[] operations, String filePath) {
        checkIfOperationsArrayIsNull(operations);
        checkIfFilePathIsNull(filePath);
        try {
            createFile(filePath);
            var writer = new BufferedWriter(new FileWriter(filePath));
            for (Operation operation : operations) {
                if (operation != null) {
                    writer.write(operation.toString());
                    writer.newLine();
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new FileSaveFailureException("Zapis do pliku " + filePath + " nie powiódł się", e);
        }
    }

    private static void createFile(String fileName) {
        checkIfFilePathIsNull(fileName);
        try {
            File file = new File(fileName);

            boolean fileExists = file.exists();
            if (!fileExists) {
                fileExists = file.createNewFile();
            }

            if (fileExists) {
                System.out.println("Plik " + fileName + " istnieje lub został utworzony");
            }
        } catch (IOException e) {
            throw new FileCreationFailureException("Nie udało się utworzyć pliku " + fileName, e);
        }
    }

    private static void checkIfFilePathIsNull(String fileName) {
        if (fileName == null) {
            throw new NullPointerException("Nazwa pliku nie może być nullem");
        }
    }

    private static void checkIfOperationsArrayIsNull(Operation[] operations) {
        if (operations == null) {
            throw new NullPointerException("Tablica operacji nie może być nullem");
        }
    }
}