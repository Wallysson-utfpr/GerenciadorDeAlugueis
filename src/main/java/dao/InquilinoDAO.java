package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import modelo.Inquilino;
import servico.ConexaoBanco;

public class InquilinoDAO {

    // Método para converter uma String no formato dd/MM/yyyy ou ddMMyyyy para java.sql.Date
    private Date convertStringToDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) { // Verifica se a string está vazia ou nula
            return null;
        }

        // Adiciona barras para formatos compactos como ddMMyyyy
        if (dateStr.matches("\\d{8}")) { // Verifica se a string tem exatamente 8 dígitos
            dateStr = dateStr.substring(0, 2) + "/" + dateStr.substring(2, 4) + "/" + dateStr.substring(4); // Transforma para dd/MM/yyyy
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Define o formato esperado
            java.util.Date date = sdf.parse(dateStr); // Converte para java.util.Date
            return new Date(date.getTime()); // Retorna a data no formato java.sql.Date
        } catch (ParseException e) {
            // Trata erros na conversão de data
            System.err.println("Erro ao converter string para data: " + e.getMessage());
            return null;
        }
    }

    // Método para inserir um novo inquilino no banco de dados
    public void inserirInquilino(Inquilino inquilino) {
        // Comando SQL para inserir dados na tabela "inquilinos"
        String sql = "INSERT INTO inquilinos (nome, imovel_alugado, cpf, telefone, data_inicio_contrato, data_fim_contrato) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConnection(); // Obtém a conexão com o banco de dados
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara o comando SQL
            // Define os valores dos parâmetros no comando SQL
            stmt.setString(1, inquilino.getNome()); // Nome do inquilino
            stmt.setString(2, inquilino.getImovelAlugado()); // Identificação do imóvel alugado
            stmt.setString(3, inquilino.getCpf()); // CPF do inquilino
            stmt.setString(4, inquilino.getTelefone()); // Telefone do inquilino

            // Convertendo e configurando as datas de contrato
            Date dataInicio = convertStringToDate(inquilino.getDataInicioContrato()); // Data de início do contrato
            Date dataFim = convertStringToDate(inquilino.getDataFimContrato()); // Data de término do contrato

            if (dataInicio != null) {
                stmt.setDate(5, dataInicio); // Configura a data de início do contrato
            } else {
                stmt.setNull(5, java.sql.Types.DATE); // Insere NULL se a data de início for inválida ou vazia
            }

            if (dataFim != null) {
                stmt.setDate(6, dataFim); // Configura a data de término do contrato
            } else {
                stmt.setNull(6, java.sql.Types.DATE); // Insere NULL se a data de término for inválida ou vazia
            }

            stmt.executeUpdate(); // Executa o comando SQL
        } catch (SQLException e) {
            // Trata erros na execução do comando SQL
            System.err.println("Erro ao inserir inquilino: " + e.getMessage());
        }
    }
}
