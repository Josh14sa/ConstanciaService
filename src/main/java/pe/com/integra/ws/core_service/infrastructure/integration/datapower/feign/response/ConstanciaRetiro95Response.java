package pe.com.integra.ws.core_service.infrastructure.integration.datapower.feign.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstanciaRetiro95Response {

    @JsonProperty("DNI")
    private String dni;

    @JsonProperty("TIPO_DOCUMENTO")
    private String tipoDocumento;

    @JsonProperty("PRIMER_APELLIDO")
    private String primerApellido;

    @JsonProperty("SEGUNDO_APELLIDO")
    private String segundoApellido;

    @JsonProperty("PRIMER_NOMBRE")
    private String primerNombre;

    @JsonProperty("SEGUNDO_NOMBRE")
    private String segundoNombre;

    @JsonProperty("FECHA_CONSTANCIA")
    private String fechaConstancia;
}