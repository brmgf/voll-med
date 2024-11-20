package med.voll.api.domain.consulta;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(@Schema(description = "Identificador único do médico", example = "4136", required = true)
                                       @NotNull(message = "É obrigatório informar um médico.")
                                       Long idMedico,
                                       @Schema(description = "Identificador único do paciente", example = "7683", required = true)
                                       @NotNull(message = "É obrigatório informar um paciente.")
                                       Long idPaciente,
                                       @Schema(description = "Data e hora da consulta", example = "2024-11-20T16:00:00", required = true)
                                       @NotNull(message = "É obrigatório informar uma data e hora.")
                                       @Future(message = "A consulta deve ser agendada após a data e a hora atual.")
                                       LocalDateTime dataHora) {
}
