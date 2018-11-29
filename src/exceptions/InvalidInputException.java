package exceptions;

public class InvalidInputException extends RuntimeException {
    protected int wrongNum;
    protected String wrongName;

    public InvalidInputException(int wrongNumber) {
        this.wrongNum = wrongNumber;
    }

    public InvalidInputException(String wrongString) {
        this.wrongName = wrongString;
    }

    public String getErrorMessege() {
        return "Sorry I cannot recognise your input";
    }
}
