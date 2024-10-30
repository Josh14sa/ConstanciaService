package pe.com.integra.ws.core_service.infrastucture.controller;

import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pe.com.integra.ws.core_service.business.port.ConstanciaRetiro95Business;
import pe.com.integra.ws.core_service.domain.exception.ComunicacionException;
import pe.com.integra.ws.core_service.infrastructure.controller.ConstanciaRetiro95PDFController;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConstanciaRetiro95PDFController.class)
public class ConstanciaRetiro95PDFControllerTest {

    @MockBean
    private ConstanciaRetiro95Business constanciaRetiro95Business;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void imprimirConstanciaRetiro95_Exitoso() throws Exception {

        String cuspp = "650000000RC7";
        byte[] pdfBytes = "dummy pdf content".getBytes();

        when(constanciaRetiro95Business.generarConstanciaPDFRetiro95(cuspp)).thenReturn(pdfBytes);

        this.mockMvc.perform(post("/api/v1/constanciaRetiro95/descargar-pdf")
                        .param("cuspp", cuspp))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> {
                    var response = result.getResponse();
                    assertEquals(MediaType.APPLICATION_PDF_VALUE, response.getContentType());
                    assertEquals(pdfBytes.length, response.getContentLength());
                });
    }

    @Test
    public void imprimirConstanciaRetiro95_SinContenido() throws Exception {

        String cuspp = "650000000RC7";

        when(constanciaRetiro95Business.generarConstanciaPDFRetiro95(cuspp)).thenReturn(null);

        this.mockMvc.perform(post("/api/v1/constanciaRetiro95/descargar-pdf")
                        .param("cuspp", cuspp))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void imprimirConstanciaRetiro95_CusppInvalido() throws Exception {

        String cuspp = "invalidCuspp";

        when(constanciaRetiro95Business.generarConstanciaPDFRetiro95(anyString()))
                .thenThrow(new ComunicacionException.InvalidCusppException("CUSPP inválido."));

        this.mockMvc.perform(post("/api/v1/constanciaRetiro95/descargar-pdf")
                        .param("cuspp", cuspp))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void imprimirConstanciaRetiro95_ErrorInternoDelServidor() throws Exception {

        String cuspp = "650000000RC7";

        when(constanciaRetiro95Business.generarConstanciaPDFRetiro95(cuspp))
                .thenThrow(new RuntimeException("Error interno"));

        this.mockMvc.perform(post("/api/v1/constanciaRetiro95/descargar-pdf")
                        .param("cuspp", cuspp))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }
}
