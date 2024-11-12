package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
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
class HorarioAntecedenciaCancelamentoValidatorTest {

    @InjectMocks
    private HorarioAntecedenciaCancelamentoValidator validator;

    @Mock
    private ConsultaRepository consultaRepository;

    @Test
    void deveValidarComSucessoQuandoHorarioCancelamentoAntecede24HorasDaDataHoraAtual() {
        var dadosCancelamentoConsulta = new DadosCancelamentoConsulta(1L, "Motivos pessoais.");
        var consulta = new Consulta();
        consulta.setDataHora(LocalDateTime.now().plusHours(25));

        when(consultaRepository.getReferenceById(any())).thenReturn(consulta);

        assertDoesNotThrow(() -> validator.validar(dadosCancelamentoConsulta));
    }

    @Test
    void deveLancarNegocioExceptionQuandoHorarioCancelamentoNaoAntecede24HorasDaDataHoraAtual() {
        var dadosCancelamentoConsulta = new DadosCancelamentoConsulta(1L, "Motivos pessoais.");
        var consulta = new Consulta();
        consulta.setDataHora(LocalDateTime.now().plusHours(2));

        when(consultaRepository.getReferenceById(any())).thenReturn(consulta);

        var resultado = assertThrows(NegocioException.class, () -> validator.validar(dadosCancelamentoConsulta));

        assertEquals("A consulta deve ser cancelada com antecedência mínima de 24 horas.", resultado.getMessage());
    }
}