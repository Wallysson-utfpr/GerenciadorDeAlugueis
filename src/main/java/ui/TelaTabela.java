package ui;

import dao.ApartamentoDAO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modelo.Apartamento;

public class TelaTabela extends Application {

    private TableView<Apartamento> tableView = new TableView<>();

    public Scene createTabelaScene(Stage stage) {
        // Barra superior contendo o rótulo "PROTECH" e o botão de fechar "X"
        Label protechLabel = new Label("PROTECH");
        protechLabel.setFont(new Font("Arial", 20));
        protechLabel.setTextFill(Color.BLACK);

        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-background-color: #FF4C4C; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
        closeButton.setOnAction(e -> {
            TelaRelatorios telaRelatorios = new TelaRelatorios();
            stage.setScene(telaRelatorios.createRelatoriosScene(stage));
        });

        // Configuração da barra superior
        HBox topBar = new HBox(10, protechLabel, new Region(), closeButton);
        HBox.setHgrow(topBar.getChildren().get(1), Priority.ALWAYS);
        topBar.setPadding(new Insets(5, 20, 5, 10));
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setBackground(new Background(new BackgroundFill(Color.web("#D3D3D3"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Rótulo centralizado indicando "TABELAS"
        Label titleLabel = new Label("TABELAS");
        titleLabel.setFont(new Font("Arial", 28));
        titleLabel.setTextFill(Color.BLACK);

        VBox titleBox = new VBox(5, titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(10));
        titleBox.setBackground(new Background(new BackgroundFill(Color.web("#D3D3D3"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Configuração do TableView
        setupTableView();
        loadTableData();

        // Envolvendo o TableView em um ScrollPane para suporte a rolagem
        ScrollPane scrollPane = new ScrollPane(tableView);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        VBox tableContainer = new VBox(scrollPane);
        tableContainer.setPadding(new Insets(10));
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        // Ícones na parte inferior da tela
        HBox bottomIcons = createBottomIcons(stage);

        // Layout principal usando BorderPane
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(new VBox(topBar, titleBox));
        mainLayout.setCenter(tableContainer);
        mainLayout.setBottom(bottomIcons);

        // Criação da cena
        Scene scene = new Scene(mainLayout, stage.getWidth(), stage.getHeight());
        stage.widthProperty().addListener((obs, oldVal, newVal) -> scene.setRoot(mainLayout));
        stage.heightProperty().addListener((obs, oldVal, newVal) -> scene.setRoot(mainLayout));
        stage.setMaximized(true);

        return scene;
    }

    private void setupTableView() {
        // Configuração das colunas da tabela
        TableColumn<Apartamento, String> numeroCol = new TableColumn<>("NÚMERO");
        numeroCol.setCellValueFactory(data -> data.getValue().numeroProperty());

        TableColumn<Apartamento, String> blocoCol = new TableColumn<>("BLOCO");
        blocoCol.setCellValueFactory(data -> data.getValue().blocoProperty());

        TableColumn<Apartamento, Double> areaCol = new TableColumn<>("ÁREA (m²)");
        areaCol.setCellValueFactory(data -> data.getValue().areaProperty().asObject());

        TableColumn<Apartamento, String> proprietarioCol = new TableColumn<>("PROPRIETÁRIO");
        proprietarioCol.setCellValueFactory(data -> data.getValue().proprietarioProperty());

        TableColumn<Apartamento, String> descricaoCol = new TableColumn<>("DESCRIÇÃO");
        descricaoCol.setCellValueFactory(data -> data.getValue().descricaoProperty());

        TableColumn<Apartamento, String> mesCompetenciaCol = new TableColumn<>("MÊS COMPETÊNCIA");
        mesCompetenciaCol.setCellValueFactory(data -> data.getValue().mesCompetenciaProperty());

        TableColumn<Apartamento, Double> aluguelCol = new TableColumn<>("ALUGUEL");
        aluguelCol.setCellValueFactory(data -> data.getValue().aluguelProperty().asObject());

        TableColumn<Apartamento, Double> garagemCol = new TableColumn<>("GARAGEM");
        garagemCol.setCellValueFactory(data -> data.getValue().garagemProperty().asObject());

        TableColumn<Apartamento, Double> condominioCol = new TableColumn<>("VALOR CONDOMÍNIO");
        condominioCol.setCellValueFactory(data -> data.getValue().valorCondominioProperty().asObject());

        TableColumn<Apartamento, Double> consumoAguaCol = new TableColumn<>("CONSUMO ÁGUA");
        consumoAguaCol.setCellValueFactory(data -> data.getValue().consumoAguaProperty().asObject());

        TableColumn<Apartamento, Double> valorAguaCol = new TableColumn<>("VALOR ÁGUA");
        valorAguaCol.setCellValueFactory(data -> data.getValue().valorAguaProperty().asObject());

        TableColumn<Apartamento, Double> consumoGasCol = new TableColumn<>("CONSUMO GÁS");
        consumoGasCol.setCellValueFactory(data -> data.getValue().consumoGasProperty().asObject());

        TableColumn<Apartamento, Double> valorGasCol = new TableColumn<>("VALOR GÁS");
        valorGasCol.setCellValueFactory(data -> data.getValue().valorGasProperty().asObject());

        TableColumn<Apartamento, Double> valorLuzCol = new TableColumn<>("VALOR LUZ");
        valorLuzCol.setCellValueFactory(data -> data.getValue().valorLuzProperty().asObject());

        TableColumn<Apartamento, Double> totalCol = new TableColumn<>("TOTAL");
        totalCol.setCellValueFactory(data -> data.getValue().totalProperty().asObject());

        // Adiciona todas as colunas à tabela
        tableView.getColumns().addAll(
                numeroCol, blocoCol, areaCol, proprietarioCol, descricaoCol,
                mesCompetenciaCol, aluguelCol, garagemCol, condominioCol,
                consumoAguaCol, valorAguaCol, consumoGasCol, valorGasCol,
                valorLuzCol, totalCol
        );

        // Configurações visuais da tabela
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setStyle("-fx-border-color: black;");
    }

    private void loadTableData() {
        // Carrega os dados da tabela usando o DAO
        ApartamentoDAO dao = new ApartamentoDAO();
        ObservableList<Apartamento> data = FXCollections.observableArrayList(dao.listarApartamentos());
        tableView.setItems(data);
    }

    private HBox createBottomIcons(Stage stage) {
        // Criação dos ícones na parte inferior
        ImageView iconConfig = createIcon("/images/icon1.png", "Configuração clicada", () -> {
            TelaConfiguracoes telaConfiguracoes = new TelaConfiguracoes();
            stage.setScene(telaConfiguracoes.createConfiguracoesScene(stage));
        });

        ImageView iconImoveis = createIcon("/images/icon2.png", "Imóveis clicado", () -> {
            TelaCadastroImoveis telaCadastroImoveis = new TelaCadastroImoveis();
            stage.setScene(telaCadastroImoveis.createCadastroImoveisScene(stage));
        });

        ImageView iconInquilinos = createIcon("/images/icon3.png", "Inquilinos clicado", () -> {
            TelaCadastroInquilinos telaCadastroInquilinos = new TelaCadastroInquilinos();
            stage.setScene(telaCadastroInquilinos.createCadastroInquilinosScene(stage));
        });

        ImageView iconTabela = createIcon("/images/icon4.png", "Tabela clicada", () -> {
            TelaTabela telaTabela = new TelaTabela();
            stage.setScene(telaTabela.createTabelaScene(stage));
        });

        HBox bottomIcons = new HBox(30, iconConfig, iconImoveis, iconInquilinos, iconTabela);
        bottomIcons.setAlignment(Pos.CENTER);
        bottomIcons.setPadding(new Insets(15, 0, 50, 0));
        bottomIcons.setBackground(new Background(new BackgroundFill(Color.web("#808080"), CornerRadii.EMPTY, Insets.EMPTY)));
        return bottomIcons;
    }

    private ImageView createIcon(String imagePath, String message, Runnable onClickAction) {
        // Método para criar um ícone com evento de clique
        ImageView icon = new ImageView();
        try {
            icon.setImage(new Image(getClass().getResource(imagePath).toExternalForm()));
        } catch (NullPointerException e) {
            System.err.println("Erro ao carregar o ícone: " + imagePath);
        }
        icon.setFitWidth(30);
        icon.setFitHeight(30);
        icon.setOnMouseClicked(e -> {
            System.out.println(message);
            if (onClickAction != null) {
                onClickAction.run();
            }
        });
        icon.setStyle("-fx-cursor: hand;");
        return icon;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tabelas UI");
        primaryStage.setScene(createTabelaScene(primaryStage));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
