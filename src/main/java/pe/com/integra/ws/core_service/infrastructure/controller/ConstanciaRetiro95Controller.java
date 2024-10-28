package pe.com.integra.ws.core_service.infrastructure.controller;

import co.elastic.apm.api.CaptureTransaction;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.com.integra.ws.core_service.business.port.ConstanciaRetiro95Business;
import pe.com.integra.ws.core_service.domain.entity.Constancia95;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static pe.com.integra.ws.core_service.infrastructure.util.Constantes.MENSAJE_ERROR_CUSPP;


@Slf4j
@RestController
@RequestMapping("/generar")
public class ConstanciaRetiro95Controller {

    @Autowired
    ConstanciaRetiro95Business constanciaRetiro95Business;

    @GetMapping(value = "/constanciaRetiro")
    @CaptureTransaction(type = "controller")
    @ApiOperation(value = "Operacion: Genera constancia de retiro por cuspp")

    public ResponseEntity<?> obtenerConstanciaRetiro(@RequestParam String cuspp) {
        ArrayList<Constancia95> listarDatosConstanciaRetiro= constanciaRetiro95Business.obtenerConstanciaRetiro95(cuspp);

        if (listarDatosConstanciaRetiro == null || listarDatosConstanciaRetiro.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(MENSAJE_ERROR_CUSPP);
        }
        Constancia95 constancia95 = listarDatosConstanciaRetiro.get(0);
        // String emailAfiliado = constanciaService.obtenerEmailAfiliado(cuspp);
        Map<String, Object> response = new HashMap<>();
        response.put("datosContanciaRetiro95", constancia95);
        //response.put("tipoReporte", "Constancia 95.5");
        //response.put("nombresAfiliado", constancia95.getPrimer_nombre());
        //response.put("cuspp", cuspp.trim());

        return ResponseEntity.ok(response);
    }
}
