package pe.com.integra.ws.core_service.infrastructure.integration.datapower.feign.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OutResultResponse {

    @JsonProperty("V_ID_CORE")
    private String idCore;

}
