package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.NegocioException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioAntecedenciaAgendamentoValidator implements AgendamentoConsultaValidator {

    public void validar(DadosAgendamentoConsulta agendamentoConsulta) {
        var dataHoraConsulta = agendamentoConsulta.dataHora();
        var dataHoraAtual = LocalDateTime.now().withSecond(0).withNano(0);
        var diferencaEmMinutos = Duration.between(dataHoraAtual, dataHoraConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new NegocioException("A consulta deve ser agendada com antecedência mínima de 30 minutos.");
        }
    }
}
