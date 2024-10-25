package pe.com.integra.ws.core_service.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArchivoDTO {
    private byte[] file;
    private String filename;
}

