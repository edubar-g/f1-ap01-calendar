package es.ebde.ap01.calendar.shared.exception.service;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Service
public class ErrorMessageService {

    private final ResourceBundleMessageSource messageSource;

    public ErrorMessageService(ResourceBundleMessageSource errorMessageSource) {
        this.messageSource = errorMessageSource;
    }

    public String getErrorCode(String errorKey) {
        try {
            return messageSource.getMessage(errorKey + ".code", null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return errorKey;
        }
    }

    public String getErrorMessage(String errorKey) {
        try {
            return messageSource.getMessage(errorKey + ".message", null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return "Error desconocido";
        }
    }

    public String getErrorDescription(String errorKey) {
        try {
            return messageSource.getMessage(errorKey + ".description", null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return "";
        }
    }

    public String getErrorLevel(String errorKey) {
        try {
            return messageSource.getMessage(errorKey + ".level", null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return "ERROR";
        }
    }
}


