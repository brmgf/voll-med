package med.voll.api.domain.usuario;

public record DetalhesUsuario(String login) {

    public DetalhesUsuario(Usuario usuario) {
        this(usuario.getLogin());
    }
}
