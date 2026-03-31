package br.com.mh.cobrancas_whpp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "divida")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Divida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @NotNull
    @Column(name = "valor_principal", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorPrincipal;

    /**
     * Juros percentual total sobre a dívida.
     * Exemplo: 20 = 20% sobre o valorPrincipal
     */
    @NotNull
    @Column(name = "juros_percentual", nullable = false, precision = 5, scale = 2)
    private BigDecimal jurosPercentual;

    @Column(name = "numero_parcelas", nullable = false)
    private Integer numeroParcelas;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private DividaStatus status;


    @OneToMany(mappedBy = "divida", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parcela> parcelas = new ArrayList<>();
}
