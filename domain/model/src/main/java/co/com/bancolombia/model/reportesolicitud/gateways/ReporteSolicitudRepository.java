package co.com.bancolombia.model.reportesolicitud.gateways;

import co.com.bancolombia.model.reportesolicitud.ReporteSolicitud;
import reactor.core.publisher.Mono;

public interface ReporteSolicitudRepository {

    Mono<ReporteSolicitud> getById(String id);

    Mono<ReporteSolicitud> save(ReporteSolicitud reporteSolicitud);

}
