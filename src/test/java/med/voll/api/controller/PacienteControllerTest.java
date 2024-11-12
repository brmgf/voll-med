package med.voll.api.controller;

import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.paciente.DadosAtualizacaoPaciente;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PacienteControllerTest {

    @InjectMocks
    private PacienteController controller;

    @Mock
    private PacienteService service;

    @Test
    void deveRetornarCreatedAoCadastrarPacienteComSucesso() {
        var endereco = new DadosEndereco("Rua X", "Centro", "3562000", "Abaeté", "MG", "1", "");
        var dadosCadastroPaciente = new DadosCadastroPaciente(null, null, null, null, endereco);
        var uriBuilder = UriComponentsBuilder.newInstance();

        when(service.salvar(any())).thenReturn(new Paciente());

        var resultado = controller.cadastrar(dadosCadastroPaciente, uriBuilder);

        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
    }

    @Test
    void deveRetornarListagemPacientesAtivosComPaginacao() {
        Pageable pageable = PageRequest.of(0, 10);
        var page = new PageImpl<>(List.of(new Paciente()), pageable, 1);

        when(service.buscarTodosAtivosComPaginacao(pageable)).thenReturn(page);

        var response = controller.listar(pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deveRetornarDetalhesPacienteAoAtualizarComSucesso() {
        var dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(null, null, null, null);

        when(service.buscarPorId(any())).thenReturn(new Paciente());

        var response = controller.atualizar(dadosAtualizacaoPaciente);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deveRetornarNoContentAoInativarPacienteComSucesso() {
        when(service.buscarPorId(any())).thenReturn(new Paciente());

        var resultado = controller.inativar(1L);

        assertEquals(HttpStatus.NO_CONTENT, resultado.getStatusCode());
    }

    @Test
    void deveRetornarStatusOKConsultarDetalhesPaciente() {
        when(service.buscarPorId(any())).thenReturn(new Paciente());

        var response = controller.detalhar(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}