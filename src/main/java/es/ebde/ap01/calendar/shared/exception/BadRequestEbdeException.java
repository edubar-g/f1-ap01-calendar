package es.ebde.ap01.calendar.shared.exception;

public class BadRequestEbdeException extends EbdeException {

    public BadRequestEbdeException(String errorCode) {
        super(errorCode);
    }

    public BadRequestEbdeException(String errorCode, String errorMessage, String level) {
        super(errorCode, errorMessage, level);
    }
}

