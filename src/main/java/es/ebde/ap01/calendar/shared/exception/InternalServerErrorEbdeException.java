package es.ebde.ap01.calendar.shared.exception;

public class InternalServerErrorEbdeException extends EbdeException {

    public InternalServerErrorEbdeException(String errorCode) {
        super(errorCode);
    }

    public InternalServerErrorEbdeException(String errorCode, String errorMessage, String level) {
        super(errorCode, errorMessage, level);
    }
}

