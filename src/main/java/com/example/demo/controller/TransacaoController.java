package com.example.demo.controller;

import com.example.demo.model.Transacao;
import com.example.demo.service.TransacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("/{contaId}")
    public Transacao realizarTransacao(@PathVariable Long contaId, @RequestBody Transacao transacao) {
        return transacaoService.realizarTransacao(contaId, transacao);
    }

    @GetMapping("/{contaId}")
    public List<Transacao> listarPorConta(@PathVariable Long contaId) {
        return transacaoService.listarPorConta(contaId);
    }
}
