package es.ebde.ap01.calendar.shared.exception.util;

import es.ebde.ap01.calendar.shared.exception.*;

public class ExceptionUtils {

    private ExceptionUtils() {
    }

    /**
     * Lanza una excepción NoContentEbdeException.
     *
     * @param errorCode código del error (ej: CALENDAR-T-0001)
     */
    public static void throwNoContent(String errorCode) {
        throw new NoContentEbdeException(errorCode);
    }

    /**
     * Lanza una excepción NotFoundEbdeException.
     *
     * @param errorCode código del error (ej: CALENDAR-T-0002)
     */
    public static void throwNotFound(String errorCode) {
        throw new NotFoundEbdeException(errorCode);
    }

    /**
     * Lanza una excepción BadRequestEbdeException.
     *
     * @param errorCode código del error (ej: CALENDAR-T-0003)
     */
    public static void throwBadRequest(String errorCode) {
        throw new BadRequestEbdeException(errorCode);
    }

    /**
     * Lanza una excepción UnauthorizedEbdeException.
     *
     * @param errorCode código del error (ej: CALENDAR-T-0006)
     */
    public static void throwUnauthorized(String errorCode) {
        throw new UnauthorizedEbdeException(errorCode);
    }

    /**
     * Lanza una excepción ForbiddenEbdeException.
     *
     * @param errorCode código del error (ej: CALENDAR-T-0007)
     */
    public static void throwForbidden(String errorCode) {
        throw new ForbiddenEbdeException(errorCode);
    }

    /**
     * Lanza una excepción InternalServerErrorEbdeException.
     *
     * @param errorCode código del error (ej: CALENDAR-T-0005)
     */
    public static void throwInternalServerError(String errorCode) {
        throw new InternalServerErrorEbdeException(errorCode);
    }
}

