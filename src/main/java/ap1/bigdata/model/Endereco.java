package ap1.bigdata.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

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

    // Construtor padrão
    public Endereco() {}

    // Construtor com parâmetros
    public Endereco(String rua, String numero, String bairro, String cidade, String estado, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    // Método para atualizar os dados do endereço
    public void atualizarEndereco(String rua, String numero, String bairro, String cidade, String estado, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
