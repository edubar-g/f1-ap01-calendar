package es.ebde.ap01.calendar.shared.exception;

public class NoContentEbdeException extends EbdeException {

    public NoContentEbdeException(String errorCode) {
        super(errorCode);
    }

    public NoContentEbdeException(String errorCode, String errorMessage, String level) {
        super(errorCode, errorMessage, level);
    }
}

