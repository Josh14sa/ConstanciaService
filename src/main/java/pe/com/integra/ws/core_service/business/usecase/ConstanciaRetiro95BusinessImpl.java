package pe.com.integra.ws.core_service.business.usecase;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.integra.ws.core_service.business.port.ConstanciaRetiro95Business;
import pe.com.integra.ws.core_service.domain.dto.GenerarConstanciasDTO;
import pe.com.integra.ws.core_service.domain.entity.ConstanciaRetiro95;
import pe.com.integra.ws.core_service.infrastructure.integration.datapower.port.DataPowerApi;
import java.io.InputStream;
import java.util.*;

@Slf4j
@Service
public class ConstanciaRetiro95BusinessImpl implements ConstanciaRetiro95Business {

    @Autowired
    DataPowerApi dataPowerApi;

    @Override
    public ArrayList<ConstanciaRetiro95> obtenerConstanciaRetiro95(String cuspp) {
        List<ConstanciaRetiro95> res = dataPowerApi.obtenerDatosReporteConstanciaRetiro95(cuspp);
        for (ConstanciaRetiro95 carta : res)
        return (ArrayList<ConstanciaRetiro95>) res;
        return null;
    }

    @Override
    public byte[] generarConstanciaPDFRetiro95(String cuspp) throws Exception {
        GenerarConstanciasDTO request = obtenerDatosPorCuspp(cuspp);
        if (request == null) {
            throw new Exception("Datos no encontrados");
        }

        InputStream jrxmlStream = getClass().getClassLoader().getResourceAsStream("Constancia95_5.jrxml");
        if (jrxmlStream == null) {
            throw new Exception("Plantilla no encontrada.");
        }
        JasperReport report = JasperCompileManager.compileReport(jrxmlStream);
        Map<String, Object> parameters = crearParametrosInforme(request);

        List<GenerarConstanciasDTO> dataList = Collections.singletonList(request);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

        JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
        if (print.getPages().isEmpty()) {
            return null;
        }
        return JasperExportManager.exportReportToPdf(print);
    }

    private GenerarConstanciasDTO obtenerDatosPorCuspp(String cuspp) {
        List<ConstanciaRetiro95> res = dataPowerApi.obtenerDatosReporteConstanciaRetiro95(cuspp);
        if (res == null || res.isEmpty()) {
            return null;
        }

        ConstanciaRetiro95 constancia = res.get(0);
        GenerarConstanciasDTO dto = new GenerarConstanciasDTO();
        dto.setCuspp(cuspp);
        dto.setDni(constancia.getDni());
        dto.setTipoDocumento(constancia.getTipo_documento());
        dto.setPrimerNombre(constancia.getPrimer_nombre());
        dto.setSegundoNombre(constancia.getSegundo_nombre());
        dto.setPrimerApellido(constancia.getPrimer_apeliido());
        dto.setSegundoApellido(constancia.getSegundo_apellido());
        dto.setFechaConstancia(constancia.getFecha_constancia());
        dto.setFechaActual(new Date().toString());

        return dto;
    }

    private Map<String, Object> crearParametrosInforme(GenerarConstanciasDTO request) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nombreape", request.getNombreCompletoAfiliado());
        parameters.put("cuspp", request.getCuspp());
        parameters.put("dni", request.getDni());
        parameters.put("tipoDocumento", request.getTipoDocumento());
        parameters.put("fechaConstancia", request.getFechaConstancia());
        parameters.put("fecha", request.getFechaActual());

        InputStream logoStream = getClass().getClassLoader().getResourceAsStream("logo.png");
        if (logoStream != null) {
            parameters.put("logo", logoStream);
        }

        InputStream firmaStream = getClass().getClassLoader().getResourceAsStream("firmaConstancia.png");
        if (firmaStream != null) {
            parameters.put("firma", firmaStream);
        }

        parameters.put("gerente", "Shirley Parodi Arevalo");
        return parameters;
    }

}


