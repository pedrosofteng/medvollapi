package med.voll.api.domain.endereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(
        @NotBlank
        String logadouro,
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,

        String complemento,

        String numero) {
        public EnderecoDTO(@NotNull @Valid Endereco endereco) {
                this(
                        endereco.getLogadouro(),
                        endereco.getBairro(),
                        endereco.getCep(),
                        endereco.getCidade(),
                        endereco.getUf(),
                        endereco.getComplemento(),
                        endereco.getNumero());
        }
}
