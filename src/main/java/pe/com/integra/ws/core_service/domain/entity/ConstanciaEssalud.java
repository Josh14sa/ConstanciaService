package pe.com.integra.ws.core_service.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConstanciaEssalud {

    private String dni;
    private String tipo_documento;
    private String primer_nombre;
    private String segundo_nombre;
    private String primer_apeliido;
    private String segundo_apellido;
    private String fechaConstancia;
}
