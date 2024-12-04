package med.voll.api.controller.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import med.voll.api.domain.paciente.DadosAtualizacaoPaciente;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.DadosListagemPaciente;
import med.voll.api.domain.paciente.DetalhesPaciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Pacientes", description = "Gerencia as operações de cadastramento e detalhamento de pacientes")
public interface PacienteControllerOpenApi {

    @Operation(summary = "Listar", description = "Este endpoint lista os pacientes ativos.")
    ResponseEntity<Page<DadosListagemPaciente>> listar(Pageable pageable);

    @Operation(summary = "Detalhar", description = "Este endpoint busca os detalhes de um paciente.")
    ResponseEntity<DetalhesPaciente> detalhar(@Parameter(description = "Identificador único do paciente", example = "7683") Long id);

    @Operation(summary = "Cadastrar", description = "Este endpoint realiza o cadastro de um paciente.")
    ResponseEntity<DetalhesPaciente> cadastrar(DadosCadastroPaciente cadastroPaciente, UriComponentsBuilder uriBuilder);

    @Operation(summary = "Atualizar", description = "Este endpoint realiza a atualização de um paciente.")
    ResponseEntity<DetalhesPaciente> atualizar(DadosAtualizacaoPaciente atualizacaoPaciente);

    @Operation(summary = "Inativar", description = "Este endpoint realiza a inativação de um paciente.")
    ResponseEntity<Void> inativar(@Parameter(description = "Identificador único do paciente", example = "7683") Long id);
}
