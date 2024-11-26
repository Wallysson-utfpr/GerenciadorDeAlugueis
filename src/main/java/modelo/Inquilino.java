package modelo;

// Classe que representa um inquilino
public class Inquilino {
    // Atributos da classe
    private String nome; // Nome do inquilino
    private String imovelAlugado; // Identificação do imóvel alugado
    private String cpf; // CPF do inquilino
    private String telefone; // Telefone de contato do inquilino
    private String dataInicioContrato; // Data de início do contrato de aluguel
    private String dataFimContrato; // Data de término do contrato de aluguel

    // Construtor para inicializar os atributos do inquilino
    public Inquilino(String nome, String imovelAlugado, String cpf, String telefone, String dataInicioContrato, String dataFimContrato) {
        this.nome = nome; // Inicializa o nome do inquilino
        this.imovelAlugado = imovelAlugado; // Inicializa o imóvel alugado
        this.cpf = cpf; // Inicializa o CPF do inquilino
        this.telefone = telefone; // Inicializa o telefone do inquilino
        this.dataInicioContrato = dataInicioContrato; // Inicializa a data de início do contrato
        this.dataFimContrato = dataFimContrato; // Inicializa a data de término do contrato
    }

    // Getters e Setters para acessar e modificar os atributos

    // Retorna o nome do inquilino
    public String getNome() {
        return nome;
    }

    // Define o nome do inquilino
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Retorna o imóvel alugado pelo inquilino
    public String getImovelAlugado() {
        return imovelAlugado;
    }

    // Define o imóvel alugado pelo inquilino
    public void setImovelAlugado(String imovelAlugado) {
        this.imovelAlugado = imovelAlugado;
    }

    // Retorna o CPF do inquilino
    public String getCpf() {
        return cpf;
    }

    // Define o CPF do inquilino
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Retorna o telefone do inquilino
    public String getTelefone() {
        return telefone;
    }

    // Define o telefone do inquilino
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Retorna a data de início do contrato
    public String getDataInicioContrato() {
        return dataInicioContrato;
    }

    // Define a data de início do contrato
    public void setDataInicioContrato(String dataInicioContrato) {
        this.dataInicioContrato = dataInicioContrato;
    }

    // Retorna a data de término do contrato
    public String getDataFimContrato() {
        return dataFimContrato;
    }

    // Define a data de término do contrato
    public void setDataFimContrato(String dataFimContrato) {
        this.dataFimContrato = dataFimContrato;
    }
}
