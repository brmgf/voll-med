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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController controller;

    @Mock
    private UsuarioService service;

    @Test
    void deveRetornarCreatedAoCadastrarUsuarioComSucesso() {
        var dto = new DadosCadastroUsuario("admin", "123");
        var usuarioSalvo = new Usuario(1L, "admin", "$3jd@4@cjk#3f");
        var uriBuilder = UriComponentsBuilder.newInstance();

        when(service.salvar(any())).thenReturn(usuarioSalvo);

        var resultado = controller.cadastrar(dto, uriBuilder);

        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
    }
}