package med.voll.api.domain.medico.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import med.voll.api.domain.medico.dto.MedicoAtualizacaoDTO;
import med.voll.api.domain.medico.dto.MedicoCadastroDTO;

// EMBAIXO DO ENTITY É TUDO LOMBOK, para reduzir código
// name do @Entity serve para JPQL, me referenciar a classe Medico
@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
//@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;
    private Boolean ativo;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    // embedded incorpora endereço dentro de medico sem necessariamente criar outra tabela

    public Medico() {
    }

    public Medico(MedicoCadastroDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
        this.telefone = dados.telefone();
        this.ativo = true;
    }

    public void atualizarInformacoes(@Valid MedicoAtualizacaoDTO dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if(dados.telefone() != null) {
            endereco.atualizarEndereco(dados.endereco());
        }
    }


    public void excluir() {
        this.ativo = false;
    }
}
