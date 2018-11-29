package exceptions;

public class InputOutOfRangeException extends InvalidInputException {
    public InputOutOfRangeException(int wrongNumber) {
        super(wrongNumber);
    }

    @Override
    public String getErrorMessege() {
        return wrongNum + " is out of the choosing range.";
    }
}
