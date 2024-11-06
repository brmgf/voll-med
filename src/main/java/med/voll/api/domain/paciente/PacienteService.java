package med.voll.api.domain.paciente;

import lombok.RequiredArgsConstructor;
import med.voll.api.infra.exception.NegocioException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PacienteService {

    private final PacienteRepository repository;

    public Paciente buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NegocioException("Paciente não encontrado")
        );
    }

    public Paciente buscarAtivoPorId(Long id) {
        var paciente = repository.findById(id).orElseThrow(() -> new NegocioException("Paciente não encontrado"));
        if (Boolean.FALSE.equals(paciente.getAtivo())) {
            throw new NegocioException("O paciente informado está inativo.");
        }

        return paciente;
    }
}
