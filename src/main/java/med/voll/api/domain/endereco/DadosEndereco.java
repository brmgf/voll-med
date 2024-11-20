package med.voll.api.domain.endereco;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(@Schema(description = "Logradouro", example = "Rua Princesa Isabel", required = true)
                            @NotBlank(message = "É obrigatório informar um logradouro.")
                            String logradouro,
                            @Schema(description = "Bairro", example = "Centro", required = true)
                            @NotBlank(message = "É obrigatório informar um bairro.")
                            String bairro,
                            @Schema(description = "CEP", example = "34000000", required = true)
                            @NotBlank(message = "É obrigatório informar um cep.")
                            @Pattern(regexp = "\\d{8}", message = "CEP inválido, por favor, tente novamente.")
                            String cep,
                            @Schema(description = "Cidade", example = "Belo Horizonte", required = true)
                            @NotBlank(message = "É obrigatório informar uma cidade.")
                            String cidade,
                            @Schema(description = "Estado", example = "MG", required = true)
                            @NotBlank(message = "É obrigatório informar um estado.")
                            String uf,
                            @Schema(description = "Número", example = "100")
                            String numero,
                            @Schema(description = "Complemento", example = "Apartamento 205")
                            String complemento) {
}
