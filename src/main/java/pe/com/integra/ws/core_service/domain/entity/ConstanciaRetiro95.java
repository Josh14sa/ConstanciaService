package pe.com.integra.ws.core_service.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConstanciaRetiro95 {

    private String dni;
    private String tipo_documento;
    private String primer_nombre;
    private String segundo_nombre;
    private String primer_apeliido;
    private String segundo_apellido;
    private String fecha_constancia;

}
