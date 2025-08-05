package com.example.demo.service;

import com.example.demo.model.Conta;
import com.example.demo.model.Transacao;
import com.example.demo.model.TipoTransacao;
import com.example.demo.repository.ContaRepository;
import com.example.demo.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ContaRepository contaRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, ContaRepository contaRepository) {
        this.transacaoRepository = transacaoRepository;
        this.contaRepository = contaRepository;
    }

    public Transacao realizarTransacao(Long contaId, Transacao transacao) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        BigDecimal valor = transacao.getValor();

        if (transacao.getTipo() == TipoTransacao.SAQUE) {
            if (conta.getSaldo().compareTo(valor) < 0) {
                throw new RuntimeException("Saldo insuficiente");
            }
            conta.setSaldo(conta.getSaldo().subtract(valor));
        } else if (transacao.getTipo() == TipoTransacao.DEPOSITO) {
            conta.setSaldo(conta.getSaldo().add(valor));
        } else {
            throw new RuntimeException("Tipo de transação inválido");
        }

        transacao.setConta(conta);
        contaRepository.save(conta);

        return transacaoRepository.save(transacao);
    }

    public List<Transacao> listarPorConta(Long contaId) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        return conta.getTransacoes();
    }
}
