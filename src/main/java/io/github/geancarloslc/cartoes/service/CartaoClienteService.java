package io.github.geancarloslc.cartoes.service;

import io.github.geancarloslc.cartoes.domain.entity.CartaoCliente;
import io.github.geancarloslc.cartoes.infra.repository.CartaoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaoClienteService {

    @Autowired
    private CartaoClienteRepository clienteRepository;

    public List<CartaoCliente> buscarCartoesClienteCpf(String cpf){
        return clienteRepository.findByCpf(cpf);
    }

}
