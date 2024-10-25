package pe.com.integra.ws.core_service.infrastructure.integration.datapower.feign.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstanciaEssaludResponse {

    @JsonProperty("dni")
    private String dni;

    @JsonProperty("tipoDocumento")
    private String tipoDocumento;

    @JsonProperty("primerApellido")
    private String primerApellido;

    @JsonProperty("segundoApellido")
    private String segundoApellido;

    @JsonProperty("primerNombre")
    private String primerNombre;

    @JsonProperty("segundoNombre")
    private String segundoNombre;

    @JsonProperty("fechaConstancia")
    private String fechaConstancia;
}

