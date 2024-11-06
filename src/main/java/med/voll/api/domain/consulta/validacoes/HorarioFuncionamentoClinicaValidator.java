package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.NegocioException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioFuncionamentoClinicaValidator implements AgendamentoConsultaValidator {

    public void validar(DadosAgendamentoConsulta agendamentoConsulta) {
        var dataHoraConsulta = agendamentoConsulta.dataHora();

        boolean isDomingo = dataHoraConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean isHorarioAntesAberturaClinica = dataHoraConsulta.getHour() < 7;
        boolean isHorarioAposAberturaClinica = dataHoraConsulta.getHour() > 18
                || (dataHoraConsulta.getHour() == 18 || (dataHoraConsulta.getMinute() != 0 && dataHoraConsulta.getSecond() != 0));

        if (isDomingo || isHorarioAntesAberturaClinica || isHorarioAposAberturaClinica) {
            throw new NegocioException("A consulta deve estar marcada dentro do horário de funcionamento da clínica, de segunda-feira ao sábado, das 7:00 às 19:00.");
        }
    }
}
