package pe.com.integra.ws.core_service.infrastructure.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static pe.com.integra.ws.core_service.infrastructure.util.Constantes.ESPACIO;
import static pe.com.integra.ws.core_service.infrastructure.util.Constantes.PUNTO;

@Slf4j
@Component
public class Util {

    public static InputStream toInputStream(byte[] byteArr) {
        return new ByteArrayInputStream(byteArr);
    }

    public static String concatenarQueryDataPower(String schema, String procedure) {
        return schema.concat(PUNTO).concat(procedure);
    }

    /*public static String concatenarTokenDataPower(String tokenType, String tokenValue) {
        return tokenType.concat(ESPACIO).concat(tokenValue);
    }*/

    public static String concatenarTokenDataPower(String tokenType, String tokenValue) {
        if (tokenType == null || tokenValue == null) {
            throw new IllegalArgumentException("El tipo o valor del token no puede ser nulo");
        }
        return tokenType.concat(" ").concat(tokenValue);
    }

}
