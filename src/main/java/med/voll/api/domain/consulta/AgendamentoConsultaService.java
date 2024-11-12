package med.voll.api.domain.consulta;

import lombok.RequiredArgsConstructor;
import med.voll.api.domain.consulta.validacoes.AgendamentoConsultaValidator;
import med.voll.api.domain.medico.MedicoService;
import med.voll.api.domain.paciente.PacienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AgendamentoConsultaService {

    private final MedicoService medicoService;
    private final PacienteService pacienteService;
    private final List<AgendamentoConsultaValidator> validadores;
    private final ConsultaRepository repository;

    @Transactional
    public Consulta agendar(DadosAgendamentoConsulta agendamentoConsulta) {
        var medico = medicoService.buscarAtivoPorId(agendamentoConsulta.idMedico());
        var paciente = pacienteService.buscarAtivoPorId(agendamentoConsulta.idPaciente());

        validadores.forEach(v -> v.validar(agendamentoConsulta));
        var consulta = new Consulta(medico, paciente, agendamentoConsulta.dataHora());

        return repository.save(consulta);
    }
}
