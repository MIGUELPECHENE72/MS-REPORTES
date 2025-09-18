package co.com.bancolombia.model.reportesolicitud;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ReporteSolicitud {
    private String id;
    private String estado;
    private Long cantidad;
    private BigDecimal montoTotal;
}
