package pe.com.integra.ws.core_service.infrastructure.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Procedimientos {

    PSCLI334("PSCLI334(?)", "obtenerAfiRetiro95"),
    //PAFIL103("PAFIL103(?)", "actualizarReintentosGrupo"),
    PSCLI335("PSCLI335(?)", "obtenerAfiRetiroEssalud");
    //PAFIL102("PAFIL102(?,?,?,?)", "actualizarEstadoCarta");

    private final String value ;
    private final String method;


}