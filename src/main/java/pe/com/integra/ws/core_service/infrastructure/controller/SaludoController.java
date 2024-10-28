package pe.com.integra.ws.core_service.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.integra.ws.core_service.business.usecase.ConstanciaRetiro95BusinessImpl;
import pe.com.integra.ws.core_service.domain.entity.Constancia95;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController

public class SaludoController {

	@Autowired
	private ConstanciaRetiro95BusinessImpl reportService;

	@GetMapping("/generateReport")
	public void generateReport(HttpServletResponse response) {

		List<Constancia95> dataList = new ArrayList<>(); // Aquí agrega la lógica para obtener tus datos

		try {
			reportService.generateReport(response, dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
