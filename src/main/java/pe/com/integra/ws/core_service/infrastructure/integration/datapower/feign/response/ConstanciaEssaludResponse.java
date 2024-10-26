package pe.com.integra.ws.core_service.infrastructure.integration.datapower.feign.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstanciaEssaludResponse {

    @JsonProperty("dni")
    private String dni;

    @JsonProperty("tipo_documento")
    private String tipo_documento;

    @JsonProperty("primer_apeliido")
    private String primer_apeliido;

    @JsonProperty("segundo_apellido")
    private String segundo_apellido;

    @JsonProperty("primer_nombre")
    private String primer_nombre;

    @JsonProperty("segundo_nombre")
    private String segundo_nombre;

    @JsonProperty("fecha_primer_envio")
    private String fecha_primer_envio;
}

