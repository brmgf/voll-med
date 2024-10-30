package med.voll.api.domain.consulta.validacoes;

import lombok.RequiredArgsConstructor;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.infra.exception.NegocioException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class HorarioAntecedenciaCancelamentoValidator implements CancelamentoConsultaValidator {

    private final ConsultaRepository consultaRepository;

    public void validar(DadosCancelamentoConsulta cancelamentoConsulta) {
        var consulta = consultaRepository.getReferenceById(cancelamentoConsulta.idConsulta());
        var dataHoraConsulta = consulta.getDataHora();
        var dataHoraAtual = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(dataHoraAtual, dataHoraConsulta).toHours();

        if (diferencaEmHoras < 24) {
            throw new NegocioException("A consulta deve ser cancelada com antecedência mínima de 24 horas.");
        }
    }
}
