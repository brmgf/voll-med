package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DetalhesConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime dataHora) {

    public DetalhesConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getDataHora());
    }
}
