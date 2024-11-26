package dao;

import servico.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Imovel;

public class ImovelDAO {

    // Método para inserir um novo imóvel no banco de dados
    public void inserirImovel(Imovel imovel) {
        // Comando SQL para inserir dados na tabela "imoveis"
        String sql = "INSERT INTO imoveis (nome, tipo, cep, endereco, numero, complemento) VALUES (?, ?, ?, ?, ?, ?)";

        // Conexão com o banco de dados e execução do comando SQL
        try (Connection conn = ConexaoBanco.getConnection(); // Obtém conexão com o banco
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara o comando SQL

            // Define os valores dos parâmetros no comando SQL
            stmt.setString(1, imovel.getNome()); // Nome do imóvel
            stmt.setString(2, imovel.getTipo()); // Tipo do imóvel (ex: casa, apartamento)
            stmt.setString(3, imovel.getCep()); // CEP do endereço do imóvel
            stmt.setString(4, imovel.getEndereco()); // Endereço do imóvel
            stmt.setString(5, imovel.getNumero()); // Número do imóvel
            stmt.setString(6, imovel.getComplemento()); // Complemento do endereço (ex: apto, bloco)

            stmt.executeUpdate(); // Executa o comando SQL para inserir os dados
        } catch (SQLException e) {
            // Trata erros de SQL durante a operação
            System.err.println("Erro ao inserir imóvel: " + e.getMessage());
        }
    }
}
