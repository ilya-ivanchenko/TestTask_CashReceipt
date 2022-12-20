package by.clevertec.ivanchenko.util;

public class OutputFileNotFoundException extends RuntimeException {

    public OutputFileNotFoundException(String message, Exception exp) {
        super(message,exp);
    }
}
