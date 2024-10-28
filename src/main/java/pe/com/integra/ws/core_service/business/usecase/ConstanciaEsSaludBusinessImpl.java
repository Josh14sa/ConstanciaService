package pe.com.integra.ws.core_service.business.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.integra.ws.core_service.business.port.ConstanciaEsSaludBusiness;
import pe.com.integra.ws.core_service.domain.dto.Constancia95DTO;
import pe.com.integra.ws.core_service.domain.entity.Constancia95;
import pe.com.integra.ws.core_service.domain.entity.ConstanciaEssalud;
import pe.com.integra.ws.core_service.infrastructure.integration.datapower.port.DataPowerApi;
import pe.com.integra.ws.core_service.infrastructure.util.FileUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class ConstanciaEsSaludBusinessImpl implements ConstanciaEsSaludBusiness {

    @Autowired
    DataPowerApi dataPowerApi;

    @Autowired
    public FileUtil fileUtil;

    @Override
    public void generarArchivoEssalud(String cuspp, HttpServletResponse response) {

    }

    @Override
    public ArrayList<Constancia95DTO> obtenerConstanciaEsSalud(Map<String, Object> parametros) throws Exception {
        return null;
    }

    @Override
    public ArrayList<ConstanciaEssalud> obtenerConstanciaEsSalud(String cuspp) {
        List<ConstanciaEssalud> res = dataPowerApi.obtenerDatosReportteConstanciaEssalud(cuspp);
        for (ConstanciaEssalud carta : res)
            return (ArrayList<ConstanciaEssalud>) res;
        return null;
    }
}
