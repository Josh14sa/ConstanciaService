package pe.com.integra.ws.core_service.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pe.com.integra.ws.core_service.domain.exception.ComunicacionException;

@ControllerAdvice
public class ExceptionAdviceConfig {

    @ExceptionHandler(ComunicacionException.InvalidCusppException.class)
    public ResponseEntity<String> handleInvalidCusppException(ComunicacionException.InvalidCusppException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
    }
}


