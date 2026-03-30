package br.com.mh.cobrancas_whpp.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CobrancaPainelResponse {

    private Long parcelaId;
    private String clienteNome;
    private String lojaNome;
    private String telefone;
    private BigDecimal valor;
    private LocalDate dataVencimento;
    private String status;
    private String horarioSugerido;
    private String mensagemCobranca;
    private String textoAgenda;

    public CobrancaPainelResponse() {
    }

    public CobrancaPainelResponse(Long parcelaId, String clienteNome, String lojaNome, String telefone,
                                  BigDecimal valor, LocalDate dataVencimento, String status,
                                  String horarioSugerido, String mensagemCobranca, String textoAgenda) {
        this.parcelaId = parcelaId;
        this.clienteNome = clienteNome;
        this.lojaNome = lojaNome;
        this.telefone = telefone;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.status = status;
        this.horarioSugerido = horarioSugerido;
        this.mensagemCobranca = mensagemCobranca;
        this.textoAgenda = textoAgenda;
    }

    public Long getParcelaId() {
        return parcelaId;
    }

    public void setParcelaId(Long parcelaId) {
        this.parcelaId = parcelaId;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getLojaNome() {
        return lojaNome;
    }

    public void setLojaNome(String lojaNome) {
        this.lojaNome = lojaNome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHorarioSugerido() {
        return horarioSugerido;
    }

    public void setHorarioSugerido(String horarioSugerido) {
        this.horarioSugerido = horarioSugerido;
    }

    public String getMensagemCobranca() {
        return mensagemCobranca;
    }

    public void setMensagemCobranca(String mensagemCobranca) {
        this.mensagemCobranca = mensagemCobranca;
    }

    public String getTextoAgenda() {
        return textoAgenda;
    }

    public void setTextoAgenda(String textoAgenda) {
        this.textoAgenda = textoAgenda;
    }
}