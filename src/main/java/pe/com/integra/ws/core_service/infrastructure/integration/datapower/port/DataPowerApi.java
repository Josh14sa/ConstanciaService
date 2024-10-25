package pe.com.integra.ws.core_service.infrastructure.integration.datapower.port;

import pe.com.integra.ws.core_service.domain.dto.Constancia95DTO;
import pe.com.integra.ws.core_service.domain.entity.Constancia95;
import pe.com.integra.ws.core_service.domain.entity.ConstanciaEssalud;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DataPowerApi {
    public List<Constancia95> obtenerDatosReporteConstanciaRetiro95(String cuspp);

    public List<ConstanciaEssalud> obtenerDatosReportteConstanciaEssalud(String cuspp);
    ArrayList obtenerConstanciaRetiro95(Map<String, Object> parametros) throws Exception;
    ArrayList<Constancia95DTO> obtenerConstanciaEsSalud(Map<String, Object> parametros);
}
