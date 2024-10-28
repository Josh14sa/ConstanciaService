package pe.com.integra.ws.core_service.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConstanciaRequest {
    private String primerNombre;
    private String segundoNombre;
    private String dni;
    private String tipoDocumento;
    private String cuspp;
    private String primerApellidos;
    private String segundoApellido;
    private String fechaConstancia;

}
