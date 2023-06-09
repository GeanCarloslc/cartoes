package io.github.geancarloslc.cartoes.controller;

import io.github.geancarloslc.cartoes.domain.dto.CartaoClienteDTO;
import io.github.geancarloslc.cartoes.domain.dto.CartaoDTO;
import io.github.geancarloslc.cartoes.domain.entity.Cartao;
import io.github.geancarloslc.cartoes.domain.entity.CartaoCliente;
import io.github.geancarloslc.cartoes.service.CartaoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.github.geancarloslc.cartoes.service.CartaoService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
public class CartoesController {

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private CartaoClienteService cartaoClienteService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody CartaoDTO cartaoDTO) {
        Cartao cartao = cartaoDTO.toModel();
        cartaoService.salvar(cartao);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("renda={renda}")
                .buildAndExpand(cartao.getRenda())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> buscarCartoesRendaAteh(@RequestParam("renda") Long renda) {
        List<Cartao> cartoesLista = cartaoService.buscarCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(cartoesLista);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartaoClienteDTO>> buscarCartoesClienteCpf(@RequestParam("cpf") String cpf) {
        List<CartaoCliente> cartoesClienteLista = cartaoClienteService.buscarCartoesClienteCpf(cpf);
        List<CartaoClienteDTO> resultList = cartoesClienteLista
                .stream()
                .map(CartaoClienteDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}
