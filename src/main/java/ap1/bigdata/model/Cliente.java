package ap1.bigdata.model;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private LocalDate dataNasc;

    @Column
    @Pattern(
        regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", 
        message = "O telefone deve estar no formato (XX) XXXXX-XXXX."
    )
    private String telefone;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    public void adicionarEndereco(Endereco endereco) {
        if (this.enderecos == null) {
        this.enderecos = new ArrayList<>();
    }
    this.enderecos.add(endereco);

}

    public Cliente() {}

    public Cliente(String nome, String cpf, String email, LocalDate dataNasc, String telefone, List<Endereco> enderecos) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNasc = dataNasc;
        this.telefone = telefone;
        this.enderecos = enderecos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Getter para enderecos
    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    // Setter para enderecos
    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }


}