package io.github.geancarloslc.cartoes.domain.dto;

import io.github.geancarloslc.cartoes.domain.entity.Cartao;
import io.github.geancarloslc.cartoes.domain.enums.BandeiraCartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoDTO {
    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao toModel(){
        return new Cartao(this.nome, this.bandeira, this.renda, this.limite);
    }
}
