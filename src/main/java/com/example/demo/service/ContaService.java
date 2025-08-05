package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.model.Conta;
import com.example.demo.repository.ContaRepository;
import com.example.demo.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    public Conta criarConta(Long clienteId, Conta conta) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        conta.setCliente(cliente);
        conta.setSaldo(BigDecimal.ZERO); // saldo inicial

        return contaRepository.save(conta);
    }

    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    public Conta buscarPorId(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }
}
