package med.voll.api.controller;

import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.TokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutenticacaoControllerTest {

    @InjectMocks
    private AutenticacaoController controller;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @Test
    void deveRetornarStatusOKAoEfetuarLoginComSucesso() {
        var dadosAutenticacao = new DadosAutenticacao("admin", "123");
        var usuarioAutenticado = new Usuario();
        var authentication = new UsernamePasswordAuthenticationToken(usuarioAutenticado, null);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(tokenService.gerarToken(usuarioAutenticado)).thenReturn("fs4rt5t2vsfmfrtw94fgr3");

        var resposta = controller.efetuarLogin(dadosAutenticacao);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }
}