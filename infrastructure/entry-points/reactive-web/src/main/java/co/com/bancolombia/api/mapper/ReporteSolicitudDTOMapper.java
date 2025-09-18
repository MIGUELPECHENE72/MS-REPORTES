package co.com.bancolombia.api.mapper;

import co.com.bancolombia.api.dto.ReporteSolicitudDTO;
import co.com.bancolombia.model.reportesolicitud.ReporteSolicitud;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReporteSolicitudDTOMapper {

    ReporteSolicitudDTO toResponse(ReporteSolicitud reporteSolicitud);

}
