package ap1.bigdata.model;


import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;


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
    // Construtor vazio
    public Cliente() {}

    // Construtor completo
    public Cliente(String nome, String cpf, String email, LocalDateTime dataNasc, String telefone, List<Endereco> enderecos) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNasc = dataNasc;
        this.telefone = telefone;
        this.enderecos = enderecos;
    }

    // MÉTODOS PARA CLIENTE

    // Getters e Setters para os atributos do cliente
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

    public LocalDateTime getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDateTime dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // MÉTODOS PARA ENDEREÇOS

    // Retorna a lista de endereços associados ao cliente
    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    // Define a lista de endereços
    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }


    // Método para atualizar um endereço com base no ID do endereço
    public boolean atualizarEndereco(int enderecoId, Endereco enderecoAtualizado) {
        Optional<Endereco> enderecoOpt = this.enderecos.stream()
                                                       .filter(e -> e.getId() == enderecoId)
                                                       .findFirst();

        if (enderecoOpt.isPresent()) {
            Endereco endereco = enderecoOpt.get();
            endereco.setRua(enderecoAtualizado.getRua());
            endereco.setNumero(enderecoAtualizado.getNumero());
            endereco.setBairro(enderecoAtualizado.getBairro());
            endereco.setCidade(enderecoAtualizado.getCidade());
            endereco.setEstado(enderecoAtualizado.getEstado());
            endereco.setCep(enderecoAtualizado.getCep());
            return true;
        }
        return false;
    }

    // Método para remover um endereço da lista baseado no ID
    public boolean removerEndereco(int enderecoId) {
        return this.enderecos.removeIf(endereco -> endereco.getId() == enderecoId);
    }

    // Método para buscar um endereço específico por ID
    public Optional<Endereco> buscarEndereco(int enderecoId) {
        return this.enderecos.stream()
                             .filter(endereco -> endereco.getId() == enderecoId)
                             .findFirst();
    }

    // Método para remover todos os endereços do cliente
    public void removerTodosEnderecos() {
        this.enderecos.clear();
    }
}
