package es.ebde.ap01.calendar.shared.exception;

public class NotFoundEbdeException extends EbdeException {

    public NotFoundEbdeException(String errorCode) {
        super(errorCode);
    }

    public NotFoundEbdeException(String errorCode, String errorMessage, String level) {
        super(errorCode, errorMessage, level);
    }
}

