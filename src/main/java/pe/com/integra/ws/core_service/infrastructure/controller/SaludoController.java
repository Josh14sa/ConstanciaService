package pe.com.integra.ws.core_service.infrastructure.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class SaludoController {

	@GetMapping
	public String obtenerSaludo() {
		return "Hola, Mundo";
	}
}
