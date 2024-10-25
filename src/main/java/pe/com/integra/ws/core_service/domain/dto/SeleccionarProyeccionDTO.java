package pe.com.integra.ws.core_service.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeleccionarProyeccionDTO {
    @NotBlank(message = "Afiliado inactivo.")
    private String afiliadoNoPermitido;

    @NotBlank(message = "Afiliado con información de acceso restringido.")
    private String empleadoIntegra;

    @NotBlank(message = "Documento de Identidad no se encuentra registrado.")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos.")
    private String dni;

    @NotBlank(message = "CUSPP no existe.")
    @Size(min = 12, max = 12, message = "El CUSPP debe tener 12 caracteres.")
    private String cuspp;

    @NotBlank(message = "Documento de identidad corresponde a más de un afiliado, mejor ingrese el CUSPP.")
    private String dniVariosCuspp;

    @NotBlank(message = "El documento de identidad y el CUSPP no tienen relación.")
    private String conflictoDniCuspp;

    @NotBlank(message = "No se puede mostrar información porque el afiliado es mayor de 65 años.")
    private String afiliadoMayor65;

    @NotBlank(message = "La edad de jubilación debe ser menor o igual a 65 años.")
    private String afiliadoMayor65PPO;
}
