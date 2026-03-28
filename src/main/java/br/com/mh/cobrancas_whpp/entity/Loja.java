package br.com.mh.cobrancas_whpp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "loja")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank
    @Column(name = "nome_responsavel", nullable = false, length = 100)
    private String nomeResponsavel;

    @NotBlank
    @Column(name = "telefone_whatsapp", nullable = false, length = 20)
    private String telefoneWhatsapp;

    @Email
    @Column(length = 100)
    private String email;

    @Builder.Default
    @Column(nullable = false)
    private Boolean ativo = true;

    @Builder.Default
    @Column(nullable = false)
    private Boolean receberResumoWhatsapp = false;
}