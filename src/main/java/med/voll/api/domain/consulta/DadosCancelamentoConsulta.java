package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosCancelamentoConsulta(@NotNull
                                        Long idConsulta,
                                        @NotBlank(message = "É obrigatório informar o motivo do cancelamento.")
                                        @Size(max = 255)
                                        String motivoCancelamento) {
}
