package med.voll.api.domain.paciente;

import io.swagger.v3.oas.annotations.media.Schema;
import med.voll.api.domain.endereco.Endereco;

public record DetalhesPaciente(@Schema(example = "7683")
                                Long id,
                                @Schema(example = "Roberto Silva")
                                String nome,
                                @Schema(example = "3799880022")
                                String telefone,
                                @Schema(example = "roberto@gmail.com")
                                String email,
                                @Schema(example = "26378580014")
                                String cpf,
                                Endereco endereco) {

    public DetalhesPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getTelefone(), paciente.getEmail(), paciente.getCpf(), paciente.getEndereco());
    }
}
