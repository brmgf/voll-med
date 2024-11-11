package med.voll.api.controller;

import med.voll.api.domain.usuario.DadosCadastroUsuario;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.domain.usuario.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController controller;

    @Mock
    private UsuarioService service;

    @Test
    void deveRetornarCreatedAoCadastrarUsuarioComSucesso() {
        var dto = new DadosCadastroUsuario("admin", "123");
        var uriBuilder = UriComponentsBuilder.newInstance();
        var usuarioSalvo = new Usuario(1L, "admin", "123");

        var resultado = controller.cadastrar(dto, uriBuilder);

        verify(service).salvar(any());

        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
        assertEquals("/usuarios/1", resultado.getHeaders().getLocation().getPath());
        assertEquals(usuarioSalvo.getId(), resultado.getBody().id());
    }

    @Test
    void naoDeveRetornarCreatedAoCadastrarUsuarioComErro() {
        var dto = new DadosCadastroUsuario("admin", "123");
        var uriBuilder = UriComponentsBuilder.newInstance();

        doThrow(new RuntimeException("Ocorreu um erro ao salvar usuário.")).when(service).salvar(any());

        var resultado = assertThrows(RuntimeException.class, () -> controller.cadastrar(dto, uriBuilder));

        assertEquals("Ocorreu um erro ao salvar usuário.", resultado.getMessage());
    }
}