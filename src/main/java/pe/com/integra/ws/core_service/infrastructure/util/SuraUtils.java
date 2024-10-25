package pe.com.integra.ws.core_service.infrastructure.util;

import java.util.ArrayList;

import pe.com.integra.ws.core_service.domain.dto.response.SuraResponse;
import pe.com.integra.ws.core_service.domain.exception.SuraException;

public class SuraUtils {

    private SuraUtils() {
    }

    @SuppressWarnings("rawtypes")
    public static SuraResponse responseSuccess(Object object) {
        return SuraResponse.builder()
                .statusResponse(Boolean.TRUE)
                .statusResponseCode(ConstantesSura.CODE_OK)
                .statusResponseMessage(ConstantesSura.MSG_OK)
                .statusResponseBody(object)
                .responseErrors(new ArrayList<>())
                .build();
    }

    public static void generateSuraException(Integer code, String message, Object object){
        throw new SuraException(code, message, object);
    }
}