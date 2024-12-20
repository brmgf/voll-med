package med.voll.api.domain.medico;

import lombok.RequiredArgsConstructor;
import med.voll.api.infra.exception.NegocioException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MedicoService {

    private final MedicoRepository repository;

    @Transactional
    public Medico salvar(Medico medico) {
        return repository.save(medico);
    }

    @Transactional(readOnly = true)
    public Page<Medico> buscarTodosAtivosComPaginacao(Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable);
    }

    @Transactional(readOnly = true)
    public Medico buscarPorId(Long idMedico) {
        return repository.findById(idMedico).orElseThrow(() -> new NegocioException("Médico não encontrado."));
    }

    @Transactional(readOnly = true)
    public Medico buscarAtivoPorId(Long id) {
        var medico = repository.findById(id).orElseThrow(() -> new NegocioException("Médico não encontrado."));
        if (Boolean.FALSE.equals(medico.getAtivo())) {
            throw new NegocioException("O médico informado está inativo.");
        }

        return medico;
    }
}
