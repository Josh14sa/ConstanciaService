package pe.com.integra.ws.core_service.business.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.integra.ws.core_service.business.port.ConstanciaEsSaludBusiness;
import pe.com.integra.ws.core_service.domain.dto.Constancia95DTO;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
}
