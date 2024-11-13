package med.voll.api.domain.usuario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UsuarioTest {

    @Test
    void deveTestarConstrutorComDadosCadastroUsuario() {
        var cadastroUsuario = new DadosCadastroUsuario("admin", "123");

        var resultado = new Usuario(cadastroUsuario);

        assertEquals(cadastroUsuario.login(), resultado.getLogin());
    }
}