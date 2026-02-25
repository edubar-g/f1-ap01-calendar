package es.ebde.ap01.calendar.shared.exception.handler;

import es.ebde.ap01.calendar.shared.exception.*;
import es.ebde.ap01.calendar.shared.exception.dto.ErrorResponse;
import es.ebde.ap01.calendar.shared.exception.dto.ErrorResponse.ErrorDetail;
import es.ebde.ap01.calendar.shared.exception.service.ErrorMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final ErrorMessageService errorMessageService;

    public GlobalExceptionHandler(ErrorMessageService errorMessageService) {
        this.errorMessageService = errorMessageService;
    }

    @ExceptionHandler(NoContentEbdeException.class)
    public ResponseEntity<ErrorResponse> handleNoContentEbdeException(
            NoContentEbdeException ex, WebRequest request) {
        logger.info("NoContentEbdeException capturada: {}", ex.getErrorCode());

        ErrorDetail error = buildErrorDetail(ex.getErrorCode());
        ErrorResponse response = ErrorResponse.of(error);

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NotFoundEbdeException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundEbdeException(
            NotFoundEbdeException ex, WebRequest request) {
        logger.warn("NotFoundEbdeException capturada: {}", ex.getErrorCode());

        ErrorDetail error = buildErrorDetail(ex.getErrorCode());
        ErrorResponse response = ErrorResponse.of(error);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestEbdeException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestEbdeException(
            BadRequestEbdeException ex, WebRequest request) {
        logger.warn("BadRequestEbdeException capturada: {}", ex.getErrorCode());

        ErrorDetail error = buildErrorDetail(ex.getErrorCode());
        ErrorResponse response = ErrorResponse.of(error);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedEbdeException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedEbdeException(
            UnauthorizedEbdeException ex, WebRequest request) {
        logger.warn("UnauthorizedEbdeException capturada: {}", ex.getErrorCode());

        ErrorDetail error = buildErrorDetail(ex.getErrorCode());
        ErrorResponse response = ErrorResponse.of(error);

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenEbdeException.class)
    public ResponseEntity<ErrorResponse> handleForbiddenEbdeException(
            ForbiddenEbdeException ex, WebRequest request) {
        logger.warn("ForbiddenEbdeException capturada: {}", ex.getErrorCode());

        ErrorDetail error = buildErrorDetail(ex.getErrorCode());
        ErrorResponse response = ErrorResponse.of(error);

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InternalServerErrorEbdeException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorEbdeException(
            InternalServerErrorEbdeException ex, WebRequest request) {
        logger.error("InternalServerErrorEbdeException capturada: {}", ex.getErrorCode(), ex);

        ErrorDetail error = buildErrorDetail(ex.getErrorCode());
        ErrorResponse response = ErrorResponse.of(error);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EbdeException.class)
    public ResponseEntity<ErrorResponse> handleEbdeException(
            EbdeException ex, WebRequest request) {
        logger.error("EbdeException capturada: {}", ex.getErrorCode(), ex);

        ErrorDetail error = buildErrorDetail(ex.getErrorCode());
        ErrorResponse response = ErrorResponse.of(error);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        logger.error("Excepción no controlada capturada", ex);

        ErrorDetail error = new ErrorDetail(
                "INTERNAL-ERROR",
                "Error interno del servidor",
                "Se ha producido un error inesperado",
                "ERROR"
        );
        ErrorResponse response = ErrorResponse.of(error);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorDetail buildErrorDetail(String errorCode) {
        String code = errorMessageService.getErrorCode(errorCode);
        String message = errorMessageService.getErrorMessage(errorCode);
        String description = errorMessageService.getErrorDescription(errorCode);
        String level = errorMessageService.getErrorLevel(errorCode);

        return new ErrorDetail(code, message, description, level);
    }
}

