package ap1.bigdata.service;

import ap1.bigdata.model.Cliente;
import ap1.bigdata.model.Endereco;
import ap1.bigdata.repository.ClienteRepository;
import ap1.bigdata.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(int id) {
        return clienteRepository.findById(id);
    }

    public Cliente createCliente(Cliente cliente) {
        // Verifica se o CPF já está cadastrado
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        // Verifica se o e-mail já está cadastrado
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(int id) {
        clienteRepository.deleteById(id);
    }

    public void addEndereco(int clienteId, Endereco endereco) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);

        if (clienteOpt.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado");
        }

        Cliente cliente = clienteOpt.get();
        cliente.associarEndereco(endereco);
        clienteRepository.save(cliente);
    }

    public void updateEndereco(int clienteId, int enderecoId, Endereco enderecoAtualizado) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);

        if (clienteOpt.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado");
        }

        Cliente cliente = clienteOpt.get();
        Optional<Endereco> enderecoOpt = cliente.getEnderecos().stream()
                .filter(e -> e.getId() == enderecoId)
                .findFirst();

        if (enderecoOpt.isEmpty()) {
            throw new RuntimeException("Endereço não encontrado");
        }

        Endereco endereco = enderecoOpt.get();
        endereco.setRua(enderecoAtualizado.getRua());
        endereco.setNumero(enderecoAtualizado.getNumero());
        endereco.setBairro(enderecoAtualizado.getBairro());
        endereco.setCidade(enderecoAtualizado.getCidade());
        endereco.setEstado(enderecoAtualizado.getEstado());
        endereco.setCep(enderecoAtualizado.getCep());

        clienteRepository.save(cliente);
    }

    public void removeEndereco(int clienteId, int enderecoId) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);

        if (clienteOpt.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado");
        }

        Cliente cliente = clienteOpt.get();
        boolean removed = cliente.getEnderecos().removeIf(endereco -> endereco.getId() == enderecoId);

        if (!removed) {
            throw new RuntimeException("Endereço não encontrado");
        }

        clienteRepository.save(cliente);
    }
}