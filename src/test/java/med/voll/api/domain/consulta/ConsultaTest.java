package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class ConsultaTest {

    private Medico medico;
    private Paciente paciente;
    private LocalDateTime dataHora;

    @BeforeEach
    public void setUp() {
        medico = mock(Medico.class);
        paciente = mock(Paciente.class);
        dataHora = LocalDateTime.of(2024, 11, 12, 10, 0);
    }

    @Test
    void deveTestarConstrutorComMedicoPacienteEDataHora() {
        var resultado = new Consulta(medico, paciente, dataHora);

        assertEquals(medico, resultado.getMedico());
        assertEquals(paciente, resultado.getPaciente());
        assertEquals(dataHora, resultado.getDataHora());
        assertEquals(StatusConsulta.ABERTA, resultado.getStatus());
    }

    @Test
    void devePreencherMotivoCancelamentoEAlterarStatusCancelamento() {
        var consulta = new Consulta(medico, paciente, dataHora);

        consulta.cancelar("Motivos pessoais.");

        assertEquals("Motivos pessoais.", consulta.getMotivoCancelamento());
        assertEquals(StatusConsulta.CANCELADA, consulta.getStatus());
    }
}