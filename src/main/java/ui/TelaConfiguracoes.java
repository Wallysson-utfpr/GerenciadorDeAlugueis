package ui;

import javafx.application.Application; // Importa a classe base para JavaFX
import javafx.geometry.Insets; // Gerencia margens internas
import javafx.geometry.Pos; // Gerencia o alinhamento dos elementos
import javafx.scene.Scene; // Representa a cena exibida no Stage
import javafx.scene.control.Button; // Importa botão para interações
import javafx.scene.control.Label; // Importa rótulo para exibição de texto
import javafx.scene.layout.*; // Gerencia layouts
import javafx.scene.paint.Color; // Permite o uso de cores
import javafx.scene.text.Font; // Gerencia fontes de texto
import javafx.stage.Stage; // Representa a janela principal da aplicação

// Classe que define a tela de Configurações
public class TelaConfiguracoes extends Application {

    // Método para criar a cena de Configurações
    public Scene createConfiguracoesScene(Stage stage) {
        // Barra superior com título "PROTECH" e botão "X" para voltar
        Label protechLabel = new Label("PROTECH");
        protechLabel.setFont(new Font("Arial", 24)); // Define a fonte do título
        protechLabel.setTextFill(Color.BLACK); // Define a cor do texto

        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
        closeButton.setOnAction(e -> {
            TelaRelatorios telaRelatorios = new TelaRelatorios();
            stage.setScene(telaRelatorios.createRelatoriosScene(stage)); // Troca para a tela de relatórios
        });

        // Layout da barra superior
        HBox topBar = new HBox(10, protechLabel, new Region(), closeButton);
        HBox.setHgrow(topBar.getChildren().get(1), Priority.ALWAYS); // Define espaço entre título e botão
        topBar.setPadding(new Insets(5, 20, 5, 10)); // Define margens internas
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setBackground(new Background(new BackgroundFill(Color.web("#A0A0A0"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Título central "CONFIGURAÇÕES"
        Label titleLabel = new Label("CONFIGURAÇÕES");
        titleLabel.setFont(new Font("Arial", 30)); // Fonte maior para o título principal
        titleLabel.setTextFill(Color.BLACK); // Define a cor do texto
        HBox titleContainer = new HBox(titleLabel);
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.setPadding(new Insets(10)); // Define espaçamento ao redor do título
        titleContainer.setBackground(new Background(new BackgroundFill(Color.web("#A0A0A0"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Container que une barra superior e título
        VBox topContainer = new VBox(topBar, titleContainer);
        topContainer.setBackground(new Background(new BackgroundFill(Color.web("#707070"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Botões de ação
        Button cadastrarUsuarioButton = new Button("CADASTRAR USUÁRIO");
        cadastrarUsuarioButton.setStyle("-fx-background-color: #004C4C; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px; -fx-cursor: hand;");
        cadastrarUsuarioButton.setOnAction(e -> openUsuarioScene(stage, false)); // Abre tela de cadastro de usuário

        Button alterarSenhaButton = new Button("ALTERAR SENHA");
        alterarSenhaButton.setStyle("-fx-background-color: #004C4C; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px; -fx-cursor: hand;");
        alterarSenhaButton.setOnAction(e -> openUsuarioScene(stage, true)); // Abre tela de alteração de senha

        // Container para os botões
        VBox buttonContainer = new VBox(15, cadastrarUsuarioButton, alterarSenhaButton);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPadding(new Insets(20));
        buttonContainer.setBackground(new Background(new BackgroundFill(Color.web("#C0C0C0"), new CornerRadii(10), Insets.EMPTY)));

        // Layout principal
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topContainer);
        mainLayout.setCenter(buttonContainer);

        // Criação da cena
        Scene scene = new Scene(mainLayout, stage.getWidth(), stage.getHeight());
        stage.widthProperty().addListener((obs, oldVal, newVal) -> scene.setRoot(mainLayout)); // Atualiza layout ao redimensionar janela
        stage.heightProperty().addListener((obs, oldVal, newVal) -> scene.setRoot(mainLayout));
        stage.setMaximized(true); // Define a janela como maximizada

        return scene;
    }

    // Método para abrir a tela de cadastro ou alteração de senha de usuários
    private void openUsuarioScene(Stage stage, boolean isAlterarSenha) {
        TelaCadastroUsuarios TelaCadastroUsuarios = new TelaCadastroUsuarios();
        stage.setScene(TelaCadastroUsuarios.createUsuarioScene(stage, isAlterarSenha)); // Troca para a tela correspondente
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Configurações"); // Define o título da janela
        primaryStage.setScene(createConfiguracoesScene(primaryStage)); // Define a cena inicial
        primaryStage.setWidth(800); // Define largura inicial da janela
        primaryStage.setHeight(600); // Define altura inicial da janela
        primaryStage.show(); // Exibe a janela
    }

    public static void main(String[] args) {
        launch(args); // Inicia a aplicação
    }
}
