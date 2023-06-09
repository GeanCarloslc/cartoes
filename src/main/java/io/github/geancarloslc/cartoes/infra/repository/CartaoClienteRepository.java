package io.github.geancarloslc.cartoes.infra.repository;

import io.github.geancarloslc.cartoes.domain.entity.CartaoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaoClienteRepository extends JpaRepository<CartaoCliente, Long> {
    List<CartaoCliente> findByCpf(String cpf);
}