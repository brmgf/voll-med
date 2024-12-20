package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.controller.openapi.MedicoControllerOpenApi;
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

@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
@RequestMapping("/medicos")
@RestController
public class MedicoController implements MedicoControllerOpenApi {

    private final MedicoService service;

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(Pageable pageable) {
        Page<DadosListagemMedico> page = service.buscarTodosAtivosComPaginacao(pageable).map(DadosListagemMedico::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesMedico> detalhar(@PathVariable Long id) {
        var medico = service.buscarPorId(id);

        return ResponseEntity.ok(new DetalhesMedico(medico));
    }

    @PostMapping
    public ResponseEntity<DetalhesMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico cadastroMedico, UriComponentsBuilder uriBuilder) {
        var novoMedico = service.salvar(new Medico(cadastroMedico));
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(novoMedico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhesMedico(novoMedico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhesMedico> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dadosAtualizacaoMedico) {
        var medico = service.buscarPorId(dadosAtualizacaoMedico.id());
        medico.atualizarInformacoes(dadosAtualizacaoMedico);

        return ResponseEntity.ok(new DetalhesMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        var medico = service.buscarPorId(id);
        medico.inativar();

        return ResponseEntity.noContent().build();
    }
}
