package med.voll.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(@NotBlank(message = "É obrigatório informar um login.")
                                   String login,
                                   @NotBlank(message = "É obrigatório informar uma senha.")
                                   String senha) {
}
