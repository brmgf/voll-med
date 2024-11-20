package med.voll.api.domain.paciente;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroPaciente(@Schema(description = "Nome do paciente", example = "Roberto Silva", required = true)
                                    @NotBlank(message = "É obrigatório informar um nome.")
                                    String nome,
                                    @Schema(description = "E-mail do paciente", example = "roberto@gmail.com", required = true)
                                    @NotBlank(message = "É obrigatório informar um e-mail.")
                                    @Email(message = "E-mail inválido, por favor, tente novamente.")
                                    String email,
                                    @Schema(description = "Telefone do paciente", example = "3799880022", required = true)
                                    @NotBlank(message = "É obrigatório informar um telefone.")
                                    String telefone,
                                    @Schema(description = "CPF do paciente", example = "26378580014", required = true)
                                    @NotBlank(message = "É obrigatório informar o cpf.")
                                    String cpf,
                                    @Valid
                                    @NotNull
                                    DadosEndereco endereco) {
}
