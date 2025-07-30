package med.voll.api.domain.cancelarconsulta;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.consulta.Consulta;

@Table(name = "cancelamento_consultas")
@Entity(name = "CancelamentoConsulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CancelamentoConsulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;
    private String motivoCancelamento;

    public CancelamentoConsulta(Consulta consulta, @NotBlank String motivoCancelamento) {
        this.consulta = consulta;
        this.motivoCancelamento = motivoCancelamento;
    }
}
