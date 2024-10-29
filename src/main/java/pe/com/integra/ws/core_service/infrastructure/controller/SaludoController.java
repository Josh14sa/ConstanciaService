package pe.com.integra.ws.core_service.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.integra.ws.core_service.business.usecase.ConstanciaRetiro95BusinessImpl;

@RestController
@RequestMapping("/api/v1")
public class SaludoController {

	@GetMapping("/saludo")
	public String saludo() {
		return "Hola";
	}
}
