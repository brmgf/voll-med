package med.voll.api.controller;

import med.voll.api.domain.consulta.AgendamentoConsultaService;
import med.voll.api.domain.consulta.CancelamentoConsultaService;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultaControllerTest {

    @InjectMocks
    private ConsultaController controller;

    @Mock
    private AgendamentoConsultaService agendamentoConsultaService;

    @Mock
    private CancelamentoConsultaService cancelamentoConsultaService;

    @Test
    void deveRetornarCreatedAoAgendarComSucesso() {
        var dadosAgendamentoConsulta = new DadosAgendamentoConsulta(1L, 2L, LocalDateTime.now());
        var consultaSalva = new Consulta(1L, Medico.builder().id(1L).build(), Paciente.builder().id(1L).build(), null, null, null);
        var uriBuilder = UriComponentsBuilder.newInstance();

        when(agendamentoConsultaService.agendar(dadosAgendamentoConsulta)).thenReturn(consultaSalva);

        var resultado = controller.agendar(dadosAgendamentoConsulta, uriBuilder);

        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
    }

    @Test
    void deveRetornarNoContentAoCancelarConsultaComSucesso() {
        var dadosCancelamentoConsulta = new DadosCancelamentoConsulta(1L, "Motivos pessoais.");

        var resultado = controller.cancelar(dadosCancelamentoConsulta);

        verify(cancelamentoConsultaService, times(1)).cancelar(dadosCancelamentoConsulta);

        assertEquals(HttpStatus.NO_CONTENT, resultado.getStatusCode());
    }
}