package pe.com.integra.ws.core_service.domain.exception;

public final class ComunicacionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ComunicacionException(final String message) {
        super(message);
    }

    public ComunicacionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static class InvalidCusppException extends RuntimeException {
        public InvalidCusppException(String message) {
            super(message);
        }
    }

}