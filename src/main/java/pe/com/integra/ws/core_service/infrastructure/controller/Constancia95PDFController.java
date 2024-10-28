package pe.com.integra.ws.core_service.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pe.com.integra.ws.core_service.domain.dto.Constancia95DTO;
import pe.com.integra.ws.core_service.infrastructure.util.GenerarPDF;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/constancia")
public class Constancia95PDFController {

    @Autowired
    private GenerarPDF generador; // Asegúrate de que GenerarPDF sea un bean de Spring

    @PostMapping("/imprimir")
    public ResponseEntity<InputStreamResource> imprimirConstancia(@RequestBody Constancia95DTO request) {
        try {
            // Cargar el archivo JRXML desde el paquete resources
            InputStream jasperStream = getClass().getResourceAsStream("/Constancia95_5.jrxml");
            if (jasperStream == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            GenerarPDF generador = new GenerarPDF();
            String gerente = "Shirley Parodi Arevalo";

            // Crear un mapa para los parámetros
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("nombreape", request.getNombreCompletoAfiliado());
            parameters.put("dni", request.getDni());
            parameters.put("tipoDocumento", request.getTipoDocumento());
            parameters.put("cuspp", request.getCuspp());
            parameters.put("fecha", request.getFechaActual());
            parameters.put("logo", getClass().getResourceAsStream("/logo.png"));
            parameters.put("fechaConstancia", request.getFechaConstancia());
            parameters.put("firma", getClass().getResourceAsStream("/firmaConstancia.png"));
            parameters.put("gerente", gerente);

            // Compilar el reporte
            JasperReport reporte = generador.getCompiledReport1(jasperStream);

            // Crear un datasource (puedes usar una lista de objetos si es necesario)
            JRDataSource dataSource = new JRBeanCollectionDataSource(Collections.emptyList());

            // Llenar el informe
            JasperPrint print = JasperFillManager.fillReport(reporte, parameters, dataSource);

            // Exportar a PDF
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);

            // Configurar las cabeceras de la respuesta
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + request.getCuspp().trim() + ".pdf");
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);
            headers.setContentLength(pdfBytes.length);

            // Devolver el PDF
            InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(pdfBytes));
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir traza del error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
