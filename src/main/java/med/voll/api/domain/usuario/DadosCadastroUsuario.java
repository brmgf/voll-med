package med.voll.api.domain.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(@Schema(description = "Login do usuário", example = "carla.vaz", required = true)
                                    @NotBlank(message = "É obrigatório informar um login.")
                                    String login,
                                    @Schema(description = "Senha do usuário", example = "123456", required = true)
                                    @NotBlank(message = "É obrigatório informar uma senha.")
                                    String senha) {
}
