package br.com.mh.cobrancas_whpp.service;

import br.com.mh.cobrancas_whpp.controller.dto.CobrancaClienteResumoResponse;
import br.com.mh.cobrancas_whpp.controller.dto.CobrancaDiariaLojaResponse;
import br.com.mh.cobrancas_whpp.service.whatsapp.WhatsappService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnvioResumoWhatsappService {

    private final ResumoCobrancaService resumoCobrancaService;
    private final WhatsappService whatsappService;

    public void enviarResumoDoDia(LocalDate dataReferencia) {
        List<CobrancaDiariaLojaResponse> resumos = resumoCobrancaService.gerarResumoDoDia(dataReferencia);

        for (CobrancaDiariaLojaResponse resumo : resumos) {
            if (!Boolean.TRUE.equals(resumo.receberResumoWhatsapp())) {
                continue;
            }

            StringBuilder mensagem = new StringBuilder();
            mensagem.append(resumo.mensagemResumoLoja()).append("\n\n");
            mensagem.append("Mensagens prontas para cobrança:").append("\n\n");

            for (CobrancaClienteResumoResponse cliente : resumo.cobrancasDoDia()) {
                mensagem.append("• ")
                        .append(cliente.nomeCliente())
                        .append(": ")
                        .append(cliente.mensagemPronta())
                        .append("\n\n");
            }

            whatsappService.enviarMensagem(
                    resumo.telefoneWhatsappLoja(),
                    mensagem.toString()
            );
        }
    }

    public List<String> gerarMensagensDoDia(LocalDate dataReferencia) {
        return resumoCobrancaService.gerarResumoDoDia(dataReferencia).stream()
                .map(resumo -> {
                    String mensagensClientes = resumo.cobrancasDoDia().stream()
                            .map(CobrancaClienteResumoResponse::mensagemPronta)
                            .collect(Collectors.joining("\n\n"));

                    return resumo.mensagemResumoLoja() + "\n\n" + mensagensClientes;
                })
                .toList();
    }
}