package ui;

import dao.ImovelDAO; // Importa o DAO responsável por manipular os dados do imóvel no banco de dados
import javafx.application.Application; // Classe base para criar aplicações JavaFX
import javafx.geometry.Insets; // Gerencia espaçamento interno
import javafx.geometry.Pos; // Gerencia alinhamento dos elementos
import javafx.scene.Scene; // Representa a cena exibida no Stage (janela)
import javafx.scene.control.*; // Importa os componentes de UI como Label, TextField, Button
import javafx.scene.layout.*; // Gerencia os layouts da interface gráfica
import javafx.scene.paint.Color; // Permite o uso de cores
import javafx.scene.text.Font; // Gerencia fontes de texto
import javafx.stage.Stage; // Representa a janela principal
import modelo.Imovel; // Importa a classe que representa o modelo de dados "Imovel"

// Classe que cria a interface gráfica para cadastro de imóveis
public class TelaCadastroImoveis extends Application {

    // Método para criar a tela de cadastro de imóveis
    public Scene createCadastroImoveisScene(Stage stage) {
        // Barra superior com título "PROTECH" e botão "X" para fechar
        Label protechLabel = new Label("PROTECH");
        protechLabel.setFont(new Font("Arial", 24));
        protechLabel.setTextFill(Color.BLACK);

        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
        closeButton.setOnAction(e -> {
            TelaRelatorios telaRelatorios = new TelaRelatorios();
            stage.setScene(telaRelatorios.createRelatoriosScene(stage)); // Altera a cena para relatórios
        });

        HBox topBar = new HBox(10, protechLabel, new Region(), closeButton);
        HBox.setHgrow(topBar.getChildren().get(1), Priority.ALWAYS); // Expande o espaço entre o título e o botão
        topBar.setPadding(new Insets(5, 20, 5, 10));
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setBackground(new Background(new BackgroundFill(Color.web("#A0A0A0"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Título central "IMÓVEIS"
        Label titleLabel = new Label("IMÓVEIS");
        titleLabel.setFont(new Font("Arial", 30));
        titleLabel.setTextFill(Color.BLACK);
        HBox titleContainer = new HBox(titleLabel);
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.setPadding(new Insets(10));
        titleContainer.setBackground(new Background(new BackgroundFill(Color.web("#A0A0A0"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Container que une barra superior e título
        VBox topContainer = new VBox(topBar, titleContainer);
        topContainer.setBackground(new Background(new BackgroundFill(Color.web("#707070"), CornerRadii.EMPTY, Insets.EMPTY)));

        // GridPane para os campos do formulário
        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(15));
        formGrid.setAlignment(Pos.CENTER);

        // Adiciona os campos ao formulário
        Label imovelLabel = new Label("IMÓVEL:");
        TextField imovelField = new TextField();
        addFieldToGrid(formGrid, imovelLabel, imovelField, 0, 0);

        Label tipoImovelLabel = new Label("TIPO DE IMÓVEL:");
        TextField tipoImovelField = new TextField();
        addFieldToGrid(formGrid, tipoImovelLabel, tipoImovelField, 0, 2);

        Label cepLabel = new Label("CEP:");
        TextField cepField = new TextField();
        addFieldToGrid(formGrid, cepLabel, cepField, 0, 4);

        Label enderecoLabel = new Label("ENDEREÇO:");
        TextField enderecoField = new TextField();
        enderecoField.setPrefWidth(350);
        addFieldToGrid(formGrid, enderecoLabel, enderecoField, 0, 6);

        Label numeroLabel = new Label("NÚMERO:");
        TextField numeroField = new TextField();
        numeroField.setPrefWidth(200);
        addFieldToGrid(formGrid, numeroLabel, numeroField, 1, 6);

        Label complementoLabel = new Label("COMPLEMENTO:");
        TextField complementoField = new TextField();
        addFieldToGrid(formGrid, complementoLabel, complementoField, 0, 8);

        // Aplica estilo aos campos
        applyStyleToFields(imovelField, tipoImovelField, cepField, enderecoField, numeroField, complementoField);

        // Botão de salvar
        Button saveButton = new Button("SALVAR");
        saveButton.setStyle("-fx-background-color: #32CD32; -fx-text-fill: black; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 12px 24px;");
        saveButton.setOnAction(e -> saveImovel(imovelField, tipoImovelField, cepField, enderecoField, numeroField, complementoField));

        VBox saveButtonContainer = new VBox(saveButton);
        saveButtonContainer.setAlignment(Pos.CENTER);
        saveButtonContainer.setPadding(new Insets(20, 0, 0, 0));

        // Container que combina formulário e botão
        VBox formContainer = new VBox(formGrid, saveButtonContainer);
        formContainer.setAlignment(Pos.CENTER);
        formContainer.setPadding(new Insets(20));
        formContainer.setBackground(new Background(new BackgroundFill(Color.web("#C0C0C0"), new CornerRadii(10), Insets.EMPTY)));

        // Layout principal
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topContainer);
        mainLayout.setCenter(formContainer);

        // Cria e retorna a cena
        Scene scene = new Scene(mainLayout, stage.getWidth(), stage.getHeight());
        stage.setMaximized(true);

        return scene;
    }

    // Método para adicionar um campo ao GridPane
    private void addFieldToGrid(GridPane grid, Label label, TextField field, int col, int row) {
        label.setFont(new Font("Arial", 16));
        grid.add(label, col, row);
        grid.add(field, col, row + 1);
    }

    // Método para aplicar estilo aos campos de texto
    private void applyStyleToFields(TextField... fields) {
        String style = "-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-padding: 5px; -fx-font-size: 14px;";
        for (TextField field : fields) {
            field.setStyle(style);
        }
    }

    // Método para salvar os dados do imóvel
    private void saveImovel(TextField imovelField, TextField tipoField, TextField cepField, TextField enderecoField, TextField numeroField, TextField complementoField) {
        try {
            // Coleta os dados dos campos
            Imovel imovel = new Imovel(
                    imovelField.getText(),
                    tipoField.getText(),
                    cepField.getText(),
                    enderecoField.getText(),
                    numeroField.getText(),
                    complementoField.getText()
            );

            // Salva os dados usando o DAO
            ImovelDAO imovelDAO = new ImovelDAO();
            imovelDAO.inserirImovel(imovel);

            // Exibe mensagem de sucesso e limpa os campos
            System.out.println("Imóvel cadastrado com sucesso!");
            imovelField.clear();
            tipoField.clear();
            cepField.clear();
            enderecoField.clear();
            numeroField.clear();
            complementoField.clear();
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar imóvel: " + e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cadastro de Imóveis");
        primaryStage.setScene(createCadastroImoveisScene(primaryStage));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
