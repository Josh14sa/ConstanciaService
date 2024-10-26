package pe.com.integra.ws.core_service.business.port;

import pe.com.integra.ws.core_service.domain.dto.Constancia95DTO;
import pe.com.integra.ws.core_service.domain.entity.Constancia95;
import pe.com.integra.ws.core_service.domain.entity.ConstanciaEssalud;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;

public interface ConstanciaEsSaludBusiness {

    public void generarArchivoEssalud(String cuspp, HttpServletResponse response);
    public ArrayList<Constancia95DTO> obtenerConstanciaEsSalud(Map<String, Object> parametros) throws Exception;
    ArrayList<ConstanciaEssalud> obtenerConstanciaEsSalud(String cuspp);
}
