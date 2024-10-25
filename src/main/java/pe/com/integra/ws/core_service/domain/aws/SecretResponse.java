package pe.com.integra.ws.core_service.domain.aws;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecretResponse {
    @JsonProperty("GENERAR_CONSTANCIA_KEY_ENCRIPTACION")
    private String generarConstanciaKeyEncriptacion;

    @JsonProperty("GENERAR_CONSTANCIA_DATAPOWER_SCHEMA")
    private String generarConstanciaDataPowerSchema;

    @JsonProperty("GENERAR_CONSTANCIA_DATAPOWER_URL_PROCEDURE")
    private String generarConstanciaDataPowerUrlProcedure;

    @JsonProperty("GENERAR_CONSTANCIA_DATASOURCE_AS400")
    private String generarConstanciaDataPowerDatasource;

    @JsonProperty("GENERAR_CONSTANCIA_DATAPOWER_TOKEN_TYPE")
    private String generarConstanciaDataPowerTokenType;

    @JsonProperty("GENERAR_CONSTANCIA_DATAPOWER_TOKEN_VALUE")
    private String generarConstanciaDataPowerTokenValue;



    @JsonProperty("MARKETINGCLOUD_SFTP_USER")
    private String marketingcloudSftpUser;

    @JsonProperty("MARKETINGCLOUD_SFTP_PASSWORD")
    private String marketingcloudSftpPassword;

    @JsonProperty("MARKETINGCLOUD_GRANT_TYPE")
    private String marketingcloudGrantType;

    @JsonProperty("MARKETINGCLOUD_CLIENT_ID")
    private String marketingcloudClientId;

    @JsonProperty("MARKETINGCLOUD_CLIENT_SECRET")
    private String marketingcloudClientSecret;

}