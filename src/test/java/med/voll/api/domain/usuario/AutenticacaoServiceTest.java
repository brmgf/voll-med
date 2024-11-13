package med.voll.api.domain.usuario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutenticacaoServiceTest {

    @InjectMocks
    private AutenticacaoService service;

    @Mock
    private UsuarioRepository repository;

    @Test
    void deveCarregarUsuarioPeloLoginComSucesso() {
        var userDetails = getUserDetails();

        when(repository.findByLogin(any())).thenReturn(userDetails);

        var resultado = service.loadUserByUsername("admin");

        assertNotNull(resultado);
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