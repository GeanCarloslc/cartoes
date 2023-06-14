package io.github.geancarloslc.cartoes.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.geancarloslc.cartoes.domain.entity.Cartao;
import io.github.geancarloslc.cartoes.domain.entity.CartaoCliente;
import io.github.geancarloslc.cartoes.infra.dto.DadosSolicitacaoEmissaoCartaoDTO;
import io.github.geancarloslc.cartoes.infra.repository.CartaoClienteRepository;
import io.github.geancarloslc.cartoes.infra.repository.CartaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmissaoCartaoSubscriber {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CartaoClienteRepository clienteRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload) {
        var mapper = new ObjectMapper();
        try {
            DadosSolicitacaoEmissaoCartaoDTO dadosSolicitacaoEmissaoCartaoDTO
                    = mapper.readValue(payload, DadosSolicitacaoEmissaoCartaoDTO.class);

            Cartao cartao = cartaoRepository.findById(dadosSolicitacaoEmissaoCartaoDTO.getIdCartao()).orElseThrow();
            CartaoCliente cartaoCliente = new CartaoCliente();
            cartaoCliente.setCartao(cartao);
            cartaoCliente.setCpf(dadosSolicitacaoEmissaoCartaoDTO.getCpf());
            cartaoCliente.setLimite(dadosSolicitacaoEmissaoCartaoDTO.getLimiteLiberado());

            clienteRepository.save(cartaoCliente);
        } catch (Exception e) {
            log.error("Erro ao receber solicitacao de emissao de cartao: {}", e.getMessage());
        }
    }
}
