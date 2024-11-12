package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.NegocioException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class HorarioFuncionamentoClinicaValidatorTest {

    @InjectMocks
    private HorarioFuncionamentoClinicaValidator validator;

    @Test
    void deveValidarComSucessoQuandoHorarioConsultaEstaDentroDoHorarioFuncionamentoClinica() {
        var dadosAgendamentoConsulta = new DadosAgendamentoConsulta(1L, 2L, LocalDateTime.of(2024, 11, 11, 15, 0));

        assertDoesNotThrow(() -> validator.validar(dadosAgendamentoConsulta));
    }

    @ParameterizedTest
    @MethodSource("horariosInvalidos")
    void deveLancarNegocioExceptionParaHorariosInvalidos(LocalDateTime dataConsulta) {
        var dadosAgendamentoConsulta = new DadosAgendamentoConsulta(1L, 2L, dataConsulta);

        var resultado = assertThrows(NegocioException.class, () -> validator.validar(dadosAgendamentoConsulta));

        assertEquals("A consulta deve estar marcada dentro do horário de funcionamento da clínica, de segunda-feira ao sábado, das 7:00 às 19:00.", resultado.getMessage());
    }

    private static Stream<LocalDateTime> horariosInvalidos() {
        return Stream.of(
                LocalDateTime.of(2024, 11, 11, 6, 0),  // Antes da abertura
                LocalDateTime.of(2024, 11, 11, 19, 0), // Após o fechamento
                LocalDateTime.of(2024, 11, 10, 15, 0)  // Domingo
        );
    }
}