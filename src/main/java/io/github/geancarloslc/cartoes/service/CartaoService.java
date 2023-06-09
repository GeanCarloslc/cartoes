package io.github.geancarloslc.cartoes.service;

import io.github.geancarloslc.cartoes.domain.entity.Cartao;
import io.github.geancarloslc.cartoes.infra.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Transactional
    public Cartao salvar(Cartao cartao) {
        return cartaoRepository.save(cartao);
    }

    public List<Cartao> buscarCartoesRendaMenorIgual(Long renda) {
        BigDecimal rendaBigDecial = BigDecimal.valueOf(renda);
        return cartaoRepository.findByRendaLessThanEqual(rendaBigDecial);
    }
}
