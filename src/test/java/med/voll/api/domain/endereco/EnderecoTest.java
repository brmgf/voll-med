package med.voll.api.domain.endereco;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EnderecoTest {

    @Test
    void deveTestarConstrutorComDadosEndereco() {
        var dadosEndereco = new DadosEndereco("Rua X", "Centro", "3562000", "Abaeté", "MG", "1", "Apartamento 202");

        var resultado = new Endereco(dadosEndereco);

        assertEquals(dadosEndereco.logradouro(), resultado.getLogradouro());
        assertEquals(dadosEndereco.bairro(), resultado.getBairro());
        assertEquals(dadosEndereco.cep(), resultado.getCep());
        assertEquals(dadosEndereco.cidade(), resultado.getCidade());
        assertEquals(dadosEndereco.uf(), resultado.getUf());
        assertEquals(dadosEndereco.numero(), resultado.getNumero());
        assertEquals(dadosEndereco.complemento(), resultado.getComplemento());
    }

    @Test
    void deveAtualizarEnderecoComDadosPreenchidos() {
        var endereco = new Endereco("Rua V", "São João", "3561000", "Dores do Indaiá", "MG", "2", null);
        var dadosEndereco = new DadosEndereco("Rua X", "Centro", "3562000", "Abaeté", "MG", "1", "Apartamento 202");

        endereco.atualizarInformacoes(dadosEndereco);

        assertEquals(dadosEndereco.logradouro(), endereco.getLogradouro());
        assertEquals(dadosEndereco.bairro(), endereco.getBairro());
        assertEquals(dadosEndereco.cep(), endereco.getCep());
        assertEquals(dadosEndereco.cidade(), endereco.getCidade());
        assertEquals(dadosEndereco.uf(), endereco.getUf());
        assertEquals(dadosEndereco.numero(), endereco.getNumero());
        assertEquals(dadosEndereco.complemento(), endereco.getComplemento());
    }

    @Test
    void naoDeveAtualizarEnderecoComDadosNaoPreenchidos() {
        var endereco = new Endereco("Rua V", "São João", "3561000", "Dores do Indaiá", "MG", "2", "Apartamento 101");
        var dadosEndereco = new DadosEndereco(null, null, null, null, null, null, null);

        endereco.atualizarInformacoes(dadosEndereco);

        assertEquals("Rua V", endereco.getLogradouro());
        assertEquals("São João", endereco.getBairro());
        assertEquals("3561000", endereco.getCep());
        assertEquals("Dores do Indaiá", endereco.getCidade());
        assertEquals("MG", endereco.getUf());
        assertEquals("2", endereco.getNumero());
        assertEquals("Apartamento 101", endereco.getComplemento());
    }
}