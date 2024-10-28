package pe.com.integra.ws.core_service.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ListaReporteLineaFormateado {

    private BigDecimal nroPagina;
    private BigDecimal nroLista;
    private List<Map<String,Object>> listaLineas;

    public static class Builder {
        private BigDecimal nroPagina;
        private BigDecimal nroLista;
        private List<Map<String,Object>> listaLineas;

        public Builder(int nroPagina) {
            this.nroPagina = new BigDecimal(nroPagina);
        }

        public Builder conNroLista(int nroLista){
            this.nroLista = new BigDecimal(nroLista) ;
            return this;
        }

        public Builder conListaLineas(List<Map<String,Object>> listaLineas){
            this.listaLineas = listaLineas;
            return this;
        }

        public ListaReporteLineaFormateado build(){
            ListaReporteLineaFormateado bean = new ListaReporteLineaFormateado();
            bean.setNroPagina(this.nroPagina);
            bean.setListaLineas(this.listaLineas);
            bean.setNroLista(this.nroLista);
            return bean;
        }
    }
}
