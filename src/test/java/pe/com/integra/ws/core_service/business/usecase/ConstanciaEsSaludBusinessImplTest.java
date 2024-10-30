package pe.com.integra.ws.core_service.business.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.com.integra.ws.core_service.domain.entity.ConstanciaEssalud;
import pe.com.integra.ws.core_service.infrastructure.integration.datapower.port.DataPowerApi;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ConstanciaEsSaludBusinessImplTest {

    @Spy
    @InjectMocks
    ConstanciaEsSaludBusinessImpl constanciaEsSaludBusinessImpl;

    @Mock
    DataPowerApi dataPowerApi;

    @Test
    void constanciaEssaludReporteVacioTest() throws Exception {

        List<ConstanciaEssalud> listaReporte = new ArrayList<>();
        when(dataPowerApi.obtenerDatosReportteConstanciaEssalud(anyString())).thenReturn(listaReporte);

        MockHttpServletResponse responseMock = mock(MockHttpServletResponse.class);

        String cuspp = "600000000RC7";

        constanciaEsSaludBusinessImpl.generarConstanciaPDFEsSalud(cuspp);

        verify(dataPowerApi).obtenerDatosReportteConstanciaEssalud(anyString());
    }

    @Test
    void constanciaEsSaludGenerarPDFExitosoTest() throws Exception {
        ConstanciaEssalud constancia = new ConstanciaEssalud();
        constancia.setDni("43212211");
        constancia.setTipo_documento("DNI");
        constancia.setPrimer_nombre("Jose");
        constancia.setSegundo_nombre("Luis");
        constancia.setPrimer_apeliido("Lopez");
        constancia.setSegundo_apellido("Alvarez");
        constancia.setFecha_primer_envio("2024-10-30");

        List<ConstanciaEssalud> listaReportes = Collections.singletonList(constancia);
        when(dataPowerApi.obtenerDatosReportteConstanciaEssalud(anyString())).thenReturn(listaReportes);

        InputStream jrxmlStream = getClass().getClassLoader().getResourceAsStream("ConstanciaEsSalud.jrxml");
        assertNotNull(jrxmlStream, "El archivo JRXML debe estar disponible para la prueba");

        String cuspp = "600000000RC7";
        byte[] resultado = constanciaEsSaludBusinessImpl.generarConstanciaPDFEsSalud(cuspp);

        verify(dataPowerApi).obtenerDatosReportteConstanciaEssalud(cuspp);

        assertNotNull(resultado, "El PDF debería generarse correctamente con datos válidos");

        assertTrue(resultado.length > 0, "El PDF generado no debe estar vacío");
    }

    @Test
    void constanciaEsSaludDatosNulosEnObtenerDatosPorCuspp() throws Exception {
        when(dataPowerApi.obtenerDatosReportteConstanciaEssalud(anyString())).thenReturn(null);

        String cuspp = "600000000RC7";

        Exception exception = assertThrows(Exception.class, () -> {
            constanciaEsSaludBusinessImpl.generarConstanciaPDFEsSalud(cuspp);
        });

        String mensajeEsperado = "Datos no encontrados";
        assertEquals(mensajeEsperado, exception.getMessage(), "El mensaje de la excepción debería indicar que no se encontraron datos");
    }

    @Test
    void constanciaEsSaludGenerarPDFConParametrosNulos() throws Exception {

        ConstanciaEssalud constancia = new ConstanciaEssalud();
        constancia.setDni(null);
        constancia.setTipo_documento("DNI");
        constancia.setPrimer_nombre("Juan");
        constancia.setPrimer_apeliido("Pérez");
        constancia.setFecha_primer_envio("2024-10-30");

        List<ConstanciaEssalud> listaReporte = Collections.singletonList(constancia);
        when(dataPowerApi.obtenerDatosReportteConstanciaEssalud(anyString())).thenReturn(listaReporte);

        String cuspp = "600000000RC7";
        Exception exception = assertThrows(Exception.class, () -> {
            constanciaEsSaludBusinessImpl.generarConstanciaPDFEsSalud(cuspp);
        });

        assertNotNull(exception, "Debería lanzar una excepción debido a parámetros nulos");
    }

    @Test
    void constanciaEsSaludDatosNoEncontradosTest() {
        String cuspp = "no_existe_cuspp";
        Exception exception = assertThrows(Exception.class, () -> {
            constanciaEsSaludBusinessImpl.generarConstanciaPDFEsSalud(cuspp);
        });

        assertEquals("Datos no encontrados", exception.getMessage(), "Debería lanzar una excepción cuando no se encuentran datos");
    }

}
