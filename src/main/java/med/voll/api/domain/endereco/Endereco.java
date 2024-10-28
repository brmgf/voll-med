package med.voll.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.util.StringUtil;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;

    public Endereco(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
    }

    public void atualizarInformacoes(DadosEndereco endereco) {
        if (StringUtil.isTextoPreenchido(endereco.logradouro())) {
            this.logradouro = endereco.logradouro();
        }
        if (StringUtil.isTextoPreenchido(endereco.bairro())) {
            this.bairro = endereco.bairro();
        }
        if (StringUtil.isTextoPreenchido(endereco.cep())) {
            this.cep = endereco.cep();
        }
        if (StringUtil.isTextoPreenchido(endereco.cidade())) {
            this.cidade = endereco.cidade();
        }
        if (StringUtil.isTextoPreenchido(endereco.uf())) {
            this.uf = endereco.uf();
        }
        if (StringUtil.isTextoPreenchido(endereco.numero())) {
            this.numero = endereco.numero();
        }
        if (StringUtil.isTextoPreenchido(endereco.complemento())) {
            this.complemento = endereco.complemento();
        }
    }
}
