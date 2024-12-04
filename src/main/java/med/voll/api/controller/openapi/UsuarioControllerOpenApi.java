package med.voll.api.controller.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import med.voll.api.domain.usuario.DadosCadastroUsuario;
import med.voll.api.domain.usuario.DetalhesUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Usuários", description = "Gerencia o cadastro do usuário")
public interface UsuarioControllerOpenApi {

    @Operation(summary = "Cadastrar", description = "Este endpoint realiza o cadastro de um usuário.")
    ResponseEntity<DetalhesUsuario> cadastrar(DadosCadastroUsuario cadastroUsuario, UriComponentsBuilder uriBuilder);
}
