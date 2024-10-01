package ap1.bigdata.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtualizarClienteRequest {
    private int id;
    private String nome;
    private String telefone;
    private String cpf;
    private String email;
    private LocalDate dataNasc;  
    private List<EnderecoRequest> enderecos; 
}
