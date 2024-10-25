package pe.com.integra.ws.core_service.infrastructure.integration.datapower.feign.port;
import java.net.URI;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.sura.arquitectura.sharedIdp.model.response.ApiDBResponse;
import com.sura.arquitectura.sharedIdp.model.resquest.ApiDBRequest;

import pe.com.integra.ws.core_service.infrastructure.integration.datapower.feign.response.ConstanciaRetiro95Response;
import pe.com.integra.ws.core_service.infrastructure.integration.datapower.feign.response.ConstanciaEssaludResponse;
import pe.com.integra.ws.core_service.infrastructure.integration.datapower.feign.response.OutResultResponse;
@FeignClient(value = "DataPowerFeign", url = "urlDataPower")
public interface DataPowerFeign {

    @PostMapping(path = "${datapower.ws.endpoint.procedure}", produces = "application/json")
    ApiDBResponse<List<ConstanciaRetiro95Response>, List<OutResultResponse>> obtenerDatosConstanciaRetiro95(
            URI baseUrl,
            @RequestBody ApiDBRequest idpRequest,
            @RequestHeader("Authorization") String token);


    @PostMapping(path = "${datapower.ws.endpoint.procedure}", produces = "application/json")
    ApiDBResponse<Object, List<OutResultResponse>> actualizarReintentos(
            URI baseUrl,
            @RequestBody ApiDBRequest idpRequest,
            @RequestHeader("Authorization") String token);


    @PostMapping(path = "${datapower.ws.endpoint.procedure}", produces = "application/json")
    ApiDBResponse<List<ConstanciaEssaludResponse>, List<OutResultResponse>> obtenerDatosConstanciaEssalud(
            URI baseUrl,
            @RequestBody ApiDBRequest idpRequest,
            @RequestHeader("Authorization") String token);


    @PostMapping(path = "${datapower.ws.endpoint.procedure}", produces = "application/json")
    ApiDBResponse<Object, List<OutResultResponse>> actualizarEstado(
            URI baseUrl,
            @RequestBody ApiDBRequest idpRequest,
            @RequestHeader("Authorization") String token);
}
