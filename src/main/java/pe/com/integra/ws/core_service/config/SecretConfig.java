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
    private static final Logger log = LoggerFactory.getLogger(SecretResponse.class);



    @Bean
    @Profile("dev")
    public SecretResponse secretsResponse() {
        log.info("inicio: secrets");
        SecretResponse secretResponse = new SecretResponse();
        secretResponse.setGenerarConstanciaKeyEncriptacion("");
        secretResponse.setGenerarConstanciaDataPowerSchema("MINTPRO");
        secretResponse.setGenerarConstanciaDataPowerUrlProcedure("https://apidb-integra-desa-procedure.desa.ocp.sura.pe");
        secretResponse.setGenerarConstanciaDataPowerDatasource("GENERADOR_CONSTANCIA_AS400");
        secretResponse.setGenerarConstanciaDataPowerTokenType("Bearer");
        secretResponse.setGenerarConstanciaDataPowerTokenValue("");

        log.info("fin: secrets");
        return secretResponse;
    }

}
