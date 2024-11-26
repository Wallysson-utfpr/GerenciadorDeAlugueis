package ui;

import dao.InquilinoDAO; // Importa o DAO responsável por manipular os dados do inquilino no banco de dados
import javafx.application.Application; // Classe base para criar aplicações JavaFX
import javafx.geometry.Insets; // Gerencia espaçamento interno
import javafx.geometry.Pos; // Gerencia alinhamento dos elementos
import javafx.scene.Scene; // Representa a cena exibida no Stage (janela)
import javafx.scene.control.*; // Importa componentes de UI como Label, TextField, Button
import javafx.scene.layout.*; // Gerencia layouts da interface gráfica
import javafx.scene.paint.Color; // Permite o uso de cores
import javafx.scene.text.Font; // Gerencia fontes de texto
import javafx.stage.Stage; // Representa a janela principal da aplicação
import modelo.Inquilino; // Importa a classe que representa o modelo de dados "Inquilino"

// Classe que cria a interface gráfica para cadastro de inquilinos
public class TelaCadastroInquilinos extends Application {

    // Método para criar a cena de cadastro de inquilinos
    public Scene createCadastroInquilinosScene(Stage stage) {
        // Barra superior com título "PROTECH" e botão "X" para voltar
        Label protechLabel = new Label("PROTECH");
        protechLabel.setFont(new Font("Arial", 24));
        protechLabel.setTextFill(Color.BLACK);

        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
        closeButton.setOnAction(e -> {
            // Altera a cena para a tela de relatórios
            TelaRelatorios telaRelatorios = new TelaRelatorios();
            stage.setScene(telaRelatorios.createRelatoriosScene(stage));
        });

        // Layout da barra superior
        HBox topBar = new HBox(10, protechLabel, new Region(), closeButton);
        HBox.setHgrow(topBar.getChildren().get(1), Priority.ALWAYS);
        topBar.setPadding(new Insets(5, 20, 5, 10));
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setBackground(new Background(new BackgroundFill(Color.web("#A0A0A0"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Título central "INQUILINOS"
        Label titleLabel = new Label("INQUILINOS");
        titleLabel.setFont(new Font("Arial", 30));
        titleLabel.setTextFill(Color.BLACK);
        HBox titleContainer = new HBox(titleLabel);
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.setPadding(new Insets(10));
        titleContainer.setBackground(new Background(new BackgroundFill(Color.web("#A0A0A0"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Container que une barra superior e título
        VBox topContainer = new VBox(topBar, titleContainer);
        topContainer.setBackground(new Background(new BackgroundFill(Color.web("#707070"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Formulário para entrada de dados
        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(15));
        formGrid.setAlignment(Pos.CENTER);

        // Campos do formulário
        addFieldToGrid(formGrid, "NOME:", new TextField(), 0, 0);
        addFieldToGrid(formGrid, "IMÓVEL ALUGADO:", new TextField(), 0, 2);
        addFieldToGrid(formGrid, "CPF:", new TextField(), 0, 4);
        addFieldToGrid(formGrid, "TELEFONE:", new TextField(), 1, 4);
        addFieldToGrid(formGrid, "DATA INÍCIO CONTRATO:", new TextField(), 0, 6);
        addFieldToGrid(formGrid, "DATA FIM CONTRATO:", new TextField(), 1, 6);

        // Botão de salvar
        Button saveButton = new Button("SALVAR");
        saveButton.setStyle("-fx-background-color: #32CD32; -fx-text-fill: black; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 12px 24px;");
        saveButton.setOnAction(e -> saveInquilino(formGrid));

        VBox saveButtonContainer = new VBox(saveButton);
        saveButtonContainer.setAlignment(Pos.CENTER);
        saveButtonContainer.setPadding(new Insets(20, 0, 0, 0));

        // Container que combina o formulário e o botão
        VBox formContainer = new VBox(formGrid, saveButtonContainer);
        formContainer.setAlignment(Pos.CENTER);
        formContainer.setPadding(new Insets(20));
        formContainer.setBackground(new Background(new BackgroundFill(Color.web("#C0C0C0"), new CornerRadii(10), Insets.EMPTY)));

        // Layout principal
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topContainer);
        mainLayout.setCenter(formContainer);

        // Criação e retorno da cena
        Scene scene = new Scene(mainLayout, stage.getWidth(), stage.getHeight());
        stage.setMaximized(true);

        return scene;
    }

    // Método para adicionar campos ao formulário
    private void addFieldToGrid(GridPane grid, String labelText, TextField field, int col, int row) {
        Label label = new Label(labelText);
        label.setFont(new Font("Arial", 16));
        field.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-padding: 5px; -fx-font-size: 14px;");
        grid.add(label, col, row);
        grid.add(field, col, row + 1);
    }

    // Método para salvar os dados do inquilino
    private void saveInquilino(GridPane formGrid) {
        try {
            // Recupera os campos do formulário
            TextField nameField = (TextField) formGrid.getChildren().get(1);
            TextField imovelField = (TextField) formGrid.getChildren().get(3);
            TextField cpfField = (TextField) formGrid.getChildren().get(5);
            TextField telefoneField = (TextField) formGrid.getChildren().get(7);
            TextField dataInicioField = (TextField) formGrid.getChildren().get(9);
            TextField dataFimField = (TextField) formGrid.getChildren().get(11);

            // Cria um objeto Inquilino
            Inquilino inquilino = new Inquilino(
                    nameField.getText(),
                    imovelField.getText(),
                    cpfField.getText(),
                    telefoneField.getText(),
                    dataInicioField.getText(),
                    dataFimField.getText()
            );

            // Salva o inquilino no banco de dados usando o DAO
            InquilinoDAO inquilinoDAO = new InquilinoDAO();
            inquilinoDAO.inserirInquilino(inquilino);

            // Exibe mensagem de sucesso e limpa os campos
            System.out.println("Inquilino cadastrado com sucesso!");
            nameField.clear();
            imovelField.clear();
            cpfField.clear();
            telefoneField.clear();
            dataInicioField.clear();
            dataFimField.clear();
        } catch (Exception e) {
            // Trata erros durante o cadastro
            System.err.println("Erro ao cadastrar inquilino: " + e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cadastro de Inquilinos");
        primaryStage.setScene(createCadastroInquilinosScene(primaryStage));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
