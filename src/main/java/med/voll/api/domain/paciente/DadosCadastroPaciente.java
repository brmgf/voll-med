package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroPaciente(@NotBlank(message = "É obrigatório informar um nome.")
                                    String nome,
                                    @NotBlank(message = "É obrigatório informar um e-mail.")
                                    @Email(message = "E-mail inválido, por favor, tente novamente.")
                                    String email,
                                    @NotBlank(message = "É obrigatório informar um telefone.")
                                    String telefone,
                                    @NotBlank(message = "É obrigatório informar o cpf.")
                                    String cpf,
                                    @Valid
                                    @NotNull
                                    DadosEndereco endereco) {
}
