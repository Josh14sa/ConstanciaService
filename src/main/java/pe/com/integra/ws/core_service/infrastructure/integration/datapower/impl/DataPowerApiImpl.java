package pe.com.integra.ws.core_service.infrastructure.integration.datapower.impl;
import static pe.com.integra.ws.core_service.infrastructure.util.Util.concatenarQueryDataPower;
import static pe.com.integra.ws.core_service.infrastructure.util.Util.concatenarTokenDataPower;

import com.google.gson.Gson;
import com.sura.arquitectura.sharedIdp.model.response.ApiDBResponse;
import com.sura.arquitectura.sharedIdp.model.resquest.ApiDBRequest;
import co.elastic.apm.api.CaptureSpan;
import com.sura.arquitectura.sharedIdp.model.resquest.ParamIn;
import com.sura.arquitectura.sharedIdp.model.resquest.Parameters;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.com.integra.ws.core_service.domain.aws.SecretResponse;
import pe.com.integra.ws.core_service.domain.entity.ConstanciaRetiro95;
import pe.com.integra.ws.core_service.domain.entity.ConstanciaEssalud;
import pe.com.integra.ws.core_service.domain.exception.ComunicacionException;
import pe.com.integra.ws.core_service.infrastructure.integration.datapower.feign.port.DataPowerFeign;
import pe.com.integra.ws.core_service.infrastructure.integration.datapower.feign.response.ConstanciaEssaludResponse;
import pe.com.integra.ws.core_service.infrastructure.integration.datapower.feign.response.ConstanciaRetiro95Response;
import pe.com.integra.ws.core_service.infrastructure.integration.datapower.feign.response.OutResultResponse;
import pe.com.integra.ws.core_service.infrastructure.integration.datapower.port.DataPowerApi;
import org.springframework.beans.factory.annotation.Value;
import pe.com.integra.ws.core_service.infrastructure.util.Constantes;
import pe.com.integra.ws.core_service.infrastructure.util.Procedimientos;
import pe.com.integra.ws.core_service.infrastructure.util.SuraUtils;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
public class DataPowerApiImpl implements DataPowerApi {

    @Value("${datapower.ws.application.name}")
    private String applicationName;

    @Autowired
    private SecretResponse secretResponse;

    @Autowired
    private DataPowerFeign dataPowerFeign;

    @Override
    @CaptureSpan(type = "repository")
    public List<ConstanciaRetiro95> obtenerDatosReporteConstanciaRetiro95(String CUSPP) {
        log.info("inicio: dataPowerApi obtenerDatosReporteCuspp");

        List<ConstanciaRetiro95> listaReporte = null;
        List<ParamIn> params = new ArrayList<>();
        Parameters parameters = new Parameters();
        params.add(new ParamIn(CUSPP));

        parameters.setDataSource(secretResponse.getGenerarConstanciaDataPowerDatasource());
        parameters.setQuery(concatenarQueryDataPower(secretResponse.getGenerarConstanciaDataPowerSchema(),
                Procedimientos.PSCLI334.getValue()));
        parameters.setParamsIn(params);

        ApiDBRequest apiDBRequest = new ApiDBRequest().setParameters(parameters)
                .setMethodName(Procedimientos.PSCLI334.getMethod()).setApplicationName(applicationName);

        URI dataPowerWsBaseUrl = URI.create(secretResponse.getGenerarConstanciaDataPowerUrlProcedure());

        try {
            ApiDBResponse<List<ConstanciaRetiro95Response>, List<OutResultResponse>> response = dataPowerFeign
                    .obtenerDatosConstanciaRetiro95(dataPowerWsBaseUrl, apiDBRequest,
                            concatenarTokenDataPower(secretResponse.getGenerarConstanciaDataPowerTokenType(),
                                    secretResponse.getGenerarConstanciaDataPowerTokenValue()));

            if (Objects.nonNull(response.getAudit()) && !(Constantes.DATAPOWER_CODIGO_EXITO).equals(response.getAudit().getCode())) {
                SuraUtils.generateSuraException(Constantes.CODIGO_ERROR_DATAPOWER,
                        Constantes.MENSAJE_ERROR_DATAPOWER.concat(response.getAudit().getMessage()), null);
            }

            if (Objects.nonNull(response.getResult().getRows())) {
                List<ConstanciaRetiro95Response> listDatosResponse = response.getResult().getRows();
                Type listTypeDto = new TypeToken<List<ConstanciaRetiro95>>() {
                }.getType();
                listaReporte = new ModelMapper().map(listDatosResponse, listTypeDto);
            }

            String representacionJSON = new Gson().toJson(response);
            log.info("{} -> {}", concatenarQueryDataPower(secretResponse.getGenerarConstanciaDataPowerSchema(),
                    Procedimientos.PSCLI334.getValue()), representacionJSON);

        } catch (FeignException e) {
            log.error("Error al obtener datos desde DataPower: {}", e.getMessage());
            if (e.status() == 500 && e.getMessage().contains("Invalid string syntax")) {
                throw new ComunicacionException.InvalidCusppException("CUSPP ingresado es inválido. Por favor, verifique e intente nuevamente.");
            }
            throw new RuntimeException("Error al comunicarse con el servicio de DataPower.");
        }
        log.info("fin: dataPowerApi obtenerDatosReporteCuspp");
        return listaReporte;
    }


