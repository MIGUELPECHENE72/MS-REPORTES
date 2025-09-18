package co.com.bancolombia.api;

import co.com.bancolombia.api.mapper.ReporteSolicitudDTOMapper;
import co.com.bancolombia.model.reportesolicitud.Exception.ResourceNotFoundException;
import co.com.bancolombia.usecase.reportesolicitud.ReporteSolicitudUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class Handler {

    private  final ReporteSolicitudUseCase reporteSolicitudUseCase;

    private final ReporteSolicitudDTOMapper reporteSolicitudDTOMapper;

    public Mono<ServerResponse> listenGetByID(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return reporteSolicitudUseCase.findById(id)
                .flatMap(reporte -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .bodyValue(reporteSolicitudDTOMapper.toResponse(reporte)))
                .switchIfEmpty(Mono.error(
                        new ResourceNotFoundException("No se ha encontrado un reporte de solicitud con el id: " + id)));
    }

}
