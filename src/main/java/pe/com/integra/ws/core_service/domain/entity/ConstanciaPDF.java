package pe.com.integra.ws.core_service.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConstanciaPDF {
    private String nombreape;
    private String dni;
    private String tipoDocumento;
    private String cuspp;
    private String fecha;
    private String logo;
    private String ruc;
    private String fechaConstancia;
    private String firma;
    private String gerente;
}
