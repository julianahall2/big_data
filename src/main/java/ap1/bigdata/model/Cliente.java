package ap1.bigdata.model;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;



@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Campo nome não pode ser vazio")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Campo CPF não pode ser vazio")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "Formato de CPF inválido")
    private String cpf;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Campo e-mail não pode ser vazio")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @Column(nullable = false)
    private LocalDateTime dataNasc;

    @Column
    @Pattern(
        regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", 
        message = "O telefone deve estar no formato (XX) XXXXX-XXXX."
    )
    private String telefone;

    @OneToMany
    @JoinColumn(referencedColumnName = "id", name = "cliente_id")
    private List<Endereco> enderecos;

    public void associarEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
    }
}



