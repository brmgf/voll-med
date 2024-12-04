package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.controller.openapi.ConsultaControllerOpenApi;
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

@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
@RequestMapping("/consultas")
@RestController
public class ConsultaController implements ConsultaControllerOpenApi {

    private final AgendamentoConsultaService agendamentoConsultaService;
    private final CancelamentoConsultaService cancelamentoConsultaService;

    @PostMapping
    public ResponseEntity<DetalhesConsulta> agendar(@RequestBody @Valid DadosAgendamentoConsulta agendamentoConsulta, UriComponentsBuilder uriBuilder) {
        var novaConsulta = new DetalhesConsulta(agendamentoConsultaService.agendar(agendamentoConsulta));
        var uri = uriBuilder.path("/consultas/{id}").buildAndExpand(novaConsulta.id()).toUri();

        return ResponseEntity.created(uri).body(novaConsulta);
    }

    @DeleteMapping
    public ResponseEntity<Void> cancelar(@RequestBody @Valid DadosCancelamentoConsulta cancelamentoConsulta) {
        cancelamentoConsultaService.cancelar(cancelamentoConsulta);

        return ResponseEntity.noContent().build();
    }
}
