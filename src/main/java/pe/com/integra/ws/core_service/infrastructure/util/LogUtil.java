package pe.com.integra.ws.core_service.infrastructure.util;

import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class LogUtil {
    public static final String LOG_INFO = "INFO";
    public static final String LOG_DEBUG = "DEBUG";
    public static final String LOG_ERROR = "ERROR";
    private static final String CONSTANTE_X = "  **********  ";
    private static List<Log4j> log4j = new ArrayList<>();

    private LogUtil() {
        super();
    }

    public static <T> void logger(String texto, String type, Class<T> classname) {
        Log4j log = new Log4j(texto, type, classname);
        print(log);
        LogUtil.log4j.add(log);
    }

    public static void clearLogger() {
        log4j.clear();
    }

    private static void print(Log4j log4j) {
        Logger log = LoggerFactory.getLogger(log4j.getClassName());
        if (log4j.getType().equalsIgnoreCase(LOG_INFO)) {
            log.info(CONSTANTE_X.concat(log4j.getTexto()));
        } else if (log4j.getType().equalsIgnoreCase(LOG_DEBUG)) {
            log.debug(CONSTANTE_X.concat(log4j.getTexto()));
        } else if (log4j.getType().equalsIgnoreCase(LOG_ERROR)) {
            log.error(CONSTANTE_X.concat(log4j.getTexto()));
        }
    }

    public static void logException(String texto, Exception e, Class<?> classname) {
        logger(texto + e.getMessage(), LOG_ERROR, classname);
        for (int i = 0; i < e.getStackTrace().length; i++) {
            logger(e.getStackTrace()[i].toString(), LOG_ERROR, classname);
        }
    }

    @Data
    public static class Log4j {
        private String texto;
        private String type;
        private String fecha;
        private Class<?> className;

        public Log4j(String texto, String type, Class<?> className) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            this.fecha = "[" + sdf.format(new Date()) + "] - ";
            this.texto = texto;
            this.type = type;
            this.className = className;
        }

    }
}
