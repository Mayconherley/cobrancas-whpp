package br.com.mh.cobrancas_whpp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nome;

    // opcional, mas já deixei aqui
    @Column(length = 14)
    private String cpf;

    @Column(length = 20)
    private String telefone;

    @Column(nullable = false)
    private Boolean receberNotificacaoWhatsapp;

    @ManyToOne(optional = false)
    @JoinColumn(name = "loja_id", nullable = false)
    private Loja loja;
}

