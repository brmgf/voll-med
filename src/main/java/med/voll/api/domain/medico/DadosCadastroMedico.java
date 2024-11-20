package med.voll.api.domain.medico;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(@Schema(description = "Nome do médico", example = "Felipe Arruda", required = true)
                                  @NotBlank(message = "É obrigatório informar um nome.")
                                  String nome,
                                  @Schema(description = "E-mail do médico", example = "felipe@vollmed.com", required = true)
                                  @NotBlank(message = "É obrigatório informar um e-mail.")
                                  @Email(message = "E-mail inválido, por favor, tente novamente.")
                                  String email,
                                  @Schema(description = "CRM do médico", example = "905723", required = true)
                                  @NotBlank(message = "É obrigatório informar o CRM.")
                                  @Pattern(regexp = "\\d{4,6}", message = "CRM inválido, por favor, tente novamente.")
                                  String crm,
                                  @Schema(description = "Especialidade do médico", example = "CARDIOLOGIA", required = true)
                                  @NotNull(message = "É obrigatório informar uma especialidade (ORTOPEDIA/CARDIOLOGIA/GINECOLOGIA/DERMATOLOGIA).")
                                  Especialidade especialidade,
                                  @Schema(description = "Telefone do médico", example = "3198550903", required = true)
                                  @NotBlank(message = "É obrigatório informar um telefone.")
                                  String telefone,
                                  @Valid
                                  @NotNull
                                  DadosEndereco endereco) {
}
