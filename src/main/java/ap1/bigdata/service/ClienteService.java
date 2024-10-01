package ap1.bigdata.service;

import ap1.bigdata.controller.dto.AtualizarClienteRequest;
import ap1.bigdata.exception.ClienteNaoEncontradoException;
import ap1.bigdata.model.Cliente;
import ap1.bigdata.repository.ClienteRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente getCliente(int id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado " + id));
    }

    public Cliente incluir(Cliente cliente) {
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }
    
        // Verifica se o cliente tem endereços e associa o cliente a cada um deles
        if (cliente.getEnderecos() != null) {
            cliente.getEnderecos().forEach(endereco -> endereco.setCliente(cliente));
        }
    
        // Salva o cliente e seus endereços (relacionamento em cascata)
        return clienteRepository.save(cliente);
    }
    
    public Cliente atualizar(int id, AtualizarClienteRequest atualizarClienteRequest) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));

        // Atualiza manualmente as propriedades que precisam ser alteradas
        if (atualizarClienteRequest.getNome() != null) {
            cliente.setNome(atualizarClienteRequest.getNome());
        }
        if (atualizarClienteRequest.getCpf() != null) {
            cliente.setCpf(atualizarClienteRequest.getCpf());
        }
        if (atualizarClienteRequest.getTelefone() != null) {
            cliente.setTelefone(atualizarClienteRequest.getTelefone());
        }
        if (atualizarClienteRequest.getEmail() != null) {
            cliente.setEmail(atualizarClienteRequest.getEmail());
        }
        // Aqui você pode tratar endereços e outras relações de maneira específica.

        clienteRepository.save(cliente);
        return cliente;
    }

    public void deletar(int id) {
        clienteRepository.deleteById(id);
    }
}
