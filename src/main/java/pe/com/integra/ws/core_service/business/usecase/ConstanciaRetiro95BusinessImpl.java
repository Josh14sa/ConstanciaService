package pe.com.integra.ws.core_service.business.usecase;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.integra.ws.core_service.business.port.ConstanciaRetiro95Business;
import pe.com.integra.ws.core_service.domain.aws.SecretResponse;
import pe.com.integra.ws.core_service.domain.entity.Constancia95;
import pe.com.integra.ws.core_service.infrastructure.integration.datapower.port.DataPowerApi;
import pe.com.integra.ws.core_service.infrastructure.util.FileUtil;
import pe.com.integra.ws.core_service.infrastructure.util.GenerarPDF;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class ConstanciaRetiro95BusinessImpl implements ConstanciaRetiro95Business {
    private static final Logger log = LoggerFactory.getLogger(ConstanciaRetiro95BusinessImpl.class);

    @Autowired
    DataPowerApi dataPowerApi;

    @Autowired
    private SecretResponse secretResponse;
    @Autowired
    public GenerarPDF generarPDF;

    @Autowired
    public FileUtil fileUtil;


    @Override
    public void generarArchivoRetiros95(String cuspp) {

    }

    @Override
    public List<Constancia95> generarArchivoRetiro95(String cuspp) {
        return null;
    }

    @Override
    public ArrayList<Constancia95> obtenerConstanciaRetiro95(String cuspp) {
        List<Constancia95> res = dataPowerApi.obtenerDatosReporteConstanciaRetiro95(cuspp);
        for (Constancia95 carta : res)
        return (ArrayList<Constancia95>) res;
        return null;
    }




    // Método para obtener datos según tu lógica actual
    public void obtenerDatosReporteConstanciaRetiros95(String cuspp) {
        // Implementación existente...

    }

    @Override
    public ArrayList obtenerConstanciaRetiro95(Map parametros) throws Exception {
        return null;
    }
}
