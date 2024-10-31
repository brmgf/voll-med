package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.domain.paciente.DadosAtualizacaoPaciente;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.DadosListagemPaciente;
import med.voll.api.domain.paciente.DetalhesPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/pacientes")
@RestController
public class PacienteController {

    private final PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesPaciente> cadastrar(@RequestBody @Valid DadosCadastroPaciente cadastroPaciente, UriComponentsBuilder uriBuilder) {
        var novoPaciente = repository.save(new Paciente(cadastroPaciente));
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(novoPaciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhesPaciente(novoPaciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(size = 2) Pageable pageable) {
        Page<DadosListagemPaciente> page = repository.findAllByAtivoTrue(pageable).map(DadosListagemPaciente::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhesPaciente> atualizar(@RequestBody @Valid DadosAtualizacaoPaciente atualizacaoPaciente) {
        var paciente = repository.getReferenceById(atualizacaoPaciente.id());
        paciente.atualizarInformacoes(atualizacaoPaciente);

        return ResponseEntity.ok(new DetalhesPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity inativar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.inativar();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesPaciente> detalhar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);

        return ResponseEntity.ok(new DetalhesPaciente(paciente));
    }
}
