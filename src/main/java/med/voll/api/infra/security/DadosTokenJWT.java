package med.voll.api.infra.security;

import io.swagger.v3.oas.annotations.media.Schema;

public record DadosTokenJWT(@Schema(example = "c9fE4ght5Weq50ijT70vch3yMe8king3")
                            String token) {
}
