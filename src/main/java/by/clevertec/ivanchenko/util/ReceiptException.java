package by.clevertec.ivanchenko.util;

public class ReceiptException extends RuntimeException {

    public ReceiptException(String message, Exception exp) {
        super(message, exp);
    }

    public ReceiptException(String message) {
        super(message);
    }

    public ReceiptException() {

    }
}
