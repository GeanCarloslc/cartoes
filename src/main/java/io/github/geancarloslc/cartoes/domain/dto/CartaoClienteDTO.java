package io.github.geancarloslc.cartoes.domain.dto;

import io.github.geancarloslc.cartoes.domain.entity.Cartao;
import io.github.geancarloslc.cartoes.domain.entity.CartaoCliente;
import io.github.geancarloslc.cartoes.domain.enums.BandeiraCartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartaoClienteDTO {
    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public static CartaoClienteDTO fromModel(CartaoCliente model) {
        return new CartaoClienteDTO(
                model.getCartao().getNome(),
                model.getCartao().getBandeira().toString(),
                model.getLimite()
        );
    }

}
