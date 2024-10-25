package pe.com.integra.ws.core_service.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BodyRequest {
    private byte[] file;
    private String filename;
    private Map<String, Object> json;
    private String codigoconstancia;
}