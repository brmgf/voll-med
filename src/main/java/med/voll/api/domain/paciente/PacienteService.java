package med.voll.api.domain.paciente;

import lombok.RequiredArgsConstructor;
import med.voll.api.infra.exception.NegocioException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PacienteService {

    private final PacienteRepository repository;

    @Transactional
    public Paciente salvar(Paciente paciente) {
        return repository.save(paciente);
    }

    @Transactional(readOnly = true)
    public Page<Paciente> buscarTodosAtivosComPaginacao(Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable);
    }

    @Transactional(readOnly = true)
    public Paciente buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new NegocioException("Paciente não encontrado."));
    }

    @Transactional(readOnly = true)
    public Paciente buscarAtivoPorId(Long id) {
        var paciente = repository.findById(id).orElseThrow(() -> new NegocioException("Paciente não encontrado."));
        if (Boolean.FALSE.equals(paciente.getAtivo())) {
            throw new NegocioException("O paciente informado está inativo.");
        }

        return paciente;
    }
}
