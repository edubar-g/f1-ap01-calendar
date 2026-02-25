package es.ebde.ap01.calendar.shared.exception.unit_tests;

import es.ebde.ap01.calendar.shared.exception.*;
import es.ebde.ap01.calendar.shared.exception.dto.ErrorResponse;
import es.ebde.ap01.calendar.shared.exception.dto.ErrorResponse.ErrorDetail;
import es.ebde.ap01.calendar.shared.exception.handler.GlobalExceptionHandler;
import es.ebde.ap01.calendar.shared.exception.service.ErrorMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests para el sistema de excepciones personalizadas.
 */
public class ExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;
    private ErrorMessageService errorMessageService;

    @BeforeEach
    public void setUp() {
        errorMessageService = mock(ErrorMessageService.class);
        exceptionHandler = new GlobalExceptionHandler(errorMessageService);
    }

    @Test
    public void testHandleNotFoundEbdeException() {
        // Arrange
        String errorCode = "CALENDAR-T-0002";
        NotFoundEbdeException exception = new NotFoundEbdeException(errorCode);

        when(errorMessageService.getErrorCode(errorCode)).thenReturn(errorCode);
        when(errorMessageService.getErrorMessage(errorCode)).thenReturn("Reunión no encontrada");
        when(errorMessageService.getErrorDescription(errorCode)).thenReturn("La reunión solicitada no existe");
        when(errorMessageService.getErrorLevel(errorCode)).thenReturn("WARN");

        // Act
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleNotFoundEbdeException(exception, null);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getErrors().size());

        ErrorDetail error = response.getBody().getErrors().get(0);
        assertEquals(errorCode, error.getCode());
        assertEquals("Reunión no encontrada", error.getMessage());
        assertEquals("La reunión solicitada no existe", error.getDescription());
        assertEquals("WARN", error.getLevel());
    }

    @Test
    public void testHandleNoContentEbdeException() {
        // Arrange
        String errorCode = "CALENDAR-T-0001";
        NoContentEbdeException exception = new NoContentEbdeException(errorCode);

        when(errorMessageService.getErrorCode(errorCode)).thenReturn(errorCode);
        when(errorMessageService.getErrorMessage(errorCode)).thenReturn("No hay contenido disponible");
        when(errorMessageService.getErrorDescription(errorCode)).thenReturn("No se encontraron reuniones");
        when(errorMessageService.getErrorLevel(errorCode)).thenReturn("INFO");

        // Act
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleNoContentEbdeException(exception, null);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getErrors().size());
    }

    @Test
    public void testHandleBadRequestEbdeException() {
        // Arrange
        String errorCode = "CALENDAR-T-0003";
        BadRequestEbdeException exception = new BadRequestEbdeException(errorCode);

        when(errorMessageService.getErrorCode(errorCode)).thenReturn(errorCode);
        when(errorMessageService.getErrorMessage(errorCode)).thenReturn("Datos inválidos");
        when(errorMessageService.getErrorDescription(errorCode)).thenReturn("Los datos no son válidos");
        when(errorMessageService.getErrorLevel(errorCode)).thenReturn("WARN");

        // Act
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleBadRequestEbdeException(exception, null);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getErrors().size());
    }

    @Test
    public void testHandleUnauthorizedEbdeException() {
        // Arrange
        String errorCode = "CALENDAR-T-0006";
        UnauthorizedEbdeException exception = new UnauthorizedEbdeException(errorCode);

        when(errorMessageService.getErrorCode(errorCode)).thenReturn(errorCode);
        when(errorMessageService.getErrorMessage(errorCode)).thenReturn("No autorizado");
        when(errorMessageService.getErrorDescription(errorCode)).thenReturn("Necesita autenticación");
        when(errorMessageService.getErrorLevel(errorCode)).thenReturn("WARN");

        // Act
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleUnauthorizedEbdeException(exception, null);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void testHandleForbiddenEbdeException() {
        // Arrange
        String errorCode = "CALENDAR-T-0007";
        ForbiddenEbdeException exception = new ForbiddenEbdeException(errorCode);

        when(errorMessageService.getErrorCode(errorCode)).thenReturn(errorCode);
        when(errorMessageService.getErrorMessage(errorCode)).thenReturn("Acceso prohibido");
        when(errorMessageService.getErrorDescription(errorCode)).thenReturn("No tiene permisos");
        when(errorMessageService.getErrorLevel(errorCode)).thenReturn("WARN");

        // Act
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleForbiddenEbdeException(exception, null);

        // Assert
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void testHandleInternalServerErrorEbdeException() {
        // Arrange
        String errorCode = "CALENDAR-T-0005";
        InternalServerErrorEbdeException exception = new InternalServerErrorEbdeException(errorCode);

        when(errorMessageService.getErrorCode(errorCode)).thenReturn(errorCode);
        when(errorMessageService.getErrorMessage(errorCode)).thenReturn("Error interno");
        when(errorMessageService.getErrorDescription(errorCode)).thenReturn("Error inesperado");
        when(errorMessageService.getErrorLevel(errorCode)).thenReturn("ERROR");

        // Act
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleInternalServerErrorEbdeException(exception, null);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}

