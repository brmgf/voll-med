package med.voll.api.domain.consulta;

import lombok.RequiredArgsConstructor;
import med.voll.api.domain.consulta.validacoes.AgendamentoConsultaValidator;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.medico.MedicoService;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.domain.paciente.PacienteService;
import med.voll.api.infra.exception.NegocioException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AgendamentoConsultaService {

    private final ConsultaRepository repository;
    private final MedicoService medicoService;
    private final PacienteService pacienteService;
    private final List<AgendamentoConsultaValidator> validadores;

    @Transactional
    public DetalhesConsulta agendar(DadosAgendamentoConsulta agendamentoConsulta) {
        var medico = medicoService.buscarAtivoPorId(agendamentoConsulta.idMedico());
        var paciente = pacienteService.buscarAtivoPorId(agendamentoConsulta.idPaciente());

        validadores.forEach(v -> v.validar(agendamentoConsulta));
        var consulta = new Consulta(medico, paciente, agendamentoConsulta.dataHora());

        return new DetalhesConsulta(repository.save(consulta));
    }
}
