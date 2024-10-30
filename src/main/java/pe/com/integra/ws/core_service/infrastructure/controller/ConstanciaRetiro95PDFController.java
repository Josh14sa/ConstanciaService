package pe.com.integra.ws.core_service.infrastructure.controller;

import co.elastic.apm.api.CaptureTransaction;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.integra.ws.core_service.business.port.ConstanciaRetiro95Business;
import pe.com.integra.ws.core_service.domain.exception.ComunicacionException;

@RestController
@RequestMapping("/api/v1/constanciaRetiro95")
public class ConstanciaRetiro95PDFController {

    @Autowired
    ConstanciaRetiro95Business constanciaRetiro95Business;

    @PostMapping("/descargar-pdf")
    @CaptureTransaction(type = "controller")
    @ApiOperation(value = "Operacion: Genera constancia en pdf de retiro95 por cuspp")
    public ResponseEntity<byte[]> imprimirConstanciaRetiro95(
            @RequestParam(value = "cuspp") String cuspp) {
        try {
            byte[] pdfBytes = constanciaRetiro95Business.generarConstanciaPDFRetiro95(cuspp);

            if (pdfBytes == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", cuspp.trim() + ".pdf");
            headers.setContentLength(pdfBytes.length);
            return ResponseEntity.ok().headers(headers).body(pdfBytes);

        } catch (ComunicacionException.InvalidCusppException e) {
            String errorMessage = "Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(errorMessage.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor".getBytes());
        }
    }
}
