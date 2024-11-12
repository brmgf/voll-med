package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.validacoes.AgendamentoConsultaValidator;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoService;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgendamentoConsultaServiceTest {

    @InjectMocks
    private AgendamentoConsultaService service;

    @Mock
    private MedicoService medicoService;

    @Mock
    private PacienteService pacienteService;

    @Mock
    @SuppressWarnings("unused")
    private List<AgendamentoConsultaValidator> validadores;

    @Mock
    private ConsultaRepository repository;

    @Test
    void deveSalvarConsultaAoAgendarComSucesso() {
        var dadosAgendamentoConsulta = new DadosAgendamentoConsulta(1L, 1L, LocalDateTime.now());

        when(medicoService.buscarAtivoPorId(any())).thenReturn(new Medico());
        when(pacienteService.buscarAtivoPorId(any())).thenReturn(new Paciente());

        service.agendar(dadosAgendamentoConsulta);

        verify(repository, times(1)).save(any());
    }
}