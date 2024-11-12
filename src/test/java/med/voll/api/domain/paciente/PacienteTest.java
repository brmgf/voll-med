package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.DadosEndereco;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
class PacienteTest {

    @Test
    void deveTestarConstrutorComDadosCadastroPaciente() {
        var dadosEndereco = new DadosEndereco(null, null, null, null, null, null, null);
        var cadastroPaciente = new DadosCadastroPaciente("Luis", "luis@gmail.com", "3799887700", "96940013075", dadosEndereco);

        var resultado = new Paciente(cadastroPaciente);

        assertEquals(cadastroPaciente.nome(), resultado.getNome());
        assertEquals(cadastroPaciente.email(), resultado.getEmail());
        assertEquals(cadastroPaciente.telefone(), resultado.getTelefone());
        assertEquals(cadastroPaciente.cpf(), resultado.getCpf());
    }

    @Test
    void deveAtualizarPacienteComDadosPreenchidos() {
        var paciente = new Paciente(1L, "Fernanda", "fer@gmail.com", "3799886600", "17933878008", true, null);
        var atualizacaoPaciente = new DadosAtualizacaoPaciente(1L, "Luis", "3799887700", null);

        paciente.atualizarInformacoes(atualizacaoPaciente);

        assertEquals(atualizacaoPaciente.id(), paciente.getId());
        assertEquals(atualizacaoPaciente.nome(), paciente.getNome());
        assertEquals(atualizacaoPaciente.telefone(), paciente.getTelefone());
    }

    @Test
    void naoDeveAtualizarPacienteComDadosNaoPreenchidos() {
        var paciente = new Paciente(1L, "Fernanda", "fer@gmail.com", "3799886600", "17933878008", true, null);
        var atualizacaoPaciente = new DadosAtualizacaoPaciente(1L, null, null, null);

        paciente.atualizarInformacoes(atualizacaoPaciente);

        assertEquals(1L, paciente.getId());
        assertEquals("Fernanda", paciente.getNome());
        assertEquals("3799886600", paciente.getTelefone());
    }

    @Test
    void deveInativarComSucesso() {
        var paciente = new Paciente(1L, "Fernanda", "fer@gmail.com", "3799886600", "17933878008", true, null);

        paciente.inativar();

        assertFalse(paciente.getAtivo());
    }
}