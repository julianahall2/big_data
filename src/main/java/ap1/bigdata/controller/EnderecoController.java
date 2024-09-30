package ap1.bigdata.controller;

import ap1.bigdata.model.Endereco;
import ap1.bigdata.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<Endereco>> getAllEnderecos() {
        return new ResponseEntity<>(enderecoService.getAllEnderecos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> getEnderecoById(@PathVariable("id") int id) {
        return enderecoService.getEnderecoById(id)
                .map(endereco -> new ResponseEntity<>(endereco, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Endereco> createEndereco(@RequestBody Endereco endereco) {
        return new ResponseEntity<>(enderecoService.createEndereco(endereco), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> updateEndereco(@PathVariable("id") int id, @RequestBody Endereco endereco) {
        return enderecoService.getEnderecoById(id)
                .map(enderecoExistente -> {
                    enderecoExistente.setRua(endereco.getRua());
                    enderecoExistente.setNumero(endereco.getNumero());
                    enderecoExistente.setBairro(endereco.getBairro());
                    enderecoExistente.setCidade(endereco.getCidade());
                    enderecoExistente.setEstado(endereco.getEstado());
                    enderecoExistente.setCep(endereco.getCep());
                    return new ResponseEntity<>(enderecoService.updateEndereco(enderecoExistente), HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable("id") int id) {
        enderecoService.deleteEndereco(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}