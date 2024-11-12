package med.voll.api.domain.medico;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.util.StringUtil;

import java.util.Objects;

@Table(name = "medicos")
@Entity(name = "medico")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100)
    private String nome;

    @NotBlank
    @Column(length = 100)
    private String email;

    @NotBlank
    @Column(length = 6)
    private String crm;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @NotBlank
    @Column(length = 20)
    private String telefone;

    @NotNull
    private Boolean ativo;

    @NotNull
    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico cadastroMedico) {
        this.nome = cadastroMedico.nome();
        this.email = cadastroMedico.email();
        this.crm = cadastroMedico.crm();
        this.especialidade = cadastroMedico.especialidade();
        this.telefone = cadastroMedico.telefone();
        this.ativo = true;
        this.endereco = new Endereco(cadastroMedico.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dadosAtualizacaoMedico) {
        if (StringUtil.isTextoPreenchido(dadosAtualizacaoMedico.nome())) {
            this.nome = dadosAtualizacaoMedico.nome();
        }
        if (StringUtil.isTextoPreenchido(dadosAtualizacaoMedico.telefone())) {
            this.telefone = dadosAtualizacaoMedico.telefone();
        }
        if (Objects.nonNull(dadosAtualizacaoMedico.endereco())) {
            this.endereco.atualizarInformacoes(dadosAtualizacaoMedico.endereco());
        }
    }

    public void inativar() {
        this.ativo = false;
    }
}
