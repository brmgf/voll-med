package med.voll.api.domain.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(@Schema(description = "Login do usuário", example = "joao@vollmed", required = true)
                                @NotBlank
                                String login,
                                @Schema(description = "Senha do usuário", example = "123456")
                                @NotBlank
                                String senha) {
}
