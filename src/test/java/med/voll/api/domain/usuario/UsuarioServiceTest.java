package med.voll.api.domain.usuario;

import med.voll.api.infra.exception.NegocioException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService service;

    @Mock
    private UsuarioRepository repository;

    @Test
    void deveRetornarUsuarioAoSalvarComSucesso() {
        var usuario = new Usuario(1L, "admin", "123");

        when(repository.save(any())).thenReturn(usuario);

        var resultado = service.salvar(new Usuario());

        assertNotNull(resultado);
    }

    @Test
    void deveLancarExceptionQuandoUsuarioJaEstaCadastrado() {
        var usuario = new Usuario(1L, "admin", "123");
        var userDetails = getUserDetails();

        when(repository.findByLogin(any())).thenReturn(userDetails);

        var resultado = assertThrows(NegocioException.class, () -> service.salvar(usuario));

        assertEquals("Esse usuário já está cadastrado no sistema.", resultado.getMessage());
    }

    UserDetails getUserDetails() {
        return new UserDetails() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return "123";
            }

            @Override
            public String getUsername() {
                return "admin";
            }
        };
    }
}