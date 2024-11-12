package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.NegocioException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedicoComOutraConsultaNoMesmoHorarioValidatorTest {

    @InjectMocks
    private MedicoComOutraConsultaNoMesmoHorarioValidator validator;

    @Mock
    private ConsultaRepository consultaRepository;

    @Test
    void deveValidarComSucessoQuandoMedicoNaoPossuiHorarioAgendado() {
        var dadosAgendamentoConsulta = new DadosAgendamentoConsulta(1L, 1L, LocalDateTime.now());

        when(consultaRepository.existeConsultaNoMesmoHorario(any(), any(), any(), any())).thenReturn(false);

        assertDoesNotThrow(() -> validator.validar(dadosAgendamentoConsulta));
    }

    @Test
    void deveLancarNegocioExceptionQuandoMedicoPossuiHorarioAgendado() {
        var dadosAgendamentoConsulta = new DadosAgendamentoConsulta(1L, 1L, LocalDateTime.now());

        when(consultaRepository.existeConsultaNoMesmoHorario(any(), any(), any(), any())).thenReturn(true);

        var resultado = assertThrows(NegocioException.class, () -> validator.validar(dadosAgendamentoConsulta));

        assertEquals("Já existe uma consulta neste horário para o médico informado.", resultado.getMessage());
    }
}