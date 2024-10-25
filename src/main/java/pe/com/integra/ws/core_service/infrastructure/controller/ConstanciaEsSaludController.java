package pe.com.integra.ws.core_service.infrastructure.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.elastic.apm.api.CaptureTransaction;
import io.swagger.annotations.ApiOperation;
import pe.com.integra.ws.core_service.business.port.ConstanciaEsSaludBusiness;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/generar")


public class ConstanciaEsSaludController {
    private static final Logger log = LoggerFactory.getLogger(ConstanciaEsSaludController.class);

    @Autowired
    ConstanciaEsSaludBusiness constanciaEsSalud;

    @GetMapping(value = "/constanciaEssalud")
    @CaptureTransaction(type = "controller")
    @ApiOperation(value = "Operacion: Genera constancia de essalud por cuspp o por dni")

    public void generarArchivo(
            @RequestParam(value = "cuspp") String cuspp,
            @RequestParam(value = "dni") String dni,
            HttpServletResponse response) {
        log.info("Inicio: GeneradorArchivoController generarArchivo");
        constanciaEsSalud.generarArchivoEssalud(cuspp, response);
    }
}
