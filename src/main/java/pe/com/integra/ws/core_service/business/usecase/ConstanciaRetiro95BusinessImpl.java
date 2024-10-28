package pe.com.integra.ws.core_service.business.usecase;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import pe.com.integra.ws.core_service.business.port.ConstanciaRetiro95Business;
import pe.com.integra.ws.core_service.domain.aws.SecretResponse;
import pe.com.integra.ws.core_service.domain.entity.Constancia95;
import pe.com.integra.ws.core_service.domain.entity.ListaReporteLineaFormateado;
import pe.com.integra.ws.core_service.infrastructure.integration.datapower.port.DataPowerApi;
import pe.com.integra.ws.core_service.infrastructure.util.FileUtil;
import pe.com.integra.ws.core_service.infrastructure.util.GenerarPDF;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class ConstanciaRetiro95BusinessImpl implements ConstanciaRetiro95Business {

    @Autowired
    DataPowerApi dataPowerApi;

    @Autowired
    private SecretResponse secretResponse;
    @Autowired
    public GenerarPDF generarPDF;

    @Autowired
    public FileUtil fileUtil;

    @Autowired
    private ResourceLoader resourceLoader;

    public void generateReport(HttpServletResponse response, List<Constancia95> dataList) throws Exception {

        // Verificar que la lista tenga datos
        if (dataList == null || dataList.isEmpty()) {
            throw new Exception("No hay datos en la lista para generar el reporte.");
        }

        // Cargar el archivo JRXML
        InputStream jasperStream = resourceLoader.getResource("classpath:Constancia95_5.jrxml").getInputStream();

        // Compilar el archivo JRXML
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

        // Parámetros del reporte
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nombreape", "Nombre Apellido"); // Cambia esto según tus necesidades
        parameters.put("dni", "12345678"); // Cambia esto según tus necesidades
        parameters.put("tipoDocumento", "DNI"); // Cambia esto según tus necesidades
        parameters.put("cuspp", "CUSPP123456"); // Cambia esto según tus necesidades
        parameters.put("fecha", "2024-10-28"); // Cambia esto según tus necesidades
        parameters.put("logo", "/ruta/del/logo.png"); // Cambia esto según tus necesidades
        parameters.put("ruc", "20157036794"); // Cambia esto según tus necesidades
        parameters.put("fechaConstancia", "2024-10-28"); // Cambia esto según tus necesidades
        parameters.put("firma", "/ruta/de/firmaConstancia.png"); // Cambia esto según tus necesidades
        parameters.put("gerente", "Gerente Nombre"); // Cambia esto según tus necesidades

        // Crear un datasource
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

        // Llenar el reporte
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Comprobar si el jasperPrint tiene datos
        if (jasperPrint.getPages().isEmpty()) {
            throw new Exception("No hay datos para generar el reporte.");
        }

        // Configurar la respuesta HTTP
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=report.pdf");

        // Exportar a PDF
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }


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

    @Override
    public ArrayList obtenerConstanciaRetiro95(Map parametros) throws Exception {
        return null;
    }





    public List<JasperPrint> generarJasperPrints(List<byte[]> listFiles, Map<String, Object> parameters,
            List<ListaReporteLineaFormateado> filas) throws JRException {
        if (filas == null) {
            return generarCartaLocal(listFiles, parameters);
        }
        return generarCartaLocal(listFiles, parameters, filas);
    }


    public List<JasperPrint> generarCartaLocal(List<byte[]> listFiles, Map<String, Object> parameters)
            throws JRException {
        List<JasperPrint> listaJaspers = new ArrayList<>();
        for (byte[] file : listFiles) {
            listaJaspers.add(generarPDF.exportPdfFile(file, parameters));
        }
        return listaJaspers;
    }

    public List<JasperPrint> generarCartaLocal(List<byte[]> listFiles, Map<String, Object> parameters,
            List<ListaReporteLineaFormateado> filas) throws JRException {
        List<JasperPrint> listaJaspers = new ArrayList<>();
        int i = 0;
        for (byte[] file : listFiles) {
            if (!filas.get(i).getListaLineas().isEmpty()) {
                listaJaspers.add(generarPDF.exportPdfFile(file, parameters, filas.get(i).getListaLineas()));
            } else {
                listaJaspers.add(generarPDF.exportPdfFile(file, parameters));
            }
            i++;
        }
        return listaJaspers;
    }
}
