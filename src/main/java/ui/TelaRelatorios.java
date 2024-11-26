package ui;

import javafx.geometry.Insets; // Gerencia margens internas
import javafx.geometry.Pos; // Define alinhamento
import javafx.scene.Scene; // Representa a cena exibida no Stage
import javafx.scene.control.Button; // Botões de interação
import javafx.scene.control.Label; // Texto exibido na tela
import javafx.scene.image.Image; // Manipula imagens
import javafx.scene.image.ImageView; // Exibe imagens
import javafx.scene.layout.*; // Gerencia layouts
import javafx.scene.paint.Color; // Permite uso de cores
import javafx.scene.text.Font; // Gerencia fontes de texto
import javafx.stage.Stage; // Representa a janela principal
import javafx.geometry.Rectangle2D; // Define regiões retangulares
import javafx.stage.Screen; // Permite acessar propriedades da tela

// Classe que implementa a tela de relatórios
public class TelaRelatorios {

    // Método principal para criar a cena de relatórios
    public Scene createRelatoriosScene(Stage stage) {
        // Barra superior contendo o título "PROTECH" e "RELATÓRIOS"
        HBox topBar = createTopBar(stage);

        // Container para os botões principais da tela
        VBox buttonContainer = createButtonContainer(stage);

        // Ícones na parte inferior da tela
        HBox bottomIcons = createBottomIcons(stage);

        // Layout principal utilizando BorderPane
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topBar); // Define a barra superior no topo
        mainLayout.setCenter(buttonContainer); // Define os botões no centro
        mainLayout.setBottom(bottomIcons); // Define os ícones na parte inferior
        mainLayout.setBackground(new Background(new BackgroundFill(Color.web("#C0C0C0"), CornerRadii.EMPTY, Insets.EMPTY))); // Cor de fundo

        // Criação da cena e configuração para adaptar ao tamanho da janela
        Scene scene = new Scene(mainLayout, stage.getWidth(), stage.getHeight());
        stage.widthProperty().addListener((obs, oldVal, newVal) -> scene.setRoot(mainLayout));
        stage.heightProperty().addListener((obs, oldVal, newVal) -> scene.setRoot(mainLayout));
        stage.setMaximized(true); // Maximiza a janela

        return scene;
    }

    // Método para criar a barra superior com título e botão de fechar
    private HBox createTopBar(Stage stage) {
        Label protechLabel = new Label("PROTECH");
        protechLabel.setFont(new Font("Arial", 20));
        protechLabel.setTextFill(Color.BLACK);

        Label titleLabel = new Label("RELATÓRIOS");
        titleLabel.setFont(new Font("Arial", 22));
        titleLabel.setTextFill(Color.BLACK);

        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-background-color: #FF4C4C; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 5; -fx-border-radius: 3px;");
        closeButton.setOnAction(e -> {
            TelaLogin telaLogin = new TelaLogin();
            stage.setScene(telaLogin.createLoginScene(stage));

            // Configura a janela para ocupar toda a tela primária
            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMinY());
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());
        });

        // Espaçadores para alinhar elementos
        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        HBox topBar = new HBox(10, protechLabel, leftSpacer, titleLabel, rightSpacer, closeButton);
        topBar.setPadding(new Insets(5, 20, 5, 10));
        topBar.setAlignment(Pos.CENTER);
        topBar.setBackground(new Background(new BackgroundFill(Color.web("#808080"), CornerRadii.EMPTY, Insets.EMPTY))); // Cor cinza para a barra superior

        return topBar;
    }

    // Método para criar os botões principais
    private VBox createButtonContainer(Stage stage) {
        Button btnApt = createStyledButton("APARTAMENTOS");
        btnApt.setOnAction(e -> {
            TelaCadastroApartamentos TelaCadastroApartamentos = new TelaCadastroApartamentos();
            stage.setScene(TelaCadastroApartamentos.createCadastroApartamentosScene(stage));
        });

        Button btnCustos = createStyledButton("CUSTOS");
        Button btnReceitas = createStyledButton("RECEITAS");
        Button btnAnalisePagamentos = createStyledButton("ANÁLISE DE PAGAMENTOS");

        VBox buttonContainer = new VBox(15, btnCustos, btnReceitas, btnAnalisePagamentos, btnApt);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPadding(new Insets(20));
        buttonContainer.setStyle("-fx-background-color: #C0C0C0; -fx-border-radius: 12px; -fx-padding: 15;");
        return buttonContainer;
    }

    // Método para criar os ícones inferiores
    private HBox createBottomIcons(Stage stage) {
        ImageView iconConfig = createIcon("/images/icon1.png", "Configuração", () -> {
            TelaConfiguracoes telaConfiguracoes = new TelaConfiguracoes();
            stage.setScene(telaConfiguracoes.createConfiguracoesScene(stage));
        });
        ImageView iconImoveis = createIcon("/images/icon2.png", "Imóveis", () -> {
            TelaCadastroImoveis telaCadastroImoveis = new TelaCadastroImoveis();
            stage.setScene(telaCadastroImoveis.createCadastroImoveisScene(stage));
        });
        ImageView iconInquilinos = createIcon("/images/icon3.png", "Inquilinos", () -> {
            TelaCadastroInquilinos telaCadastroInquilinos = new TelaCadastroInquilinos();
            stage.setScene(telaCadastroInquilinos.createCadastroInquilinosScene(stage));
        });
        ImageView iconTabela = createIcon("/images/icon4.png", "Tabela", () -> {
            TelaTabela telaTabela = new TelaTabela();
            stage.setScene(telaTabela.createTabelaScene(stage));
        });

        HBox bottomIcons = new HBox(30, iconConfig, iconImoveis, iconInquilinos, iconTabela);
        bottomIcons.setAlignment(Pos.CENTER);
        bottomIcons.setPadding(new Insets(15, 0, 50, 0));
        bottomIcons.setBackground(new Background(new BackgroundFill(Color.web("#808080"), CornerRadii.EMPTY, Insets.EMPTY))); // Cor cinza para o fundo
        return bottomIcons;
    }

    // Método para criar botões estilizados
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #00BFFF; -fx-text-fill: black; -fx-padding: 10 20; "
                + "-fx-border-radius: 8px; -fx-background-radius: 8px; -fx-font-size: 14px; -fx-border-color: black; -fx-border-width: 1px; -fx-cursor: hand;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #1E90FF; -fx-text-fill: black; -fx-padding: 10 20; "
                + "-fx-border-radius: 8px; -fx-background-radius: 8px; -fx-font-size: 14px; -fx-border-color: black; -fx-border-width: 1px; -fx-cursor: hand;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #00BFFF; -fx-text-fill: black; -fx-padding: 10 20; "
                + "-fx-border-radius: 8px; -fx-background-radius: 8px; -fx-font-size: 14px; -fx-border-color: black; -fx-border-width: 1px; -fx-cursor: hand;"));
        return button;
    }

    // Método para criar ícones clicáveis
    private ImageView createIcon(String imagePath, String message, Runnable onClickAction) {
        ImageView icon = new ImageView();
        try {
            icon.setImage(new Image(getClass().getResource(imagePath).toExternalForm()));
        } catch (NullPointerException e) {
            System.err.println("Erro ao carregar o ícone: " + imagePath);
        }
        icon.setFitWidth(30);
        icon.setFitHeight(30);
        icon.setOnMouseClicked(e -> {
            System.out.println(message); // Log da ação
            if (onClickAction != null) {
                onClickAction.run();
            }
        });
        icon.setStyle("-fx-cursor: hand;"); // Define o cursor como "mão" ao passar sobre o ícone
        return icon;
    }
}
