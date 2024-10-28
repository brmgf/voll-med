package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.Endereco;

public record DetalhesPaciente(Long id,
                               String nome,
                               String telefone,
                               String email,
                               String cpf,
                               Endereco endereco) {

    public DetalhesPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getTelefone(), paciente.getEmail(), paciente.getCpf(), paciente.getEndereco());
    }
}
