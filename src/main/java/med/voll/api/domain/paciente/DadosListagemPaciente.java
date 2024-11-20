package med.voll.api.domain.paciente;

import io.swagger.v3.oas.annotations.media.Schema;

public record DadosListagemPaciente(@Schema(example = "7683") Long id,
                                    @Schema(example = "Roberto Silva") String nome,
                                    @Schema(example = "roberto@gmail.com") String email,
                                    @Schema(example = "26378580014") String cpf) {

    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
