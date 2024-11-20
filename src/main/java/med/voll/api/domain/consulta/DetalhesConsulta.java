package med.voll.api.domain.consulta;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record DetalhesConsulta(@Schema(example = "548") Long id,
                               @Schema(example = "4136") Long idMedico,
                               @Schema(example = "7683") Long idPaciente,
                               @Schema(example = "2024-11-20T23:51:42.380Z") LocalDateTime dataHora) {

    public DetalhesConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getDataHora());
    }
}
