package pe.com.integra.ws.core_service.business.port;

import pe.com.integra.ws.core_service.domain.entity.ConstanciaEssalud;

import java.util.ArrayList;

public interface ConstanciaEsSaludBusiness {

    ArrayList<ConstanciaEssalud> obtenerConstanciaEsSalud(String cuspp);

    byte[] generarConstanciaPDFEsSalud(String cuspp) throws Exception;
}
