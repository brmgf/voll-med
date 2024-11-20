package med.voll.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.domain.consulta.AgendamentoConsultaService;
import med.voll.api.domain.consulta.CancelamentoConsultaService;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.DetalhesConsulta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Consultas", description = "Agenda consultas")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
@RequestMapping("/consultas")
@RestController
public class ConsultaController {

    private final AgendamentoConsultaService agendamentoConsultaService;
    private final CancelamentoConsultaService cancelamentoConsultaService;

    @Operation(summary = "Agendar", description = "Este endpoint realiza o agendamento de uma consulta.")
    @PostMapping
    public ResponseEntity<DetalhesConsulta> agendar(@RequestBody @Valid DadosAgendamentoConsulta agendamentoConsulta, UriComponentsBuilder uriBuilder) {
        var novaConsulta = new DetalhesConsulta(agendamentoConsultaService.agendar(agendamentoConsulta));
        var uri = uriBuilder.path("/consultas/{id}").buildAndExpand(novaConsulta.id()).toUri();

        return ResponseEntity.created(uri).body(novaConsulta);
    }

    @Operation(summary = "Cancelar", description = "Este endpoint realiza o cancelamento de uma consulta.")
    @DeleteMapping
    public ResponseEntity<Void> cancelar(@RequestBody @Valid DadosCancelamentoConsulta cancelamentoConsulta) {
        cancelamentoConsultaService.cancelar(cancelamentoConsulta);

        return ResponseEntity.noContent().build();
    }
}
