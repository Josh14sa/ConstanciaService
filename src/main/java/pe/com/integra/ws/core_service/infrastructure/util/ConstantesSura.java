package pe.com.integra.ws.core_service.infrastructure.util;

public class ConstantesSura {

    private ConstantesSura() {
        throw new IllegalStateException("ConstantsSura class");
    }

    public static final int CODE_OK = 1200;
    public static final String MSG_OK = "Success";

    public static final int CODIGO_ERROR = 1500;
    public static final String MENSAJE_ERROR = "Error Generico";

    public static final int CODIGO_ERROR_COMUNICACION_SERVICIOS = 1501;
    public static final String MENSAJE_ERROR_COMUNICACION_SERVICIOS = "Error Conexión Servicios";

    public static final int CODIGO_ERROR_DATAPOWER = 1502;
    public static final String MENSAJE_ERROR_DATAPOWER = "Error DataPower message: ";

    public static final int CODIGO_CAMPO_MAL_FORMADO = 1400;
    public static final String MENSAJE_CAMPO_MAL_FORMADO = "campos malformados ingresados";

    public static final int CODIGO_BODY_NO_VALIDO = 1401;
    public static final String MENSAJE_BODY_NO_VALIDO = "El Valor enviado no es valido";

    public static final int CODIGO_PARAMETRO_TIPO_NO_VALIDO = 1402;
    public static final String MENSAJE_PARAMETRO_TIPO_NO_VALIDO = " debe ser de tipo ";

    public static final int CODIGO_PARAMETRO_OBLIGATORIO = 1403;
    public static final String MENSAJE_PARAMETRO_OBLIGATORIO = "Campo debe ser obligatorio ";

    public static final int CODIGO_ERROR_SFTP = 1406;
    public static final String MENSAJE_ERROR_SFTP = "Ocurrió un error, subiendo archivo al servidor sftp";

    public static final int CODIGO_ERROR_SAMBA = 1408;
    public static final String MENSAJE_DE_ERROR_SAMBA = "Ocurrió un error, al obtener archivo del servidor samba";

    public static final int CODIGO_ERROR_ENVIO_MKT = 1409;
    public static final String MENSAJE_ERROR_ENVIO_MKT = "Error en el envío a Marketing Cloud";

    public static final int CODIGO_CONVERSACIONES_NO_OBTENIDO = 6030;
    public static final String MENSAJE_CONVERSACIONES_NO_OBTENIDO = "No se encontraron conversaciones a generar";

    public static final int CODIGO_CONTACTKEY_NO_OBTENIDO = 6031;
    public static final String MENSAJE_CONTACTKEY_NO_OBTENIDO = "No existe contactKey en campo DANC58 de la tabla DANA04. ";

    public static final int CODIGO_EXTERNALKEY_NO_OBTENIDO = 6032;
    public static final String MENSAJE_EXTERNALKEY_NO_OBTENIDO = "No se encontró el EXTERNAL_KEY para el CODIGO_COMUNICACION: ";

    public static final int CODIGO_CABECERA_NO_OBTENIDO = 6033;
    public static final String MENSAJE_CABECERA_NO_OBTENIDO = "No se encontraron cabeceras para el código de comunicación. ";

    public static final int CODIGO_DETALLE_NO_OBTENIDO = 6034;
    public static final String MENSAJE_DETALLE_NO_OBTENIDO = "No se encontraron detalles para el código de comunicación. ";

    public static final int CODIGO_VALIDACION_ID_CONVERSACION = 6035;
    public static final String MENSAJE_VALIDACION_ID_CONVERSACION = "Código conversación no esta relacionado a un ID comunicación. ";

    public static final int CODIGO_VALIDACION_ID_MATCHCONTACTKEY = 6036;
    public static final String MENSAJE_VALIDACION_MATCH_CONTACTKEY = "No se encuentra en el map el contactKey: ";

    public static final String MENSAJE_PROCESO_EJECUTANDOSE_EN_SEGUNDO_PLANO = "El proceso se está ejecutando en segundo plano";


}
