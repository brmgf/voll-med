package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.NegocioException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class HorarioAntecedenciaAgendamentoValidatorTest {

    @InjectMocks
    private HorarioAntecedenciaAgendamentoValidator validator;

    @Test
    void deveValidarComSucessoQuandoHorarioConsultaAntecede30MinutosDaDataHoraAtual() {
        var dadosAgendamentoConsulta = new DadosAgendamentoConsulta(1L, 1L, LocalDateTime.now().plusHours(1));

        assertDoesNotThrow(() -> validator.validar(dadosAgendamentoConsulta));
    }

    @Test
    void deveLancarNegocioExceptionQuandoHorarioConsultaNaoAntecede30MinutosDaDataHoraAtual() {
        var dadosAgendamentoConsulta = new DadosAgendamentoConsulta(1L, 1L, LocalDateTime.now());

        var resultado = assertThrows(NegocioException.class, () -> validator.validar(dadosAgendamentoConsulta));

        assertEquals("A consulta deve ser agendada com antecedência mínima de 30 minutos.", resultado.getMessage());
    }
}