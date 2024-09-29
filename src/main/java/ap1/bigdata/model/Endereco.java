package ap1.bigdata.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Rua não pode ser vazia")
    @Size(min = 3, max = 255, message = "Rua deve ter entre 3 e 255 caracteres")
    private String rua;

    @Column(nullable = false)
    @NotBlank(message = "Número não pode ser vazio")
    private String numero;

    @Column(nullable = false)
    @NotBlank(message = "Bairro não pode ser vazio")
    @Size(min = 3, max = 100, message = "Bairro deve ter entre 3 e 100 caracteres")
    private String bairro;

    @Column(nullable = false)
    @NotBlank(message = "Cidade não pode ser vazia")
    @Size(min = 2, max = 100, message = "Cidade deve ter entre 2 e 100 caracteres")
    private String cidade;

    @Column(nullable = false)
    @NotBlank(message = "Estado não pode ser vazio")
    @Pattern(regexp = "SP|RJ|MG|...", message = "Estado inválido")
    private String estado;

    @Column(nullable = false)
    @NotBlank(message = "CEP não pode ser vazio")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Formato de CEP inválido")
    private String cep;

}
