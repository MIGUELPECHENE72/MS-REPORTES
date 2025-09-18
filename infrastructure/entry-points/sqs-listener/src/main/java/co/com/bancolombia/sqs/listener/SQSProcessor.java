package co.com.bancolombia.sqs.listener;

import co.com.bancolombia.usecase.reportesolicitud.ReporteSolicitudUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.model.Message;

import java.math.BigDecimal;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SQSProcessor implements Function<Message, Mono<Void>> {

    private final ReporteSolicitudUseCase reporteSolicitudUseCase;

    @Override
    public Mono<Void> apply(Message message) {
        System.out.println(message.body());
        BigDecimal montoSolicitud = new BigDecimal(message.body());
        return reporteSolicitudUseCase.findById("1")
                .flatMap(reporteSolicitud -> {
                    reporteSolicitud.setCantidad(reporteSolicitud.getCantidad() + 1L);
                    reporteSolicitud.setMontoTotal(reporteSolicitud.getMontoTotal().add(montoSolicitud));
                    return reporteSolicitudUseCase.save(reporteSolicitud);
                }).then(Mono.empty());
    }
}
