package ap1.bigdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ap1.bigdata.model.Cliente;
import ap1.bigdata.model.Endereco;
import ap1.bigdata.repository.EnderecoRepository;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    
    @Autowired
    private EnderecoRepository enderecoRepository;
    
    // Adiciona um endereço a um cliente
    @PostMapping("/cliente/{clienteId}")
    public ResponseEntity<Endereco> adicionarEndereco(@PathVariable int clienteId, @Valid @RequestBody Endereco endereco) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);
        if (clienteOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Cliente cliente = clienteOpt.get();
        endereco.setCliente(cliente);
        enderecoRepository.save(endereco);
        return new ResponseEntity<>(endereco, HttpStatus.CREATED);
    }

    // Atualiza um endereço
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable int id, @Valid @RequestBody Endereco enderecoAtualizado) {
        return enderecoRepository.findById(id)
            .map(endereco -> {
                endereco.setRua(enderecoAtualizado.getRua());
                endereco.setNumero(enderecoAtualizado.getNumero());
                endereco.setBairro(enderecoAtualizado.getBairro());
                endereco.setCidade(enderecoAtualizado.getCidade());
                endereco.setEstado(enderecoAtualizado.getEstado());
                endereco.setCep(enderecoAtualizado.getCep());
                enderecoRepository.save(endereco);
                return new ResponseEntity<>(endereco, HttpStatus.OK);
            })
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Remove um endereço
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable int id) {
        Optional<Endereco> enderecoOpt = enderecoRepository.findById(id);
        if (enderecoOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        enderecoRepository.delete(enderecoOpt.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
