package med.voll.api.domain.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

public record DetalhesUsuario(@Schema(example = "5421")
                              Long id,
                              @Schema(example = "carla.vaz")
                              String login) {

    public DetalhesUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin());
    }
}
