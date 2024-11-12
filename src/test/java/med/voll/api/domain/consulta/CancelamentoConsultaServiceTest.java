package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.validacoes.CancelamentoConsultaValidator;
import med.voll.api.infra.exception.NegocioException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CancelamentoConsultaServiceTest {

    @InjectMocks
    private CancelamentoConsultaService service;

    @Mock
    @SuppressWarnings("unused")
    private List<CancelamentoConsultaValidator> validadores;

    @Mock
    private ConsultaRepository repository;

    @Test
    void deveCancelarConsultaComSucesso() {
        var dadosCancelamentoConsulta = new DadosCancelamentoConsulta(1L, "Motivos pessoais.");
        var consulta = mock(Consulta.class);

        when(repository.findById(any())).thenReturn(Optional.of(consulta));

        service.cancelar(dadosCancelamentoConsulta);

        verify(consulta, times(1)).cancelar("Motivos pessoais.");
    }

    @Test
    void deveLancarExceptionAoTentarCancelarConsultaNaoEncontrada() {
        var dadosCancelamentoConsulta = new DadosCancelamentoConsulta(1L, "Motivos pessoais.");

        when(repository.findById(any())).thenReturn(Optional.empty());

        var resultado = assertThrows(NegocioException.class, () -> service.cancelar(dadosCancelamentoConsulta));

        assertEquals("Consulta não encontrada.", resultado.getMessage());
    }
}