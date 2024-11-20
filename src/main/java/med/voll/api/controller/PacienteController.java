package med.voll.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.domain.paciente.DadosAtualizacaoPaciente;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.DadosListagemPaciente;
import med.voll.api.domain.paciente.DetalhesPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Pacientes", description = "Gerencia as operações de cadastramento e detalhamento de pacientes")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
@RequestMapping("/pacientes")
@RestController
public class PacienteController {

    private final PacienteService service;

    @Operation(summary = "Listar", description = "Este endpoint lista os pacientes ativos.")
    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar(Pageable pageable) {
        Page<DadosListagemPaciente> page = service.buscarTodosAtivosComPaginacao(pageable)
                .map(DadosListagemPaciente::new);

        return ResponseEntity.ok(page);
    }

    @Operation(summary = "Detalhar", description = "Este endpoint busca os detalhes de um paciente.")
    @GetMapping("/{id}")
    public ResponseEntity<DetalhesPaciente> detalhar(@Parameter(description = "Identificador único do paciente", example = "7683") @PathVariable Long id) {
        var paciente = service.buscarPorId(id);

        return ResponseEntity.ok(new DetalhesPaciente(paciente));
    }

    @Operation(summary = "Cadastrar", description = "Este endpoint realiza o cadastro de um paciente.")
    @PostMapping
    public ResponseEntity<DetalhesPaciente> cadastrar(@RequestBody @Valid DadosCadastroPaciente cadastroPaciente, UriComponentsBuilder uriBuilder) {
        var novoPaciente = service.salvar(new Paciente(cadastroPaciente));
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(novoPaciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhesPaciente(novoPaciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhesPaciente> atualizar(@RequestBody @Valid DadosAtualizacaoPaciente atualizacaoPaciente) {
        var paciente = service.buscarPorId(atualizacaoPaciente.id());
        paciente.atualizarInformacoes(atualizacaoPaciente);

        return ResponseEntity.ok(new DetalhesPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> inativar(@Parameter(description = "Identificador único do paciente", example = "7683") @PathVariable Long id) {
        var paciente = service.buscarPorId(id);
        paciente.inativar();

        return ResponseEntity.noContent().build();
    }
}
