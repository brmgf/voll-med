package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.DadosEndereco;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
class MedicoTest {

    @Test
    void deveTestarConstrutorComDadosCadastroMedico() {
        var dadosEndereco = new DadosEndereco(null, null, null, null, null, null, null);
        var cadastroMedico = new DadosCadastroMedico("Felipe", "felipe@vollmed.com", "408030", Especialidade.CARDIOLOGIA, "35412600", dadosEndereco);

        var resultado = new Medico(cadastroMedico);

        assertEquals(cadastroMedico.nome(), resultado.getNome());
        assertEquals(cadastroMedico.email(), resultado.getEmail());
        assertEquals(cadastroMedico.crm(), resultado.getCrm());
        assertEquals(cadastroMedico.especialidade(), resultado.getEspecialidade());
        assertEquals(cadastroMedico.telefone(), resultado.getTelefone());
    }

    @Test
    void deveAtualizarMedicoComDadosPreenchidos() {
        var medico = new Medico(1L, "Felipe", "felipe@vollmed.com", "408030", Especialidade.CARDIOLOGIA, "35412600", true, null);
        var dadosAtualizacao = new DadosAtualizacaoMedico(1L, "João", "35412300", null);

        medico.atualizarInformacoes(dadosAtualizacao);

        assertEquals(dadosAtualizacao.id(), medico.getId());
        assertEquals(dadosAtualizacao.nome(), medico.getNome());
        assertEquals(dadosAtualizacao.telefone(), medico.getTelefone());
    }

    @Test
    void naoDeveAtualizarMedicoComDadosNaoPreenchidos() {
        var medico = new Medico(1L, "Felipe", "felipe@vollmed.com", "408030", Especialidade.CARDIOLOGIA, "35412600", true, null);
        var dadosAtualizacao = new DadosAtualizacaoMedico(1L, null, null, null);

        medico.atualizarInformacoes(dadosAtualizacao);

        assertEquals(dadosAtualizacao.id(), medico.getId());
        assertEquals("Felipe", medico.getNome());
        assertEquals("35412600", medico.getTelefone());
    }

    @Test
    void deveInativarComSucesso() {
        var medico = new Medico(1L, "Felipe", "felipe@vollmed.com", "408030", Especialidade.CARDIOLOGIA, "35412600", true, null);

        medico.inativar();

        assertFalse(medico.getAtivo());
    }
}