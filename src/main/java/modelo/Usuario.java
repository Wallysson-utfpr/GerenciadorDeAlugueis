package modelo;

// Classe que representa um usuário do sistema
public class Usuario {
    // Atributos da classe
    private int id; // Identificador único do usuário
    private String nome; // Nome do usuário
    private String login; // Login utilizado para acessar o sistema
    private String senha; // Senha do usuário

    // Construtores

    // Construtor para criar um usuário sem ID (normalmente usado ao cadastrar um novo usuário)
    public Usuario(String nome, String login, String senha) {
        this.nome = nome; // Inicializa o nome do usuário
        this.login = login; // Inicializa o login do usuário
        this.senha = senha; // Inicializa a senha do usuário
    }

    // Construtor para criar um usuário com ID (normalmente usado ao buscar um usuário já existente)
    public Usuario(int id, String nome, String login, String senha) {
        this.id = id; // Inicializa o ID do usuário
        this.nome = nome; // Inicializa o nome do usuário
        this.login = login; // Inicializa o login do usuário
        this.senha = senha; // Inicializa a senha do usuário
    }

    // Getters e Setters para acessar e modificar os atributos

    // Retorna o ID do usuário
    public int getId() {
        return id;
    }

    // Define o ID do usuário
    public void setId(int id) {
        this.id = id;
    }

    // Retorna o nome do usuário
    public String getNome() {
        return nome;
    }

    // Define o nome do usuário
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Retorna o login do usuário
    public String getLogin() {
        return login;
    }

    // Define o login do usuário
    public void setLogin(String login) {
        this.login = login;
    }

    // Retorna a senha do usuário
    public String getSenha() {
        return senha;
    }

    // Define a senha do usuário
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
