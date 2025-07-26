package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.EnderecoDTO;

public record MedicoCadastroDTO(
        //DEPENDENCY BEAN VALIDATION
        //@NotNull
        //NotBlank - verifica se não está vazio e nulo
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        // Pattern é uma expressão regular, essa simbologia indica que o campo
        // necessita ter entre 4 a 6 digitos somente
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        // VALID é para validar a classe dentro de outra classe, verificar se está correto
        @Valid
        EnderecoDTO endereco,

        @NotBlank
        @Pattern(regexp = "\\d{10,11}")
        String telefone) {

}
