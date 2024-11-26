package modelo;

// Classe que representa um imóvel
public class Imovel {
    // Atributos da classe
    private String nome; // Nome do imóvel
    private String tipo; // Tipo do imóvel (ex: casa, apartamento, terreno)
    private String cep; // CEP do endereço do imóvel
    private String endereco; // Endereço completo do imóvel
    private String numero; // Número do imóvel no endereço
    private String complemento; // Complemento do endereço (ex: bloco, apto)

    // Construtor para inicializar os atributos do imóvel
    public Imovel(String nome, String tipo, String cep, String endereco, String numero, String complemento) {
        this.nome = nome; // Inicializa o nome do imóvel
        this.tipo = tipo; // Inicializa o tipo do imóvel
        this.cep = cep; // Inicializa o CEP do imóvel
        this.endereco = endereco; // Inicializa o endereço completo do imóvel
        this.numero = numero; // Inicializa o número do imóvel
        this.complemento = complemento; // Inicializa o complemento do endereço
    }

    // Getters e Setters para acessar e modificar os atributos

    // Retorna o nome do imóvel
    public String getNome() {
        return nome;
    }

    // Define o nome do imóvel
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Retorna o tipo do imóvel
    public String getTipo() {
        return tipo;
    }

    // Define o tipo do imóvel
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Retorna o CEP do imóvel
    public String getCep() {
        return cep;
    }

    // Define o CEP do imóvel
    public void setCep(String cep) {
        this.cep = cep;
    }

    // Retorna o endereço do imóvel
    public String getEndereco() {
        return endereco;
    }

    // Define o endereço do imóvel
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // Retorna o número do imóvel
    public String getNumero() {
        return numero;
    }

    // Define o número do imóvel
    public void setNumero(String numero) {
        this.numero = numero;
    }

    // Retorna o complemento do imóvel
    public String getComplemento() {
        return complemento;
    }

    // Define o complemento do imóvel
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