    @Override
    @CaptureSpan(type = "repository")
    public List<ConstanciaEssalud> obtenerDatosReportteConstanciaEssalud(String cuspp) {
        log.info("inicio: dataPowerApi obtenerDatosReporteCuspp");

        List<ConstanciaEssalud> listaReportes = null;
        List<ParamIn> params = new ArrayList<>();
        Parameters parameters = new Parameters();

        params.add(new ParamIn(cuspp));

        parameters.setDataSource(secretResponse.getGenerarConstanciaDataPowerDatasource());
        parameters.setQuery(concatenarQueryDataPower(secretResponse.getGenerarConstanciaDataPowerSchema(),
                Procedimientos.PSCLI335.getValue()));
        parameters.setParamsIn(params);

        ApiDBRequest apiDBRequest = new ApiDBRequest().setParameters(parameters)
                .setMethodName(Procedimientos.PSCLI335.getMethod()).setApplicationName(applicationName);

        URI dataPowerWsBaseUrl = URI.create(secretResponse.getGenerarConstanciaDataPowerUrlProcedure());

        try {
            // Llamada al servicio de DataPower usando Feign
            ApiDBResponse<List<ConstanciaEssaludResponse>, List<OutResultResponse>> response = dataPowerFeign
                    .obtenerDatosConstanciaEssalud(dataPowerWsBaseUrl, apiDBRequest,
                            concatenarTokenDataPower(secretResponse.getGenerarConstanciaDataPowerTokenType(),
                                    secretResponse.getGenerarConstanciaDataPowerTokenValue()));

            if (Objects.nonNull(response.getAudit()) && !(Constantes.DATAPOWER_CODIGO_EXITO).equals(response.getAudit().getCode())) {
                // Generar una excepción si el código no es de éxito
                SuraUtils.generateSuraException(Constantes.CODIGO_ERROR_DATAPOWER,
                        Constantes.MENSAJE_ERROR_DATAPOWER.concat(response.getAudit().getMessage()), null);
            }

            if (Objects.nonNull(response.getResult().getRows())) {
                List<ConstanciaEssaludResponse> listDatosResponse = response.getResult().getRows();
                Type listTypeDto = new TypeToken<List<ConstanciaEssalud>>() {
                }.getType();
                listaReportes = new ModelMapper().map(listDatosResponse, listTypeDto);
            }

            String representacionJSON = new Gson().toJson(response);
            log.info("{} -> {}", concatenarQueryDataPower(secretResponse.getGenerarConstanciaDataPowerSchema(),
                    Procedimientos.PSCLI335.getValue()), representacionJSON);

        } catch (FeignException e) {
            log.error("Error al obtener datos desde DataPower: {}", e.getMessage());
            if (e.status() == 500 && e.getMessage().contains("Invalid string syntax")) {
                throw new ComunicacionException.InvalidCusppException("CUSPP ingresado es inválido. Por favor, verifique e intente nuevamente.");
            }
            throw new RuntimeException("Error al comunicarse con el servicio de DataPower.");
        }
        log.info("fin: dataPowerApi obtenerDatosReporteCuspp");
        return listaReportes;
    }
}
