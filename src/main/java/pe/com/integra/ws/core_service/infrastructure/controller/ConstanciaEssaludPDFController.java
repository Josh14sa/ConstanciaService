package pe.com.integra.ws.core_service.infrastructure.controller;

import co.elastic.apm.api.CaptureTransaction;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.com.integra.ws.core_service.business.port.ConstanciaEsSaludBusiness;
import pe.com.integra.ws.core_service.domain.exception.ComunicacionException;

@RestController
@RequestMapping("/api/v1/constanciaEssalud")
public class ConstanciaEssaludPDFController {

    @Autowired
    ConstanciaEsSaludBusiness constanciaEsSaludBusiness;

    @PostMapping("/descargar-pdf")
    @CaptureTransaction(type = "controller")
    @ApiOperation(value = "Operacion: Genera constancia en pdf de essalud por cuspp")
    public ResponseEntity<byte[]> imprimirConstanciaEssalud(
            @RequestParam(value = "cuspp") String cuspp) {
        try {
            byte[] pdfBytes = constanciaEsSaludBusiness.generarConstanciaPDFEsSalud(cuspp);

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