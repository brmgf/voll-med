package med.voll.api.domain.consulta;

import lombok.RequiredArgsConstructor;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AgendamentoConsultaService {

    private final ConsultaRepository repository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    @Transactional
    public void agendar(DadosAgendamentoConsulta agendamentoConsulta) {
        var medico = medicoRepository.findById(agendamentoConsulta.idMedico())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado."));
        var paciente = pacienteRepository.findById(agendamentoConsulta.idPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado."));
        var consulta = new Consulta(null, medico, paciente, agendamentoConsulta.dataHora());

        repository.save(consulta);
    }
}
