package es.ebde.ap01.calendar.shared.exception;

public class UnauthorizedEbdeException extends EbdeException {

    public UnauthorizedEbdeException(String errorCode) {
        super(errorCode);
    }

    public UnauthorizedEbdeException(String errorCode, String errorMessage, String level) {
        super(errorCode, errorMessage, level);
    }
}

