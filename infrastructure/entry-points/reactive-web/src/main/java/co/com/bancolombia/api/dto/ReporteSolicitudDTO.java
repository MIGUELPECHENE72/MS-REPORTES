package co.com.bancolombia.api.dto;

import java.math.BigDecimal;

public record ReporteSolicitudDTO(
        String id,
        String estado,
        Long cantidad,
        BigDecimal montoTotal) {
}
