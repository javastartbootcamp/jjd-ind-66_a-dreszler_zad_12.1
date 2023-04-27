class Operation {
    private double a;
    private String operator;
    private double b;
    private static final String ADD = "+";
    private static final String SUBTRACT = "-";
    private static final String MULTIPLY = "*";
    private static final String DIVIDE = "/";

    Operation(double a, String operator, double b) {
        this.a = a;
        this.operator = operator;
        this.b = b;
    }

    private double getResult() {
        return switch (operator) {
            case ADD -> a + b;
            case SUBTRACT -> a - b;
            case MULTIPLY -> a * b;
            case DIVIDE -> {
                if (b == 0) {
                    throw new IllegalArgumentException("Nie można dzielić przez 0");
                }
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Użyto złego operatora. Dopuszczalne operatory: +, -, *, /");
        };
    }

    @Override
    public String toString() {
        double result = -1;
        try {
            result = getResult();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        String stringA = a % 1 == 0 ? String.valueOf((int) a) : String.valueOf(a);
        String stringB = b % 1 == 0 ? String.valueOf((int) b) : String.valueOf(b);
        String resultAsString = result % 1 == 0 ? String.valueOf((int) result) : String.valueOf(result);

        return String.format("%s %s %s = %s", stringA, operator, stringB, resultAsString);
    }
}