package co.com.bancolombia.usecase.reportesolicitud;

import co.com.bancolombia.model.reportesolicitud.ReporteSolicitud;
import co.com.bancolombia.model.reportesolicitud.gateways.ReporteSolicitudRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReporteSolicitudUseCaseTest {

    @InjectMocks
    ReporteSolicitudUseCase reporteSolicitudUseCase;

    @Mock
    ReporteSolicitudRepository reporteSolicitudRepository;

    private static final String TEST_ID = "1";
    private static final ReporteSolicitud reporteSolicitud = new ReporteSolicitud("1","Aprobado",2L);

    @Test
    void mustFindById(){

        when(reporteSolicitudRepository.getById(TEST_ID)).thenReturn(Mono.just(reporteSolicitud));

        Mono<ReporteSolicitud> result = reporteSolicitudUseCase.findById(TEST_ID);

        StepVerifier.create(result)
                .assertNext(reporteConsulta -> {
                    assertNotNull(reporteConsulta);
                    assertEquals("Aprobado", reporteConsulta.getEstado());
                })
                .verifyComplete();
    }

    @Test
    void mustSave(){

        when(reporteSolicitudRepository.save(reporteSolicitud)).thenReturn(Mono.just(reporteSolicitud));

        Mono<ReporteSolicitud> result = reporteSolicitudUseCase.save(reporteSolicitud);

        StepVerifier.create(result)
                .assertNext(reporteConsulta -> {
                    assertNotNull(reporteConsulta);
                    assertEquals("Aprobado", reporteConsulta.getEstado());
                })
                .verifyComplete();
    }

}
