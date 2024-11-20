package med.voll.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.domain.medico.DadosAtualizacaoMedico;
import med.voll.api.domain.medico.DadosCadastroMedico;
import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.medico.DetalhesMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Médicos", description = "Gerencia as operações de cadastramento e detalhamento de médicos")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
@RequestMapping("/medicos")
@RestController
public class MedicoController {

    private final MedicoService service;

    @Operation(summary = "Listar", description = "Este endpoint lista os médicos ativos.")
    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(Pageable pageable) {
        Page<DadosListagemMedico> page = service.buscarTodosAtivosComPaginacao(pageable).map(DadosListagemMedico::new);

        return ResponseEntity.ok(page);
    }

    @Operation(summary = "Detalhar", description = "Este endpoint busca os detalhes de um médico.")
    @GetMapping("/{id}")
    public ResponseEntity<DetalhesMedico> detalhar(@Parameter(description = "Identificador único do médico", example = "4136") @PathVariable Long id) {
        var medico = service.buscarPorId(id);

        return ResponseEntity.ok(new DetalhesMedico(medico));
    }

    @Operation(summary = "Cadastrar", description = "Este endpoint realiza o cadastro de um médico.")
    @PostMapping
    public ResponseEntity<DetalhesMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico cadastroMedico, UriComponentsBuilder uriBuilder) {
        var novoMedico = service.salvar(new Medico(cadastroMedico));
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(novoMedico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhesMedico(novoMedico));
    }

    @Operation(summary = "Atualizar", description = "Este endpoint realiza a atualização de um médico.")
    @PutMapping
    @Transactional
    public ResponseEntity<DetalhesMedico> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dadosAtualizacaoMedico) {
        var medico = service.buscarPorId(dadosAtualizacaoMedico.id());
        medico.atualizarInformacoes(dadosAtualizacaoMedico);

        return ResponseEntity.ok(new DetalhesMedico(medico));
    }

    @Operation(summary = "Inativar", description = "Este endpoint realiza a inativação de um médico.")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> inativar(@Parameter(description = "Identificador único do médico", example = "4136") @PathVariable Long id) {
        var medico = service.buscarPorId(id);
        medico.inativar();

        return ResponseEntity.noContent().build();
    }
}
