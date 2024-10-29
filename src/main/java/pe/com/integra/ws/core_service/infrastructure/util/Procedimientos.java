package pe.com.integra.ws.core_service.infrastructure.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Procedimientos {

    PSCLI334("PSCLI334(?)", "obtenerAfiRetiro95"),
    PSCLI335("PSCLI335(?)", "obtenerAfiRetiroEssalud");

    private final String value ;
    private final String method;


}