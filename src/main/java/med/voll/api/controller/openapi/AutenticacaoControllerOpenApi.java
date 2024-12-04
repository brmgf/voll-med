package med.voll.api.controller.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.infra.security.DadosTokenJWT;
import org.springframework.http.ResponseEntity;

@Tag(name = "Autenticação", description = "Gerencia o login no sistema")
public interface AutenticacaoControllerOpenApi {

    @Operation(summary = "Efetuar login", description = "Este endpoint efetua o login do usuário no sistema e retorna um token para autenticar as demais operações.")
    ResponseEntity<DadosTokenJWT> efetuarLogin(DadosAutenticacao dados);
}
