package servico;

import java.sql.Connection; // Importa a classe para manipular conexões com o banco de dados
import java.sql.DriverManager; // Importa a classe responsável por gerenciar o driver JDBC
import java.sql.SQLException; // Importa a exceção para tratar erros relacionados ao banco de dados

// Classe responsável por gerenciar a conexão com o banco de dados
public class ConexaoBanco {
    // URL de conexão com o banco de dados
    private static final String URL = "jdbc:postgresql://localhost:5432/Aluguel";

    // Usuário do banco de dados
    private static final String USER = "postgres";

    // Senha do banco de dados
    private static final String PASSWORD = "123qwe";

    // Método estático para obter a conexão com o banco de dados
    // Retorna uma instância de Connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD); // Cria uma conexão usando os parâmetros fornecidos
    }

    // Método estático para testar a conexão com o banco de dados
    public static void testarConexao() {
        try (Connection conn = getConnection()) { // Tenta obter uma conexão
            System.out.println("Conexão bem-sucedida!"); // Exibe mensagem de sucesso se a conexão funcionar
        } catch (SQLException e) { // Captura e trata erros de conexão
            System.err.println("Erro de conexão: " + e.getMessage()); // Exibe mensagem de erro detalhada
        }
    }
}
