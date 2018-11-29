package exceptions;

public class NegativeNumException extends InvalidInputException {
    public NegativeNumException(int wrongNumber) {
        super(wrongNumber);
    }

    @Override
    public String getErrorMessege() {
        return "Cannot enter negative number" + wrongNum;
    }
}
