package co.com.bancolombia.api;

import co.com.bancolombia.api.dto.ReporteSolicitudDTO;
import co.com.bancolombia.api.util.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/reportes/{id}",
                    produces = { "application/json" },
                    method = RequestMethod.GET,
                    beanClass = Handler.class,
                    beanMethod = "listenGetByID",
                    operation = @Operation(
                            operationId = "getByID",
                            summary = "Obtener un reporte de solicitud por ID",
                            tags = { "Reporte solicitud" },
                            parameters = {
                                    @Parameter(
                                            name = "id",
                                            description = "ID del reporte de solicitud",
                                            required = true,
                                            in = ParameterIn.PATH,
                                            example = "1"
                                    )
                            },
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Solicitud exitosa",
                                            content = @Content(
                                                    mediaType = "application/json",
                                                    schema = @Schema(implementation = ReporteSolicitudDTO.class),
                                                    examples = @ExampleObject(
                                                            value = """
                                                            {
                                                              "id": 1,
                                                              "estado": "Aprobado",
                                                              "cantidad": 2
                                                            }
                                                            """
                                                    )
                                            )
                                    ),
                                    @ApiResponse(responseCode = "401", description = "No autorizado"),
                                    @ApiResponse(responseCode = "403", description = "Acceso denegado"),
                                    @ApiResponse(
                                            responseCode = "404",
                                            description = "Solicitud no encontrada",
                                            content = @Content(
                                                    mediaType = "application/json",
                                                    schema = @Schema(implementation = ErrorResponse.class),
                                                    examples = @ExampleObject(
                                                            value = """
                                                            {
                                                              "errorCode": "Not Found",
                                                              "message": "No se ha encontrado un reporte de solicitud con el id: 1"
                                                            }
                                                            """
                                                    )
                                            )
                                    )
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET("/api/v1/reportes/{id}"), handler::listenGetByID);
    }
}
