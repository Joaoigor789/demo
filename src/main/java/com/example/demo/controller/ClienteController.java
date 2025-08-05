package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente criar(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
