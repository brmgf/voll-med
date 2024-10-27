package med.voll.api.medico;

import med.voll.api.endereco.Endereco;

public record DetalhesMedico(Long id,
                             String nome,
                             String email,
                             String crm,
                             Especialidade especialidade,
                             String telefone,
                             Endereco endereco) {

    public DetalhesMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(),
                medico.getCrm(), medico.getEspecialidade(), medico.getTelefone(),
                medico.getEndereco());
    }
}
