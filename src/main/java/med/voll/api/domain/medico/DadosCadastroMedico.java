package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(@NotBlank(message = "É obrigatório informar um nome.")
                                  String nome,
                                  @NotBlank(message = "É obrigatório informar um e-mail.")
                                  @Email(message = "E-mail inválido, por favor, tente novamente.")
                                  String email,
                                  @NotBlank(message = "É obrigatório informar o CRM.")
                                  @Pattern(regexp = "\\d{4,6}", message = "CRM inválido, por favor, tente novamente.")
                                  String crm,
                                  @NotNull(message = "É obrigatório informar uma especialidade.")
                                  Especialidade especialidade,
                                  @NotBlank(message = "É obrigatório informar um telefone.")
                                  String telefone,
                                  @Valid
                                  @NotNull
                                  DadosEndereco endereco) {
}
