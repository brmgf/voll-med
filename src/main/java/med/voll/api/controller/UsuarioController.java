package med.voll.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.domain.usuario.DadosCadastroUsuario;
import med.voll.api.domain.usuario.DetalhesUsuario;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.domain.usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Usuários", description = "Gerencia a operação de cadastro do usuário")
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @Operation(summary = "Cadastrar", description = "Este endpoint realiza o cadastro de um usuário.")
    @PostMapping
    public ResponseEntity<DetalhesUsuario> cadastrar(@RequestBody @Valid DadosCadastroUsuario cadastroUsuario, UriComponentsBuilder uriBuilder) {
        var novoUsuario = service.salvar(new Usuario(cadastroUsuario));
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(novoUsuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhesUsuario(novoUsuario));
    }
}
