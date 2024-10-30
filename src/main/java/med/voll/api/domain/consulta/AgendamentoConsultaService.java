package med.voll.api.domain.consulta;

import lombok.RequiredArgsConstructor;
import med.voll.api.domain.consulta.validacoes.AgendamentoConsultaValidator;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.NegocioException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AgendamentoConsultaService {

    private final ConsultaRepository repository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final List<AgendamentoConsultaValidator> validadores;

    @Transactional
    public DetalhesConsulta agendar(DadosAgendamentoConsulta agendamentoConsulta) {
        var medico = medicoRepository.findById(agendamentoConsulta.idMedico())
                .orElseThrow(() -> new NegocioException("Médico não encontrado."));
        if (Boolean.FALSE.equals(medico.getAtivo())) {
            throw new NegocioException("O médico informado está inativo.");
        }

        var paciente = pacienteRepository.findById(agendamentoConsulta.idPaciente())
                .orElseThrow(() -> new NegocioException("Paciente não encontrado."));
        if (Boolean.FALSE.equals(paciente.getAtivo())) {
            throw new NegocioException("O paciente informado está inativo.");
        }

        validadores.forEach(v -> v.validar(agendamentoConsulta));
        var consulta = new Consulta(medico, paciente, agendamentoConsulta.dataHora());

        return new DetalhesConsulta(repository.save(consulta));
    }
}
