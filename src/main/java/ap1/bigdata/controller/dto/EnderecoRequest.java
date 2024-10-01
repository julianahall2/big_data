package ap1.bigdata.controller.dto;

import lombok.Data;

@Data
public class EnderecoRequest {
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public EnderecoRequest() {}
}