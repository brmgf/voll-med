package med.voll.api.domain.consulta;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosCancelamentoConsulta(@Schema(description = "Identificador único da consulta", example = "5409", required = true)
                                        @NotNull
                                        Long idConsulta,
                                        @Schema(description = "Descrição do motivo do cancelamento", example = "Motivos pessoais.", required = true)
                                        @NotBlank(message = "É obrigatório informar o motivo do cancelamento.")
                                        @Size(max = 255)
                                        String motivoCancelamento) {
}
