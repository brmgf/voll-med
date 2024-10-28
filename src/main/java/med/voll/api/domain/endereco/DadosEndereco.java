package med.voll.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
                            @NotBlank(message = "É obrigatório informar um logradouro.")
                            String logradouro,
                            @NotBlank(message = "É obrigatório informar um bairro.")
                            String bairro,
                            @NotBlank(message = "É obrigatório informar um cep.")
                            @Pattern(regexp = "\\d{8}", message = "CEP inválido, por favor, tente novamente.")
                            String cep,
                            @NotBlank(message = "É obrigatório informar uma cidade.")
                            String cidade,
                            @NotBlank(message = "É obrigatório informar um estado.")
                            String uf,
                            String numero,
                            String complemento) {
}
