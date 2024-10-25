package pe.com.integra.ws.core_service.infrastructure.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.com.integra.ws.core_service.business.port.ConstanciaRetiro95Business;
import pe.com.integra.ws.core_service.business.usecase.ConstanciaRetiro95BusinessImpl;
import pe.com.integra.ws.core_service.domain.entity.Constancia95;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/afiliado")


public class ConstanciaRetiro95Controller {
    @Autowired
    ConstanciaRetiro95Business constanciaRetiro95Business;

    @GetMapping("/retirar95")
    public ResponseEntity<?> obtenerConstanciaRetiro95(@RequestParam String cuspp) {
        ArrayList<Constancia95> listarDatosConstanciaRetiro95 = constanciaRetiro95Business.obtenerConstanciaRetiro95(cuspp);

        if (listarDatosConstanciaRetiro95.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron datos para el CUSPP proporcionado.");
        }

        Constancia95 constancia95 = listarDatosConstanciaRetiro95.get(0);
       // String emailAfiliado = constanciaService.obtenerEmailAfiliado(cuspp);

        Map<String, Object> response = new HashMap<>();
        response.put("datosContancia95", constancia95);

        response.put("tipoReporte", "Constancia 95.5");
        response.put("nombresAfiliado", constancia95.getPrimerNombre());
        response.put("cuspp", cuspp.trim());

        return ResponseEntity.ok(response);
    }
}

