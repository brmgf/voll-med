package med.voll.api.controller.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.DetalhesConsulta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Consultas", description = "Grencia as consultas")
public interface ConsultaControllerOpenApi {

    @Operation(summary = "Agendar", description = "Este endpoint realiza o agendamento de uma consulta.")
    ResponseEntity<DetalhesConsulta> agendar(DadosAgendamentoConsulta agendamentoConsulta, UriComponentsBuilder uriBuilder);

    @Operation(summary = "Cancelar", description = "Este endpoint realiza o cancelamento de uma consulta.")
    ResponseEntity<Void> cancelar(DadosCancelamentoConsulta cancelamentoConsulta);
}
