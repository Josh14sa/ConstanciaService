package pe.com.integra.ws.core_service.domain.dto.request;

import lombok.Builder;
import lombok.Data;
import pe.com.integra.ws.core_service.domain.dto.ArchivoDTO;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class BodyRequestList {
    private List<ArchivoDTO> files;
    private Map<String, Object> json;
    private String codigocomunicacion;
}