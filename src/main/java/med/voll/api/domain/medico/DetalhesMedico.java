package med.voll.api.domain.medico;

import io.swagger.v3.oas.annotations.media.Schema;
import med.voll.api.domain.endereco.Endereco;

public record DetalhesMedico(@Schema(example = "4136") Long id,
                             @Schema(example = "Felipe Arruda") String nome,
                             @Schema(example = "felipe@vollmed.com") String email,
                             @Schema(example = "905723") String crm,
                             @Schema(example = "CARDIOLOGIA") Especialidade especialidade,
                             @Schema(example = "3198550903") String telefone,
                             Endereco endereco) {

    public DetalhesMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(),
                medico.getCrm(), medico.getEspecialidade(), medico.getTelefone(),
                medico.getEndereco());
    }
}
