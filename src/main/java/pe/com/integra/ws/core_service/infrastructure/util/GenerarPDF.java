package pe.com.integra.ws.core_service.infrastructure.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class GenerarPDF {


    public JasperPrint exportPdfFile(byte[] plantilla, Map<String, Object> parameters) throws JRException {
        JasperReport jasperReport = JasperCompileManager.compileReport(Util.toInputStream(plantilla));
        return JasperFillManager.fillReport(jasperReport, parameters);
    }

    public JasperPrint exportPdfFile(byte[] plantilla, Map<String, Object> parameters, List<Map<String,Object>> filas) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(Util.toInputStream(plantilla));
            return JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(filas));
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}