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


   /* @Value("${aws.secretname}")
    private String awsSecretName;

    @Value("${aws.region}")
    private String awsSecretRegion;

    @Bean
    @Profile("cloud")
    public SecretResponse secretResponse() {
        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard().withRegion(awsSecretRegion).build();
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(awsSecretName);
        GetSecretValueResult getSecretValueResult = client.getSecretValue(getSecretValueRequest);
        String secretString = getSecretValueResult.getSecretString();

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            SecretResponse secretResponse = mapper.readValue(secretString, SecretResponse.class);
            log.info("secretResponse: {}", new ObjectMapper().writeValueAsString(secretResponse));
            secretResponse.setGenerarConstanciaDataPowerSchema(EncryptionCLI.decrypt(Base64.decodeBase64(secretResponse.getGenerarConstanciaDataPowerSchema()), secretResponse.getGenerarConstanciaKeyEncriptacion()));
            secretResponse.setGenerarConstanciaDataPowerUrlProcedure(EncryptionCLI.decrypt(Base64.decodeBase64(secretResponse.getGenerarConstanciaDataPowerUrlProcedure()), secretResponse.getGenerarConstanciaKeyEncriptacion()));
            secretResponse.setGenerarConstanciaDataPowerDatasource(EncryptionCLI.decrypt(Base64.decodeBase64(secretResponse.getGenerarConstanciaDataPowerDatasource()), secretResponse.getGenerarConstanciaKeyEncriptacion()));
            secretResponse.setGenerarConstanciaDataPowerTokenType(EncryptionCLI.decrypt(Base64.decodeBase64(secretResponse.getGenerarConstanciaDataPowerTokenType()), secretResponse.getGenerarConstanciaKeyEncriptacion()));
            secretResponse.setGenerarConstanciaDataPowerTokenValue(EncryptionCLI.decrypt(Base64.decodeBase64(secretResponse.getGenerarConstanciaDataPowerTokenValue()), secretResponse.getGenerarConstanciaKeyEncriptacion()));

            log.info("Result secrets: {}", new ObjectMapper().writeValueAsString(secretResponse));

            return secretResponse;
        } catch (Exception e) {
            log.error("Error acceso AWS Secrets: ", e);
            return null;
        }
    }*/

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
