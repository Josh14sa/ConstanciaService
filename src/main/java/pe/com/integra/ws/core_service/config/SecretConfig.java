package pe.com.integra.ws.core_service.config;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pe.com.integra.ws.core_service.domain.aws.EncryptionCLI;
import pe.com.integra.ws.core_service.domain.aws.SecretResponse;
import pe.com.integra.ws.core_service.infrastructure.controller.ConstanciaRetiro95Controller;

@Slf4j
@Configuration
public class SecretConfig {

    @Bean
    @Profile("dev")
    public SecretResponse secretsResponse() {
        log.info("inicio: secrets");
        SecretResponse secretResponse = new SecretResponse();
       //secretResponse.setGenerarConstanciaKeyEncriptacion("");
        secretResponse.setGenerarConstanciaDataPowerSchema("MINTPRO");
       secretResponse.setGenerarConstanciaDataPowerUrlProcedure("https://apidb-integra-desa-procedure.desa.ocp.sura.pe");
        secretResponse.setGenerarConstanciaDataPowerDatasource("MKT_COMUNICACION_SERVICE_AS400");
       secretResponse.setGenerarConstanciaDataPowerTokenType("Bearer");
       secretResponse.setGenerarConstanciaDataPowerTokenValue("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJta3QtZGVzYSIsImlhdCI6MTU4NzQxMjk3MCwiZXhwIjoxNjE4OTQ4OTcwLCJhdWQiOiJ3d3cuc3VyYS5jb20iLCJzdWIiOiJzZWd1cmlkYWRAc3VyYS5jb20iLCJkYXRhQmFzZSI6W3sibmFtZSI6Ik1LVF9DT01VTklDQUNJT05fU0VSVklDRV9BUzQwMCIsInBhdGgiOiJkYjIifV0sInNlcnZpY2UiOlt7Im5hbWUiOiJxdWVyeSJ9LHsibmFtZSI6InByb2NlZHVyZSJ9XSwidmVyc2lvbiI6ImtleVRva2VuVjEifQ.Xihp6qTdbWT5mEB21tZgSahA5g3SY-qdiF-WryLKK78");

        log.info("fin: secrets");
        return secretResponse;
    }

}
