package ap1.bigdata.service;

import ap1.bigdata.model.Endereco;
import ap1.bigdata.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> getAllEnderecos() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> getEnderecoById(int id) {
        return enderecoRepository.findById(id);
    }

    public Endereco createEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco updateEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void deleteEndereco(int id) {
        enderecoRepository.deleteById(id);
    }
}