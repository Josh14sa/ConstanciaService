package pe.com.integra.ws.core_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import pe.com.integra.ws.core_service.domain.aws.SecretResponse;

@SpringBootApplication
@EnableFeignClients(basePackages = "pe.com.integra.ws.core_service.infrastructure.integration.datapower.feign.port")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
