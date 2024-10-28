package pe.com.integra.ws.core_service.infrastructure.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static pe.com.integra.ws.core_service.infrastructure.util.Constantes.ESPACIO;
import static pe.com.integra.ws.core_service.infrastructure.util.Constantes.PUNTO;

@Slf4j
@Component
public class Util {

    public static final int BUFFER_SIZE = 4096;
    private static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";
    private static final String ATACHMENT_FILE = "attachment; filename=\"%s\"";

    private Util() {
    }

    public static String concatenarQueryDataPower(String schema, String procedure) {
        return schema.concat(PUNTO).concat(procedure);
    }

    public static String concatenarTokenDataPower(String tokenType, String tokenValue) {
        return tokenType.concat(ESPACIO).concat(tokenValue);
    }

    public static void downloadFileProperties(HttpServletResponse resp, byte[] file, String fileNameToShow)
            throws IOException {

        String mimeType = getMimeType(fileNameToShow);
        resp.setContentType(mimeType);

        String headerKey = HEADER_CONTENT_DISPOSITION;
        String headerValue = String.format(ATACHMENT_FILE, fileNameToShow);
        resp.setHeader(headerKey, headerValue);

        OutputStream outStream = resp.getOutputStream();
        InputStream inputStream = toInputStream(file);
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outStream.close();
    }
    public static String getMimeType(String fileName) {
        if (fileName != null) {
            int posicionPunto = fileName.lastIndexOf('.');
            String extension = fileName.substring(posicionPunto);
            if (Constantes.EXTENSION_PDF.equals(extension)) {
                return "application/pdf";
            } else if (Constantes.EXTENSION_ZIP.equals(extension)) {
                return "application/zip";
            }
        }
        return "application/octet-stream";
    }

    public static InputStream toInputStream(byte[] byteArr) {
        return new ByteArrayInputStream(byteArr);
    }

}
