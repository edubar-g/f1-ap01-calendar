package es.ebde.ap01.calendar.shared.exception;

import lombok.Getter;

@Getter
public abstract class EbdeException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;
    private final String level;

    public EbdeException(String errorCode, String errorMessage, String level) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.level = level;
    }

    public EbdeException(String errorCode) {
        this(errorCode, null, null);
    }

}

