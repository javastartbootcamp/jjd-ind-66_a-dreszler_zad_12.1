class Calculator {
    public void printResults(Operation[] operations) {
        checkIfOperationsArrayIsNull(operations);
        for (Operation operation : operations) {
            if (operation != null) {
                System.out.println(operation);
            }
        }
    }

    private void checkIfOperationsArrayIsNull(Operation[] operations) {
        if (operations == null) {
            throw new NullPointerException("Tablica operacji nie może być nullem");
        }
    }
}
