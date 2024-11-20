package med.voll.api.domain.paciente;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(@Schema(description = "Identificador único do paciente", example = "7683", required = true)
                                        @NotNull
                                        Long id,
                                        @Schema(description = "Nome do paciente", example = "Roberto Silva")
                                        String nome,
                                        @Schema(description = "Telefone do paciente", example = "3799880022")
                                        String telefone,
                                        DadosEndereco endereco) {
}
