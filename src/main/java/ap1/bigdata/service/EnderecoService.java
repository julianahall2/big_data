package ap1.bigdata.service;

import ap1.bigdata.model.Cliente;
import ap1.bigdata.model.Endereco;
import ap1.bigdata.repository.ClienteRepository;
import ap1.bigdata.repository.EnderecoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;

    public EnderecoService(EnderecoRepository enderecoRepository, ClienteRepository clienteRepository) {
        this.enderecoRepository = enderecoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Endereco> getAllEnderecos() {
        return enderecoRepository.findAll();
    }

    public List<Endereco> getEnderecosByClienteId(int clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return cliente.getEnderecos();
    }

    public Optional<Endereco> getEnderecoByClienteIdAndEnderecoId(int clienteId, int enderecoId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return cliente.getEnderecos().stream().filter(e -> e.getId() == enderecoId).findFirst();
    }

    public Endereco createEndereco(int clienteId, Endereco endereco) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        endereco.setCliente(cliente);
        return enderecoRepository.save(endereco);
    }

    public Endereco updateEndereco(int clienteId, int enderecoId, Endereco endereco) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Endereco enderecoExistente = cliente.getEnderecos().stream().filter(e -> e.getId() == enderecoId).findFirst().orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        endereco.setId(enderecoId);
        endereco.setCliente(cliente);
        return enderecoRepository.save(endereco);
    }

    public void deleteEndereco(int clienteId, int enderecoId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Endereco endereco = cliente.getEnderecos().stream().filter(e -> e.getId() == enderecoId).findFirst().orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        enderecoRepository.deleteById(enderecoId);
    }
}
