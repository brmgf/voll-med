package med.voll.api.domain.consulta;

import lombok.RequiredArgsConstructor;
import med.voll.api.domain.consulta.validacoes.CancelamentoConsultaValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CancelamentoConsultaService {

    private final List<CancelamentoConsultaValidator> validadores;
    private final ConsultaRepository repository;

    @Transactional
    public void cancelar(DadosCancelamentoConsulta cancelamentoConsulta) {
        validadores.forEach(v -> v.validar(cancelamentoConsulta));
        var consulta = repository.getReferenceById(cancelamentoConsulta.idConsulta());
        consulta.cancelar(cancelamentoConsulta.motivoCancelamento());
    }
}
