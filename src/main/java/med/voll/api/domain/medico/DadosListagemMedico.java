package med.voll.api.domain.medico;

import io.swagger.v3.oas.annotations.media.Schema;

public record DadosListagemMedico(@Schema(example = "4136") Long id,
                                  @Schema(example = "Felipe Arruda") String nome,
                                  @Schema(example = "felipe@vollmed.com") String email,
                                  @Schema(example = "905723") String crm,
                                  @Schema(example = "CARDIOLOGIA") Especialidade especialidade) {

    public DadosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
