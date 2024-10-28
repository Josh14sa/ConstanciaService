package pe.com.integra.ws.core_service.infrastructure.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.elastic.apm.api.CaptureTransaction;
import io.swagger.annotations.ApiOperation;
import pe.com.integra.ws.core_service.business.port.ConstanciaEsSaludBusiness;
import pe.com.integra.ws.core_service.domain.entity.ConstanciaEssalud;
import pe.com.integra.ws.core_service.infrastructure.util.Util;
import static pe.com.integra.ws.core_service.infrastructure.util.Constantes.MENSAJE_ERROR_CUSPP;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/generar")


public class ConstanciaEsSaludController {

    @Autowired
    ConstanciaEsSaludBusiness constanciaEsSalud;
    @Autowired
    private Util util;

    @GetMapping(value = "/constanciaEssalud")
    @CaptureTransaction(type = "controller")
    @ApiOperation(value = "Operacion: Genera constancia de essalud por cuspp")

    public ResponseEntity<?> obtenerConstanciaEssalud(@RequestParam String cuspp) {
        ArrayList<ConstanciaEssalud> listarDatosConstanciaEsalud = constanciaEsSalud.obtenerConstanciaEsSalud(cuspp);

        if (listarDatosConstanciaEsalud == null || listarDatosConstanciaEsalud.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(MENSAJE_ERROR_CUSPP);
        }
        ConstanciaEssalud constanciaEssalud = listarDatosConstanciaEsalud.get(0);
        // String emailAfiliado = constanciaService.obtenerEmailAfiliado(cuspp);
        Map<String, Object> response = new HashMap<>();
        response.put("datosContanciaEsSalud", constanciaEssalud);
        //response.put("tipoReporte", "Constancia 95.5");
        //response.put("nombresAfiliado", constancia95.getPrimer_nombre());
        //response.put("cuspp", cuspp.trim());

        return ResponseEntity.ok(response);
    }
}

