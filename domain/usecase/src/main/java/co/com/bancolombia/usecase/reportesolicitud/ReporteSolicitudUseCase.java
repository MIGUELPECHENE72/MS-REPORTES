package co.com.bancolombia.usecase.reportesolicitud;

import co.com.bancolombia.model.reportesolicitud.ReporteSolicitud;
import co.com.bancolombia.model.reportesolicitud.gateways.ReporteSolicitudRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ReporteSolicitudUseCase {

    private final ReporteSolicitudRepository reporteSolicitudRepository;

    public Mono<ReporteSolicitud> findById(String id){
        return reporteSolicitudRepository.getById(id);
    }

    public Mono<ReporteSolicitud> save(ReporteSolicitud reporteSolicitud){
        return reporteSolicitudRepository.save(reporteSolicitud);
    }

}
