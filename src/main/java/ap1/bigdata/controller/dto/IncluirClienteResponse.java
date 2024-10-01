package ap1.bigdata.controller.dto;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;


@Data
public class IncluirClienteResponse {
    private int id;
    private String nome;
    private String telefone;
    private String cpf;
    private String email;
    private LocalDate dataNasc;
    private List<EnderecoRequest> enderecos;
    private Instant dataCadastro;
    private Instant ultimaAtualizacao;
}
