package pe.com.integra.ws.core_service.business.usecase;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.integra.ws.core_service.business.port.ConstanciaEsSaludBusiness;
import pe.com.integra.ws.core_service.domain.dto.GenerarConstanciasDTO;
import pe.com.integra.ws.core_service.domain.entity.ConstanciaEssalud;
import pe.com.integra.ws.core_service.infrastructure.integration.datapower.port.DataPowerApi;

import java.io.InputStream;
import java.util.*;

@Slf4j
@Service
public class ConstanciaEsSaludBusinessImpl implements ConstanciaEsSaludBusiness {

    @Autowired
    DataPowerApi dataPowerApi;

    @Override
    public ArrayList<ConstanciaEssalud> obtenerConstanciaEsSalud(String cuspp) {
        List<ConstanciaEssalud> res = dataPowerApi.obtenerDatosReportteConstanciaEssalud(cuspp);
        for (ConstanciaEssalud carta : res)
            return (ArrayList<ConstanciaEssalud>) res;
        return null;
    }

    @Override
    public byte[] generarConstanciaPDFEsSalud(String cuspp) throws Exception {
        GenerarConstanciasDTO request = obtenerDatosPorCuspp(cuspp);
        if (request == null) {
            return null;
        }

        InputStream jrxmlStream = getClass().getClassLoader().getResourceAsStream("ConstanciaEsSalud.jrxml");
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
        List<ConstanciaEssalud> res = dataPowerApi.obtenerDatosReportteConstanciaEssalud(cuspp);
        if (res == null || res.isEmpty()) {
            return null;
        }

        ConstanciaEssalud constancia = res.get(0);
        GenerarConstanciasDTO dto = new GenerarConstanciasDTO();
        dto.setCuspp(cuspp);
        dto.setDni(constancia.getDni());
        dto.setTipoDocumento(constancia.getTipo_documento());
        dto.setPrimerNombre(constancia.getPrimer_nombre());
        dto.setSegundoNombre(constancia.getSegundo_nombre());
        dto.setPrimerApellido(constancia.getPrimer_apeliido());
        dto.setSegundoApellido(constancia.getSegundo_apellido());
        dto.setFechaPrimerEnvio(constancia.getFecha_primer_envio());
        dto.setFechaActual(new Date().toString());

        return dto;
    }

    private Map<String, Object> crearParametrosInforme(GenerarConstanciasDTO request) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nombreape", request.getNombreCompletoAfiliado());
        parameters.put("cuspp", request.getCuspp());
        parameters.put("dni", request.getDni());
        parameters.put("tipoDocumento", request.getTipoDocumento());
        parameters.put("fechaEsSalud", request.getFechaPrimerEnvio());
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
