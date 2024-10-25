package pe.com.integra.ws.core_service.domain.dto.response;

import lombok.*;
import pe.com.integra.ws.core_service.domain.exception.ErrorBody;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SuraResponse<A> {

    private boolean statusResponse;
    private int statusResponseCode;
    private String statusResponseMessage;
    private int countBody;
    private A statusResponseBody;
    private List<ErrorBody> responseErrors;


}
