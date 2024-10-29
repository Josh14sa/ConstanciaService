package pe.com.integra.ws.core_service.infrastructure.controller;

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
import pe.com.integra.ws.core_service.domain.dto.GenerarConstanciasDTO;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/constancia")
public class Constancia95PDFController {

    @PostMapping("/imprimir")
    public ResponseEntity<byte[]> imprimirConstancia(@RequestBody GenerarConstanciasDTO request) {
        try {
            // Cargar el archivo JRXML desde el paquete resources
            InputStream jrxmlStream = getClass().getClassLoader().getResourceAsStream("Constancia95_5.jrxml");
            if (jrxmlStream == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            // Compilar el archivo JRXML
            JasperReport report = JasperCompileManager.compileReport(jrxmlStream);

            // Configurar los parámetros para el informe
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("nombreape", request.getNombreCompletoAfiliado());
            parameters.put("cuspp", request.getCuspp());
            parameters.put("dni", request.getDni());
            parameters.put("tipoDocumento", request.getTipoDocumento());
            parameters.put("fechaConstancia", request.getFechaConstancia());
            parameters.put("fecha", request.getFechaActual());

            // Cargar imágenes de recursos (si existen)
            InputStream logoStream = getClass().getClassLoader().getResourceAsStream("logo.png");
            if (logoStream != null) {
                parameters.put("logo", logoStream);
            } else {
                // Manejar el caso donde no se encontró el logo
                System.out.println("Logo no encontrado");
            }

            InputStream firmaStream = getClass().getClassLoader().getResourceAsStream("firmaConstancia.png");
            if (firmaStream != null) {
                parameters.put("firma", firmaStream);
            } else {
                // Manejar el caso donde no se encontró la firma
                System.out.println("Firma no encontrada");
            }

            parameters.put("gerente", "Shirley Parodi Arevalo");

            // Crear una lista con el objeto `Constancia95DTO`
            List<GenerarConstanciasDTO> dataList = Collections.singletonList(request);

            // Crear el JRDataSource con datos
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

            // Llenar el informe con los datos y parámetros
            JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

            // Verificar si el informe tiene páginas
            if (print.getPages().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }

            // Exportar a PDF en un array de bytes
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);

            // Configurar los encabezados de la respuesta
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", request.getCuspp().trim() + ".pdf");
            headers.setContentLength(pdfBytes.length);

            // Devolver el PDF en formato byte[]
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (JRException e) {
            // Manejo específico de errores de JasperReports
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            // Manejo general de errores
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
