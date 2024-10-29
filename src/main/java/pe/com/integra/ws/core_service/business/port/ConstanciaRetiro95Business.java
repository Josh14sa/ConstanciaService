package pe.com.integra.ws.core_service.business.port;

import pe.com.integra.ws.core_service.domain.entity.ConstanciaRetiro95;

import java.util.ArrayList;

public interface ConstanciaRetiro95Business {

    ArrayList<ConstanciaRetiro95> obtenerConstanciaRetiro95(String cuspp);

    byte[] generarConstanciaPDFRetiro95(String cuspp) throws Exception;

}
