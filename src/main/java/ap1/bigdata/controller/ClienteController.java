package ap1.bigdata.controller;

import ap1.bigdata.controller.dto.AtualizarClienteRequest;
import ap1.bigdata.controller.dto.EnderecoRequest;
import ap1.bigdata.controller.dto.IncluirClienteRequest;
import ap1.bigdata.controller.dto.IncluirClienteResponse;
import ap1.bigdata.model.Cliente;
import ap1.bigdata.model.Endereco;
import ap1.bigdata.service.ClienteService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return new ResponseEntity<>(clienteService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> ler(@PathVariable("id") int id) {
        return new ResponseEntity<>(clienteService.getCliente(id), HttpStatus.OK);
    }
 @PostMapping
public ResponseEntity<IncluirClienteResponse> incluir(@RequestBody IncluirClienteRequest incluirClienteRequest) {
    List<Endereco> enderecos = incluirClienteRequest.getEnderecos().stream().map(enderecoRequest -> {
        Endereco endereco = new Endereco();
        endereco.setRua(enderecoRequest.getRua());
        endereco.setNumero(enderecoRequest.getNumero()); 
        endereco.setBairro(enderecoRequest.getBairro()); 
        endereco.setCidade(enderecoRequest.getCidade());
        endereco.setEstado(enderecoRequest.getEstado());
        endereco.setCep(enderecoRequest.getCep());
        return endereco;
    }).collect(Collectors.toList());
    
    Cliente cliente = new Cliente(
        incluirClienteRequest.getNome(),
        incluirClienteRequest.getCpf(),
        incluirClienteRequest.getEmail(),
        incluirClienteRequest.getDataNasc(),
        incluirClienteRequest.getTelefone(),
        enderecos
    );
    
    clienteService.incluir(cliente);
    
    IncluirClienteResponse response = new IncluirClienteResponse();
    response.setId(cliente.getId());
    response.setNome(cliente.getNome());
    response.setTelefone(cliente.getTelefone());
    response.setCpf(cliente.getCpf());
    response.setEmail(cliente.getEmail());
    response.setDataNasc(cliente.getDataNasc());
    response.setEnderecos(converterEnderecos(cliente.getEnderecos()));
    response.setDataCadastro(Instant.now());
    response.setUltimaAtualizacao(Instant.now());
    
    return ResponseEntity.ok(response);
}

private List<EnderecoRequest> converterEnderecos(List<Endereco> enderecos) {
    return enderecos.stream().map(endereco -> {
        EnderecoRequest enderecoRequest = new EnderecoRequest();
        enderecoRequest.setRua(endereco.getRua());
        enderecoRequest.setNumero(endereco.getNumero());
        enderecoRequest.setBairro(endereco.getBairro());
        enderecoRequest.setCidade(endereco.getCidade());
        enderecoRequest.setEstado(endereco.getEstado());
        enderecoRequest.setCep(endereco.getCep());
        return enderecoRequest;
    }).collect(Collectors.toList());
}

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable("id") int id, @RequestBody AtualizarClienteRequest atualizarClienteRequest) {
        Cliente clienteAtualizado = clienteService.atualizar(id, atualizarClienteRequest);
        return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") int id) {
        clienteService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
