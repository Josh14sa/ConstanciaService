package pe.com.integra.ws.core_service.domain.dto;

import java.util.Calendar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Constancia95DTO {
    private String cuspp;
    private String dni;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String fechaConstancia;
    private String tipoDocumento;

    public String getNombreCompletoAfiliado() {
        return getPrimerNombre().trim() + " " + getSegundoNombre().trim() + " " + getPrimerApellido().trim() + " " + getSegundoApellido().trim();
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public static int getAnio() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    public static int getDay() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static String obtenerMes() {
        Calendar cal = Calendar.getInstance();
        int mes = cal.get(Calendar.MONTH);
        String[] monthNames = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return monthNames[mes];
    }

    public String getFechaActual() {
        return "Lima, " + getDay() + " de " + obtenerMes() + " de " + getAnio();
    }
}
