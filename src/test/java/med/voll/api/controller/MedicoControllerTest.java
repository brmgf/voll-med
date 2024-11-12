package med.voll.api.controller;

import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.medico.DadosAtualizacaoMedico;
import med.voll.api.domain.medico.DadosCadastroMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoService;
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
class MedicoControllerTest {

    @InjectMocks
    private MedicoController controller;

    @Mock
    private MedicoService service;

    @Test
    void deveRetornarCreatedAoCadastrarMedicoComSucesso() {
        var endereco = new DadosEndereco("Rua X", "Centro", "3562000", "Abaeté", "MG", "1", "");
        var dadosCadastroMedico = new DadosCadastroMedico(null, null, null, null, null, endereco);
        var uriBuilder = UriComponentsBuilder.newInstance();

        when(service.salvar(any())).thenReturn(new Medico());

        var resultado = controller.cadastrar(dadosCadastroMedico, uriBuilder);

        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
    }

    @Test
    void deveRetornarListagemMedicosAtivosComPaginacao() {
        Pageable pageable = PageRequest.of(0, 10);
        var page = new PageImpl<>(List.of(new Medico()), pageable, 1);

        when(service.buscarTodosAtivosComPaginacao(pageable)).thenReturn(page);

        var response = controller.listar(pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deveRetornarDetalhesMedicoAoAtualizarComSucesso() {
        var dadosAtualizacaoMedico = new DadosAtualizacaoMedico(null, null, null, null);

        when(service.buscarPorId(any())).thenReturn(new Medico());

        var response = controller.atualizar(dadosAtualizacaoMedico);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deveRetornarNoContentAoInativarMedicoComSucesso() {
        when(service.buscarPorId(any())).thenReturn(new Medico());

        var resultado = controller.inativar(1L);

        assertEquals(HttpStatus.NO_CONTENT, resultado.getStatusCode());
    }

    @Test
    void deveRetornarStatusOKConsultarDetalhesMedico() {
        when(service.buscarPorId(any())).thenReturn(new Medico());

        var response = controller.detalhar(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}