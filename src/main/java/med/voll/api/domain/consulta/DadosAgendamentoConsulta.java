package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(@NotNull(message = "É obrigatório informar um médico.")
                                       Long idMedico,
                                       @NotNull(message = "É obrigatório informar um paciente.")
                                       Long idPaciente,
                                       @NotNull(message = "É obrigatório informar uma data e hora.")
                                       @Future(message = "A consulta deve ser agendada após a data e a hora atual.")
                                       LocalDateTime dataHora) {
}
