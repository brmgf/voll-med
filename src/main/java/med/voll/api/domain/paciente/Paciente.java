package med.voll.api.domain.paciente;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.util.StringUtil;

import java.util.Objects;

@Table(name = "pacientes")
@Entity(name = "paciente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100)
    private String nome;

    @Email
    @NotBlank
    @Column(length = 100)
    private String email;

    @NotBlank
    @Column(length = 20)
    private String telefone;

    @NotBlank
    @Column(length = 11)
    private String cpf;

    @NotNull
    private Boolean ativo;

    @NotNull
    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastroPaciente cadastroPaciente) {
        this.nome = cadastroPaciente.nome();
        this.email = cadastroPaciente.email();
        this.telefone = cadastroPaciente.telefone();
        this.cpf = cadastroPaciente.cpf();
        this.ativo = true;
        this.endereco = new Endereco(cadastroPaciente.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoPaciente atualizacaoPaciente) {
        if (StringUtil.isTextoPreenchido(atualizacaoPaciente.nome())) {
            this.nome = atualizacaoPaciente.nome();
        }
        if (StringUtil.isTextoPreenchido(atualizacaoPaciente.telefone())) {
            this.telefone = atualizacaoPaciente.telefone();
        }
        if (Objects.nonNull(atualizacaoPaciente.endereco())) {
            this.endereco.atualizarInformacoes(atualizacaoPaciente.endereco());
        }
    }

    public void inativar() {
        this.ativo = false;
    }
}
