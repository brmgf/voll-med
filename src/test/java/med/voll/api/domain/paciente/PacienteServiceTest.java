package med.voll.api.domain.paciente;

import med.voll.api.infra.exception.NegocioException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

    @InjectMocks
    private PacienteService service;

    @Mock
    private PacienteRepository repository;

    @Test
    void deveSalvarPacienteComSucesso() {
        when(repository.save(any())).thenReturn(Paciente.builder().id(1L).build());

        var resultado = service.salvar(new Paciente());

        assertNotNull(resultado);
    }

    @Test
    void deveListarTodosPacientesAtivosComPaginacao() {
        Pageable pageable = PageRequest.of(0, 10);
        var page = new PageImpl<>(List.of(new Paciente()), pageable, 1);

        when(repository.findAllByAtivoTrue(any())).thenReturn(page);

        var resultado = service.buscarTodosAtivosComPaginacao(pageable);

        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
    }

    @Test
    void deveRetornarPacienteAoBuscarPorIdComSucesso() {
        when(repository.findById(any())).thenReturn(Optional.of(new Paciente()));

        var resultado = service.buscarPorId(1L);

        assertNotNull(resultado);
    }

    @Test
    void deveLancarNegocioExceptionAoBuscarPacienteInexistente() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        var resultado = assertThrows(NegocioException.class, () -> service.buscarPorId(1L));

        assertEquals("Paciente não encontrado.", resultado.getMessage());
    }

    @Test
    void deveRetornarPacienteAoBuscarAtivoPorIdComSucesso() {
        when(repository.findById(any())).thenReturn(Optional.of(Paciente.builder().ativo(true).build()));

        var resultado = service.buscarAtivoPorId(1L);

        assertNotNull(resultado);
    }

    @Test
    void deveLancarNegocioExceptionAoBuscarAtivoQuandoPacienteInexistente() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        var resultado = assertThrows(NegocioException.class, () -> service.buscarAtivoPorId(1L));

        assertEquals("Paciente não encontrado.", resultado.getMessage());
    }

    @Test
    void deveLancarNegocioExceptionAoBuscarAtivoQuandoPacienteInativo() {
        when(repository.findById(any())).thenReturn(Optional.of(Paciente.builder().ativo(false).build()));

        var resultado = assertThrows(NegocioException.class, () -> service.buscarAtivoPorId(1L));

        assertEquals("O paciente informado está inativo.", resultado.getMessage());
    }
}