package med.voll.api.domain.medico;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(@Schema(description = "Identificador único do médico", example = "4136", required = true)
                                     @NotNull
                                     Long id,
                                     @Schema(description = "Nome do médico", example = "Felipe Arruda")
                                     String nome,
                                     @Schema(description = "Telefone do médico", example = "3198550903")
                                     String telefone,
                                     DadosEndereco endereco) {
}
