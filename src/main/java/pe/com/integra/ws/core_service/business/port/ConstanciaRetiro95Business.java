package pe.com.integra.ws.core_service.business.port;

import pe.com.integra.ws.core_service.domain.entity.Constancia95;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ConstanciaRetiro95Business {

    public void generarArchivoRetiros95(String cuspp);

    public List<Constancia95> generarArchivoRetiro95(String cuspp);

    ArrayList<Constancia95> obtenerConstanciaRetiro95(String cuspp);

    public ArrayList obtenerConstanciaRetiro95(Map parametros) throws Exception;


}
