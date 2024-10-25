package pe.com.integra.ws.core_service.infrastructure.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pe.com.integra.ws.core_service.domain.exception.ComunicacionException;

import java.io.File;
import java.io.FileOutputStream;

import static pe.com.integra.ws.core_service.infrastructure.util.Constantes.MENSAJE_ERROR_GENERICO;

@Slf4j
@Component
public class FileUtil {

    private FileUtil() {
    }

    public static File convertByteFile(byte[] bytesFile, String fileName) {
        File convFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(bytesFile);
            return convFile;
        } catch (Exception e) {
            //log.error("ERROR: {}", e.getLocalizedMessage());
            LogUtil.logException(MENSAJE_ERROR_GENERICO, e, FileUtil.class);
            throw new ComunicacionException(e.getMessage(), e.getCause());
        }
    }
}
