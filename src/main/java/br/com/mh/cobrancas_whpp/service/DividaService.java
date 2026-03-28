package br.com.mh.cobrancas_whpp.service;

import br.com.mh.cobrancas_whpp.controller.dto.DividaRequest;
import br.com.mh.cobrancas_whpp.entity.*;
import br.com.mh.cobrancas_whpp.repository.ClienteRepository;
import br.com.mh.cobrancas_whpp.repository.DividaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DividaService {

    private final DividaRepository dividaRepository;
    private final ClienteRepository clienteRepository;

    @Transactional
    public Divida criarDivida(DividaRequest request) {
        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        BigDecimal valorPrincipal = request.valorPrincipal();
        BigDecimal jurosPercentual = request.jurosPercentual();
        Integer numeroParcelas = request.numeroParcelas();

        // jurosPercentual = 20 -> 0.20
        BigDecimal fatorJuros = jurosPercentual
                .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);

        BigDecimal valorTotal = valorPrincipal
                .multiply(BigDecimal.ONE.add(fatorJuros));

        BigDecimal valorParcela = valorTotal
                .divide(BigDecimal.valueOf(numeroParcelas), 2, RoundingMode.HALF_UP);

        Divida divida = Divida.builder()
                .cliente(cliente)
                .valorPrincipal(valorPrincipal)
                .jurosPercentual(jurosPercentual)
                .numeroParcelas(numeroParcelas)
                .dataCriacao(LocalDate.now())
                .status(DividaStatus.ATIVA)
                .build();

        List<Parcela> parcelas = new ArrayList<>();

        LocalDate dataPrimeiroVencimento = request.dataPrimeiroVencimento();

        for (int i = 1; i <= numeroParcelas; i++) {
            LocalDate vencimento = dataPrimeiroVencimento.plusMonths(i - 1);

            Parcela parcela = Parcela.builder()
                    .divida(divida)
                    .numeroParcela(i)
                    .valorParcela(valorParcela)
                    .dataVencimento(vencimento)
                    .status(ParcelaStatus.PENDENTE)
                    .build();

            parcelas.add(parcela);
        }

        divida.setParcelas(parcelas);

        return dividaRepository.save(divida);
    }

    public List<Divida> listarPorCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        return dividaRepository.findByCliente(cliente);
    }
}
