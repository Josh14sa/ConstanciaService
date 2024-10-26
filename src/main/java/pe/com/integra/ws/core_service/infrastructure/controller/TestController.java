package pe.com.integra.ws.core_service.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.com.integra.ws.core_service.business.port.ConstanciaRetiro95Business;
import pe.com.integra.ws.core_service.domain.entity.Constancia95;
import pe.com.integra.ws.core_service.domain.exception.SuraException;
import pe.com.integra.ws.core_service.infrastructure.integration.datapower.impl.DataPowerApiImpl;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ConstanciaRetiro95Business constanciaRetiro95Business;

    @GetMapping("/constancia")
    public ResponseEntity<?> testConstancia(@RequestParam String CUSPP) {


        try {
            // Llamar al servicio para obtener los datos
            List<Constancia95> datos = constanciaRetiro95Business.obtenerConstanciaRetiro95(CUSPP);
            if (datos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron datos para el CUSPP proporcionado.");
            }
            return ResponseEntity.ok(datos);
        } catch (SuraException e) {
            // Manejo específico de la excepción SuraException
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al obtener los datos: " + e.getMessage());
        } catch (Exception e) {
            // Manejo genérico de excepciones
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado.");
        }
    }

}