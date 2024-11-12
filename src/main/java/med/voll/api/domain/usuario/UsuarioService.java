package med.voll.api.domain.usuario;

import lombok.RequiredArgsConstructor;
import med.voll.api.infra.exception.NegocioException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        var usuarioCadastrado = repository.findByLogin(usuario.getLogin());
        if (Objects.nonNull(usuarioCadastrado)) {
            throw new NegocioException("Esse usuário já está cadastrado no sistema.");
        }

        return repository.save(usuario);
    }
}
