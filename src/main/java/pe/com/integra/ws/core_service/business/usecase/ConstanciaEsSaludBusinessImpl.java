package pe.com.integra.ws.core_service.business.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.integra.ws.core_service.business.port.ConstanciaEsSaludBusiness;
import pe.com.integra.ws.core_service.domain.dto.Constancia95DTO;
import pe.com.integra.ws.core_service.domain.entity.Constancia95;
import pe.com.integra.ws.core_service.domain.entity.ConstanciaEssalud;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Service
public class ConstanciaEsSaludBusinessImpl implements ConstanciaEsSaludBusiness {
    @Override
    public void generarArchivoEssalud(String cuspp, HttpServletResponse response) {

    }

    @Override
    public ArrayList<Constancia95DTO> obtenerConstanciaEsSalud(Map<String, Object> parametros) throws Exception {
        return null;
    }

    @Override
    public ArrayList<ConstanciaEssalud> obtenerConstanciaEsSalud(String cuspp) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("CUSPP", cuspp);

        // Aquí debes implementar la lógica para obtener la constancia.
        // Este es un ejemplo simplificado y deberías adaptarlo a tus necesidades.
        ArrayList<ConstanciaEssalud> listarDatosConstanciaRetiro95 = new ArrayList<>();

        // Simular obtener datos de un repositorio o fuente de datos.
        // Agregar lógica para llenar listarDatosConstanciaRetiro95 aquí.

        return listarDatosConstanciaRetiro95;
    }
}
