package med.voll.api.domain.medico.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import med.voll.api.domain.medico.dto.EnderecoDTO;

// precisa dessa anotação na classe do embedded
// endereço fica dentro de medico
@Embeddable
@Getter
//@NoArgsConstructor
//@AllArgsConstructor
public class Endereco {
    private String logadouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String complemento;
    private String numero;

    public Endereco() {
    }

    public Endereco(EnderecoDTO endereco) {
        this.logadouro = endereco.logadouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
        this.complemento = endereco.complemento();
        this.numero = endereco.numero();
    }

    public void atualizarEndereco(EnderecoDTO endereco) {
        if (endereco.logadouro() != null) {
            this.logadouro = endereco.logadouro();
        }
        if (endereco.numero() != null) {
            this.numero = endereco.numero();
        }
        if (endereco.cep() != null) {
            this.cep = endereco.cep();
        }
        if (endereco.uf() != null) {
            this.uf = endereco.uf();
        }
        if (endereco.complemento() != null) {
            this.complemento = endereco.complemento();
        }
        if (endereco.bairro() != null) {
            this.bairro = endereco.bairro();
        }
        if (endereco.cidade() != null) {
            this.cidade = endereco.cidade();
        }
    }
}
