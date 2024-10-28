package pe.com.integra.ws.core_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import pe.com.integra.ws.core_service.domain.aws.SecretResponse;

import javax.annotation.PreDestroy;
import java.util.concurrent.Executor;

@SpringBootApplication
@EnableFeignClients(basePackages = "pe.com.integra.ws.core_service.infrastructure.integration.datapower.feign.port")
public class Application {

	private ThreadPoolTaskExecutor executor;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Primary
	@Bean(name = "taskExecutor")
	public Executor taskExecutor() {
		executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(15);
		executor.setMaxPoolSize(15);
		executor.setQueueCapacity(25);
		executor.setThreadNamePrefix("Hilo-");
		executor.initialize();
		return executor;
	}

	@PreDestroy
	public void destroy() {
		if (executor != null) {
			executor.shutdown();
		}
	}
}
