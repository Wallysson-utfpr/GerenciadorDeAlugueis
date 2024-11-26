package org.example;

import servico.ConexaoBanco; // Importa a classe responsável pela conexão com o banco de dados
import javafx.application.Application; // Importa a classe base para criar uma aplicação JavaFX
import javafx.scene.Scene; // Importa a classe para criar uma cena no JavaFX
import javafx.stage.Stage; // Importa a classe que representa a janela principal da aplicação
import ui.TelaLogin; // Importa a classe responsável pela interface gráfica de login

// Classe principal da aplicação, que inicia a interface gráfica e a conexão com o banco de dados
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Testa a conexão com o banco de dados antes de carregar a interface gráfica
        // Isso garante que o sistema esteja pronto para funcionar antes do login
        ConexaoBanco.testarConexao();

        // Instancia a tela de login
        TelaLogin telaLogin = new TelaLogin();

        // Cria a cena inicial da aplicação usando a tela de login
        Scene scene = telaLogin.createLoginScene(primaryStage);

        // Configura a cena e o título da janela principal
        primaryStage.setScene(scene); // Define a cena no stage principal
        primaryStage.setTitle("Login"); // Define o título da janela
        primaryStage.show(); // Exibe a janela na tela
    }

    // Método principal da aplicação, chamado ao iniciar o programa
    public static void main(String[] args) {
        // Lança a aplicação JavaFX
        launch(args);
    }
}
