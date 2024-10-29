package pe.com.integra.ws.core_service.infrastructure.integration.datapower.port;

import pe.com.integra.ws.core_service.domain.entity.ConstanciaRetiro95;
import pe.com.integra.ws.core_service.domain.entity.ConstanciaEssalud;

import java.util.List;

public interface DataPowerApi {
    List<ConstanciaRetiro95> obtenerDatosReporteConstanciaRetiro95(String cuspp);

    List<ConstanciaEssalud> obtenerDatosReportteConstanciaEssalud(String cuspp);

}
