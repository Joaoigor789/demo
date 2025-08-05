package com.example.demo.controller;

import com.example.demo.model.Conta;
import com.example.demo.service.ContaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/{clienteId}")
    public Conta criarConta(@PathVariable Long clienteId, @RequestBody Conta conta) {
        return contaService.criarConta(clienteId, conta);
    }

    @GetMapping
    public List<Conta> listarContas() {
        return contaService.listarContas();
    }

    @GetMapping("/{id}")
    public Conta buscarPorId(@PathVariable Long id) {
        return contaService.buscarPorId(id);
    }
}
