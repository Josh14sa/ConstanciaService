package pe.com.integra.ws.core_service.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SuraException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private int code;
    private String message;
    private Object success;


}
