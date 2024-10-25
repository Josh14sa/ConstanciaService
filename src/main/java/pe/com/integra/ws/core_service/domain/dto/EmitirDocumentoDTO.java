package pe.com.integra.ws.core_service.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmitirDocumentoDTO {
    @NotBlank(message = "No es posible generar la constancia por no cumplir las condiciones para la Constancia 95.")
    private String constancia95;

    @NotBlank(message = "No es posible generar la constancia por no cumplir las condiciones para la Constancia EsSalud.")
    private String constanciaEsSalud;
}
