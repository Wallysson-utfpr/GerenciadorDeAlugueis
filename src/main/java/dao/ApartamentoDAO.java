package dao;

import modelo.Apartamento;
import servico.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartamentoDAO {

    // Método para cadastrar um novo apartamento no banco de dados
    public void cadastrarApartamento(Apartamento apartamento) {
        // Comando SQL para inserir dados na tabela "apartamentos"
        String sql = "INSERT INTO apartamentos (numero, bloco, area, proprietario, descricao, mes_competencia, aluguel, garagem, valor_condominio, " +
                "consumo_agua, valor_agua, consumo_gas, valor_gas, valor_luz, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Conexão com o banco de dados e execução do comando SQL
        try (Connection conn = ConexaoBanco.getConnection(); // Obtém conexão com o banco
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara o comando SQL
            // Define os valores dos parâmetros no comando SQL
            stmt.setString(1, apartamento.getNumero());
            stmt.setString(2, apartamento.getBloco());
            stmt.setDouble(3, apartamento.getArea());
            stmt.setString(4, apartamento.getProprietario());
            stmt.setString(5, apartamento.getDescricao());
            stmt.setString(6, apartamento.getMesCompetencia());
            stmt.setDouble(7, apartamento.getAluguel());
            stmt.setDouble(8, apartamento.getGaragem());
            stmt.setDouble(9, apartamento.getValorCondominio());
            stmt.setDouble(10, apartamento.getConsumoAgua());
            stmt.setDouble(11, apartamento.getValorAgua());
            stmt.setDouble(12, apartamento.getConsumoGas());
            stmt.setDouble(13, apartamento.getValorGas());
            stmt.setDouble(14, apartamento.getValorLuz());
            stmt.setDouble(15, apartamento.getTotal());
            stmt.executeUpdate(); // Executa o comando SQL
        } catch (SQLException e) {
            // Trata erros de SQL durante a operação
            System.err.println("Erro ao cadastrar apartamento: " + e.getMessage());
        }
    }

    // Método para listar todos os apartamentos do banco de dados
    public List<Apartamento> listarApartamentos() {
        // Comando SQL para buscar todos os registros da tabela "apartamentos"
        String sql = "SELECT * FROM apartamentos";
        List<Apartamento> apartamentos = new ArrayList<>(); // Lista para armazenar os resultados

        // Conexão com o banco de dados e execução da consulta
        try (Connection conn = ConexaoBanco.getConnection(); // Obtém conexão com o banco
             Statement stmt = conn.createStatement(); // Cria um Statement para executar a consulta
             ResultSet rs = stmt.executeQuery(sql)) { // Executa a consulta e armazena o resultado em ResultSet

            // Itera sobre o resultado da consulta para construir objetos Apartamento
            while (rs.next()) {
                Apartamento apartamento = new Apartamento(
                        rs.getString("numero"), // Número do apartamento
                        rs.getString("bloco"), // Bloco do apartamento
                        rs.getDouble("area"), // Área do apartamento
                        rs.getString("proprietario"), // Nome do proprietário
                        rs.getString("descricao"), // Descrição do apartamento
                        rs.getString("mes_competencia"), // Mês de competência
                        rs.getDouble("aluguel"), // Valor do aluguel
                        rs.getDouble("garagem"), // Valor da garagem
                        rs.getDouble("valor_condominio"), // Valor do condomínio
                        rs.getDouble("consumo_agua"), // Consumo de água
                        rs.getDouble("valor_agua"), // Valor da água
                        rs.getDouble("consumo_gas"), // Consumo de gás
                        rs.getDouble("valor_gas"), // Valor do gás
                        rs.getDouble("valor_luz"), // Valor da luz
                        rs.getDouble("total") // Valor total
                );
                apartamentos.add(apartamento); // Adiciona o objeto à lista
            }
            System.out.println("Apartamentos listados com sucesso!"); // Mensagem de sucesso
        } catch (SQLException e) {
            // Trata erros de SQL durante a operação
            System.err.println("Erro ao listar apartamentos: " + e.getMessage());
        }

        return apartamentos; // Retorna a lista de apartamentos
    }
}
