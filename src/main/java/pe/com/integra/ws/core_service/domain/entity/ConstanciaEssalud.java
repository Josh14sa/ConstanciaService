package pe.com.integra.ws.core_service.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConstanciaEssalud {

    private String dni;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellidos;
    private String segundoApellido;
    private String fechaConstancia;
}
