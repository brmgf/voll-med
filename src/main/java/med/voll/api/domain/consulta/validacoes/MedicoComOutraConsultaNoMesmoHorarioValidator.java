package med.voll.api.domain.consulta.validacoes;

import lombok.RequiredArgsConstructor;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.NegocioException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MedicoComOutraConsultaNoMesmoHorarioValidator implements AgendamentoConsultaValidator {

    private final ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta agendamentoConsulta) {
        boolean existeConsultaNoHorario = consultaRepository.existeConsultaNoMesmoHorario(
                agendamentoConsulta.idMedico(),
                agendamentoConsulta.dataHora(),
                agendamentoConsulta.dataHora().minusHours(1),
                agendamentoConsulta.dataHora().plusHours(1));

        if (existeConsultaNoHorario) {
            throw new NegocioException("Já existe uma consulta neste horário para o médico informado.");
        }
    }
}
