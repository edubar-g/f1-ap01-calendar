package es.ebde.ap01.calendar.shared.exception;

public class ForbiddenEbdeException extends EbdeException {

    public ForbiddenEbdeException(String errorCode) {
        super(errorCode);
    }

    public ForbiddenEbdeException(String errorCode, String errorMessage, String level) {
        super(errorCode, errorMessage, level);
    }
}

