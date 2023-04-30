class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String fileName = "operations.txt";
        try {
            Operation[] operations = FileUtils.readOperations(fileName);
            calculator.printResults(operations);
            FileUtils.saveToFile(operations, "results.txt");
        } catch (NullPointerException | FileReadFailureException | FileCreationFailureException |
                 FileSaveFailureException e) {
            System.err.println(e.getMessage());
        }
    }
}