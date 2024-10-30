package med.voll.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.domain.consulta.AgendamentoConsultaService;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/consultas")
@RestController
public class ConsultaController {

    private final AgendamentoConsultaService agendamentoConsultaService;

    @PostMapping
    public void agendar(@RequestBody @Valid DadosAgendamentoConsulta agendamentoConsulta) {
        agendamentoConsultaService.agendar(agendamentoConsulta);
    }
}
