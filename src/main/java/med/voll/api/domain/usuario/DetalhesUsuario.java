package med.voll.api.domain.usuario;

public record DetalhesUsuario(Long id, String login) {

    public DetalhesUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin());
    }
}
