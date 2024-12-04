package med.voll.api.controller.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import med.voll.api.domain.medico.DadosAtualizacaoMedico;
import med.voll.api.domain.medico.DadosCadastroMedico;
import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.medico.DetalhesMedico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Médicos", description = "Gerencia as operações de cadastramento e detalhamento de médicos")
public interface MedicoControllerOpenApi {

    @Operation(summary = "Listar", description = "Este endpoint lista os médicos ativos.")
    ResponseEntity<Page<DadosListagemMedico>> listar(Pageable pageable);
    @Operation(summary = "Detalhar", description = "Este endpoint busca os detalhes de um médico.")
    ResponseEntity<DetalhesMedico> detalhar(@Parameter(description = "Identificador único do médico", example = "4136") Long id);

    @Operation(summary = "Cadastrar", description = "Este endpoint realiza o cadastro de um médico.")
    ResponseEntity<DetalhesMedico> cadastrar(DadosCadastroMedico cadastroMedico, UriComponentsBuilder uriBuilder);

    @Operation(summary = "Atualizar", description = "Este endpoint realiza a atualização de um médico.")
    ResponseEntity<DetalhesMedico> atualizar(DadosAtualizacaoMedico dadosAtualizacaoMedico);

    @Operation(summary = "Inativar", description = "Este endpoint realiza a inativação de um médico.")
    ResponseEntity<Void> inativar(@Parameter(description = "Identificador único do médico", example = "4136") Long id);
}
