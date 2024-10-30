package pe.com.integra.ws.core_service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerarConstanciasDTO {

    private String cuspp;
    private String dni;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String fechaConstancia;
    private String fechaEsSalud;
    private String fechaPrimerEnvio;
    private String tipoDocumento;
    private String fechaActual;

    private Date fechaMovimiento;
    private String codigoExtracto;
    private String planillaInterna;
    private String periodoCotizacion;
    private String fechaSistema;
    private String remuneracionAsegurada;
    private String abono;
    private String cuotasAbono;
    private String tipoFondo;
    private String cargo;
    private String cuotaCargo;
    private String valorCuota;
    private String saldoCuenta;
    private String retenciones;
    private String rucEmpleador;


    public String getNombreCompletoAfiliado() {
        return getPrimerNombre().trim() + " " + getSegundoNombre().trim() + " " + getPrimerApellido().trim() + " " + getSegundoApellido().trim();
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

    public String getRealPath(String s) {
        return s;
    }
}

