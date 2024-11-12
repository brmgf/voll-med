package med.voll.api.domain.medico;

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
class MedicoServiceTest {

    @InjectMocks
    private MedicoService service;

    @Mock
    private MedicoRepository repository;

    @Test
    void deveSalvarMedicoComSucesso() {
        when(repository.save(any())).thenReturn(Medico.builder().id(1L).build());

        var resultado = service.salvar(new Medico());

        assertNotNull(resultado);
    }

    @Test
    void deveListarTodosMedicosAtivosComPaginacao() {
        Pageable pageable = PageRequest.of(0, 10);
        var page = new PageImpl<>(List.of(new Medico()), pageable, 1);

        when(repository.findAllByAtivoTrue(any())).thenReturn(page);

        var resultado = service.buscarTodosAtivosComPaginacao(pageable);

        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
    }

    @Test
    void deveRetornarMedicoAoBuscarPorIdComSucesso() {
        when(repository.findById(any())).thenReturn(Optional.of(new Medico()));

        var resultado = service.buscarPorId(1L);

        assertNotNull(resultado);
    }

    @Test
    void deveLancarNegocioExceptionAoBuscarMedicoInexistente() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        var resultado = assertThrows(NegocioException.class, () -> service.buscarPorId(1L));

        assertEquals("Médico não encontrado.", resultado.getMessage());
    }

    @Test
    void deveRetornarMedicoAoBuscarAtivoPorIdComSucesso() {
        when(repository.findById(any())).thenReturn(Optional.of(Medico.builder().ativo(true).build()));

        var resultado = service.buscarAtivoPorId(1L);

        assertNotNull(resultado);
    }

    @Test
    void deveLancarNegocioExceptionAoBuscarAtivoQuandoMedicoInexistente() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        var resultado = assertThrows(NegocioException.class, () -> service.buscarAtivoPorId(1L));

        assertEquals("Médico não encontrado.", resultado.getMessage());
    }

    @Test
    void deveLancarNegocioExceptionAoBuscarAtivoQuandoMedicoInativo() {
        when(repository.findById(any())).thenReturn(Optional.of(Medico.builder().ativo(false).build()));

        var resultado = assertThrows(NegocioException.class, () -> service.buscarAtivoPorId(1L));

        assertEquals("O médico informado está inativo.", resultado.getMessage());
    }
}