package pe.com.integra.ws.core_service.business.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.com.integra.ws.core_service.domain.entity.ConstanciaRetiro95;
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
public class ConstanciaRetiro95BusinessImplTest {

    @Spy
    @InjectMocks
    ConstanciaRetiro95BusinessImpl constanciaRetiro95BusinessImpl;

    @Mock
    DataPowerApi dataPowerApi;

    @Test
    void constanciaRetiro95ReporteVacioTest() throws Exception {

        List<ConstanciaRetiro95> listaReporte = new ArrayList<>();
        when(dataPowerApi.obtenerDatosReporteConstanciaRetiro95(anyString())).thenReturn(listaReporte);

        MockHttpServletResponse responseMock = mock(MockHttpServletResponse.class);

        String cuspp = "600000000RC7";

        constanciaRetiro95BusinessImpl.generarConstanciaPDFRetiro95(cuspp);

        verify(dataPowerApi).obtenerDatosReporteConstanciaRetiro95(anyString());
    }

    @Test
    void constanciaRetiro95GenerarPDFExitosoTest() throws Exception {
        ConstanciaRetiro95 constancia = new ConstanciaRetiro95();
        constancia.setDni("43212211");
        constancia.setTipo_documento("DNI");
        constancia.setPrimer_nombre("Jose");
        constancia.setSegundo_nombre("Luis");
        constancia.setPrimer_apeliido("Lopez");
        constancia.setSegundo_apellido("Alvarez");
        constancia.setFecha_constancia("2024-10-30");

        List<ConstanciaRetiro95> listaReportes = Collections.singletonList(constancia);
        when(dataPowerApi.obtenerDatosReporteConstanciaRetiro95(anyString())).thenReturn(listaReportes);

        InputStream jrxmlStream = getClass().getClassLoader().getResourceAsStream("Constancia95_5.jrxml");
        assertNotNull(jrxmlStream, "El archivo JRXML debe estar disponible para la prueba");

        String cuspp = "600000000RC7";
        byte[] resultado = constanciaRetiro95BusinessImpl.generarConstanciaPDFRetiro95(cuspp);

        verify(dataPowerApi).obtenerDatosReporteConstanciaRetiro95(cuspp);

        assertNotNull(resultado, "El PDF debería generarse correctamente con datos válidos");

        assertTrue(resultado.length > 0, "El PDF generado no debe estar vacío");
    }

    @Test
    void constanciaRetiro95DatosNulosEnObtenerDatosPorCuspp() throws Exception {
        when(dataPowerApi.obtenerDatosReporteConstanciaRetiro95(anyString())).thenReturn(null);

        String cuspp = "600000000RC7";

        Exception exception = assertThrows(Exception.class, () -> {
            constanciaRetiro95BusinessImpl.generarConstanciaPDFRetiro95(cuspp);
        });

        String mensajeEsperado = "Datos no encontrados";
        assertEquals(mensajeEsperado, exception.getMessage(), "El mensaje de la excepción debería indicar que no se encontraron datos");
    }

    @Test
    void constanciaRetiro95GenerarPDFConParametrosNulos() throws Exception {

        ConstanciaRetiro95 constancia = new ConstanciaRetiro95();
        constancia.setDni(null);
        constancia.setTipo_documento("DNI");
        constancia.setPrimer_nombre("Juan");
        constancia.setPrimer_apeliido("Pérez");
        constancia.setFecha_constancia("2024-10-30");

        List<ConstanciaRetiro95> listaReporte = Collections.singletonList(constancia);
        when(dataPowerApi.obtenerDatosReporteConstanciaRetiro95(anyString())).thenReturn(listaReporte);

        String cuspp = "600000000RC7";
        Exception exception = assertThrows(Exception.class, () -> {
            constanciaRetiro95BusinessImpl.generarConstanciaPDFRetiro95(cuspp);
        });

        assertNotNull(exception, "Debería lanzar una excepción debido a parámetros nulos");
    }

    @Test
    void constanciaRetiro95DatosNoEncontradosTest() {
        String cuspp = "no_existe_cuspp";
        Exception exception = assertThrows(Exception.class, () -> {
            constanciaRetiro95BusinessImpl.generarConstanciaPDFRetiro95(cuspp);
        });

        assertEquals("Datos no encontrados", exception.getMessage(), "Debería lanzar una excepción cuando no se encuentran datos");
    }
}
