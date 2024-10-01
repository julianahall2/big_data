package ap1.bigdata.controller;

import ap1.bigdata.model.Endereco;
import ap1.bigdata.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<List<Endereco>> listarPorCliente(@PathVariable("clienteId") int clienteId) {
        List<Endereco> enderecos = enderecoService.getEnderecosByClienteId(clienteId);
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }

    @GetMapping("/{clienteId}/enderecos/{enderecoId}")
    public ResponseEntity<Endereco> lerPorCliente(@PathVariable("clienteId") int clienteId, @PathVariable("enderecoId") int enderecoId) {
        return enderecoService.getEnderecoByClienteIdAndEnderecoId(clienteId, enderecoId)
                .map(endereco -> new ResponseEntity<>(endereco, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{clienteId}/enderecos")
    public ResponseEntity<Endereco> incluir(@PathVariable("clienteId") int clienteId, @RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoService.createEndereco(clienteId, endereco);
        return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);
    }

    @PutMapping("/{clienteId}/enderecos/{enderecoId}")
    public ResponseEntity<Endereco> atualizar(@PathVariable("clienteId") int clienteId, @PathVariable("enderecoId") int enderecoId, @RequestBody Endereco endereco) {
        Endereco enderecoAtualizado = enderecoService.updateEndereco(clienteId, enderecoId, endereco);
        return new ResponseEntity<>(enderecoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{clienteId}/enderecos/{enderecoId}")
    public ResponseEntity<Void> deletar(@PathVariable("clienteId") int clienteId, @PathVariable("enderecoId") int enderecoId) {
        enderecoService.deleteEndereco(clienteId, enderecoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
