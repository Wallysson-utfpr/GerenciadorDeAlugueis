package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
import servico.ConexaoBanco;

public class UsuarioDAO {

    // Método para cadastrar um novo usuário no banco de dados
    public void cadastrarUsuario(Usuario usuario) {
        // Comando SQL para inserir um novo registro na tabela "usuarios"
        String sql = "INSERT INTO usuarios (nome, login, senha) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConnection(); // Obtém a conexão com o banco
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara o comando SQL
            // Define os valores dos parâmetros do comando SQL
            stmt.setString(1, usuario.getNome()); // Nome do usuário
            stmt.setString(2, usuario.getLogin()); // Login do usuário
            stmt.setString(3, usuario.getSenha()); // Senha do usuário
            stmt.executeUpdate(); // Executa o comando SQL
            System.out.println("Usuário cadastrado com sucesso!"); // Confirmação de sucesso
        } catch (SQLException e) {
            // Trata erros na execução do comando SQL
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    // Método para alterar a senha de um usuário existente no banco de dados
    public void alterarSenha(int id, String novaSenha) {
        // Comando SQL para atualizar a senha do usuário pelo ID
        String sql = "UPDATE usuarios SET senha = ? WHERE id = ?";

        try (Connection conn = ConexaoBanco.getConnection(); // Obtém a conexão com o banco
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara o comando SQL
            // Define os valores dos parâmetros do comando SQL
            stmt.setString(1, novaSenha); // Nova senha do usuário
            stmt.setInt(2, id); // ID do usuário
            int rowsAffected = stmt.executeUpdate(); // Executa o comando e verifica quantas linhas foram afetadas
            if (rowsAffected > 0) {
                System.out.println("Senha alterada com sucesso!"); // Confirmação de sucesso
            } else {
                System.err.println("Usuário não encontrado para alterar a senha."); // Mensagem para usuário não encontrado
            }
        } catch (SQLException e) {
            // Trata erros na execução do comando SQL
            System.err.println("Erro ao alterar senha: " + e.getMessage());
        }
    }

    // Método para buscar um usuário no banco de dados pelo login
    public Usuario buscarUsuarioPorLogin(String login) {
        // Comando SQL para buscar um registro na tabela "usuarios" pelo login, ignorando maiúsculas e minúsculas
        String sql = "SELECT * FROM usuarios WHERE LOWER(login) = LOWER(?)";
        Usuario usuario = null; // Inicializa o objeto que será retornado

        try (Connection conn = ConexaoBanco.getConnection(); // Obtém a conexão com o banco
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara o comando SQL
            stmt.setString(1, login); // Define o valor do parâmetro do comando SQL
            ResultSet rs = stmt.executeQuery(); // Executa o comando e armazena o resultado

            if (rs.next()) { // Verifica se encontrou algum registro
                // Cria um objeto Usuario com os dados retornados pelo banco
                usuario = new Usuario(
                        rs.getInt("id"), // ID do usuário
                        rs.getString("nome"), // Nome do usuário
                        rs.getString("login"), // Login do usuário
                        rs.getString("senha") // Senha do usuário
                );
            }
        } catch (SQLException e) {
            // Trata erros na execução do comando SQL
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
        }

        return usuario; // Retorna o objeto Usuario ou null se não foi encontrado
    }

    // Método para listar todos os usuários no banco de dados
    public List<Usuario> listarUsuarios() {
        // Comando SQL para buscar todos os registros na tabela "usuarios"
        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>(); // Lista para armazenar os resultados

        try (Connection conn = ConexaoBanco.getConnection(); // Obtém a conexão com o banco
             PreparedStatement stmt = conn.prepareStatement(sql); // Prepara o comando SQL
             ResultSet rs = stmt.executeQuery()) { // Executa o comando e armazena o resultado

            while (rs.next()) { // Itera sobre os registros retornados pelo banco
                // Cria um objeto Usuario para cada registro encontrado
                Usuario usuario = new Usuario(
                        rs.getInt("id"), // ID do usuário
                        rs.getString("nome"), // Nome do usuário
                        rs.getString("login"), // Login do usuário
                        rs.getString("senha") // Senha do usuário
                );
                usuarios.add(usuario); // Adiciona o objeto à lista
            }
        } catch (SQLException e) {
            // Trata erros na execução do comando SQL
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }

        return usuarios; // Retorna a lista de usuários
    }
}
