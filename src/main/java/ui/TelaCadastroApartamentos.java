package ui;

import dao.ApartamentoDAO; // Importa o DAO para salvar os dados no banco
import javafx.application.Application; // Classe base para aplicações JavaFX
import javafx.geometry.Insets; // Gerencia margens internas
import javafx.geometry.Pos; // Gerencia o alinhamento dos elementos
import javafx.scene.Scene; // Representa a cena que será exibida na janela
import javafx.scene.control.*; // Importa controles de interface gráfica
import javafx.scene.layout.*; // Importa gerenciadores de layout
import javafx.scene.paint.Color; // Permite o uso de cores
import javafx.scene.text.Font; // Gerencia fontes de texto
import javafx.stage.Stage; // Representa a janela principal da aplicação
import modelo.Apartamento; // Classe do modelo de dados para apartamentos

// Classe que representa a tela de cadastro de apartamentos
public class TelaCadastroApartamentos extends Application {

    // Campos do formulário para entrada de dados
    private TextField numeroField = new TextField(); // Campo para o número do apartamento
    private TextField blocoField = new TextField(); // Campo para o bloco
    private TextField areaField = new TextField(); // Campo para a área
    private TextField proprietarioField = new TextField(); // Campo para o nome do proprietário
    private TextArea descricaoField = new TextArea(); // Campo para a descrição do apartamento
    private TextField mesCompetenciaField = new TextField(); // Campo para o mês de competência
    private TextField aluguelField = new TextField(); // Campo para o valor do aluguel
    private TextField garagemField = new TextField(); // Campo para o valor da garagem
    private TextField valorCondominioField = new TextField(); // Campo para o valor do condomínio
    private TextField consumoAguaField = new TextField(); // Campo para o consumo de água
    private TextField valorAguaField = new TextField(); // Campo para o valor da água
    private TextField consumoGasField = new TextField(); // Campo para o consumo de gás
    private TextField valorGasField = new TextField(); // Campo para o valor do gás
    private TextField valorLuzField = new TextField(); // Campo para o valor da luz
    private TextField totalField = new TextField(); // Campo para o total

    // Método para criar a cena de cadastro
    public Scene createCadastroApartamentosScene(Stage stage) {
        // Barra superior com título e botão de fechar
        Label protechLabel = new Label("PROTECH");
        protechLabel.setFont(new Font("Arial", 24)); // Define a fonte do título
        protechLabel.setTextFill(Color.BLACK); // Define a cor do texto

        Button closeButton = new Button("X"); // Botão para fechar a tela
        closeButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
        closeButton.setOnAction(e -> {
            // Redireciona para a tela de relatórios
            TelaRelatorios telaRelatorios = new TelaRelatorios();
            stage.setScene(telaRelatorios.createRelatoriosScene(stage));
        });

        // Layout da barra superior
        HBox topBar = new HBox(10, protechLabel, new Region(), closeButton);
        HBox.setHgrow(topBar.getChildren().get(1), Priority.ALWAYS); // Adiciona espaço entre o título e o botão
        topBar.setPadding(new Insets(5, 20, 5, 10));
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setBackground(new Background(new BackgroundFill(Color.web("#A0A0A0"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Campos do formulário
        VBox formContainer = new VBox(15); // Container para os campos de entrada
        formContainer.setPadding(new Insets(20)); // Define o espaçamento interno

        // Adiciona os campos de entrada ao container
        formContainer.getChildren().addAll(
                createLabeledField("Número:", numeroField),
                createLabeledField("Bloco:", blocoField),
                createLabeledField("Área (m²):", areaField),
                createLabeledField("Proprietário:", proprietarioField),
                createLabeledField("Descrição:", descricaoField),
                createLabeledField("Mês Competência:", mesCompetenciaField),
                createLabeledField("Aluguel:", aluguelField),
                createLabeledField("Garagem:", garagemField),
                createLabeledField("Valor Condomínio:", valorCondominioField),
                createLabeledField("Consumo Água:", consumoAguaField),
                createLabeledField("Valor Água:", valorAguaField),
                createLabeledField("Consumo Gás:", consumoGasField),
                createLabeledField("Valor Gás:", valorGasField),
                createLabeledField("Valor Luz:", valorLuzField),
                createLabeledField("Total:", totalField)
        );

        // Botão de salvar
        Button saveButton = new Button("Salvar");
        saveButton.setStyle("-fx-background-color: #32CD32; -fx-text-fill: black; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 12px 20px;");
        saveButton.setOnAction(e -> saveApartamento()); // Aciona o método de salvar apartamento
        formContainer.getChildren().add(saveButton); // Adiciona o botão ao container
        formContainer.setAlignment(Pos.TOP_CENTER);

        // Adiciona rolagem para o formulário
        ScrollPane scrollPane = new ScrollPane(formContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));
        scrollPane.setStyle("-fx-background: #C0C0C0;");

        // Layout principal
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topBar); // Define a barra superior
        mainLayout.setCenter(scrollPane); // Define o formulário no centro

        // Cria a cena com o layout principal
        Scene scene = new Scene(mainLayout, stage.getWidth(), stage.getHeight());
        stage.widthProperty().addListener((obs, oldVal, newVal) -> scene.setRoot(mainLayout));
        stage.heightProperty().addListener((obs, oldVal, newVal) -> scene.setRoot(mainLayout));
        stage.setMaximized(true); // Janela inicia maximizada

        return scene;
    }

    // Método para criar campos com rótulos
    private VBox createLabeledField(String labelText, TextField field) {
        Label label = new Label(labelText);
        label.setFont(new Font("Arial", 16));
        field.setStyle("-fx-background-color: white; -fx-border-color: black;");
        VBox fieldContainer = new VBox(5, label, field);
        fieldContainer.setAlignment(Pos.CENTER_LEFT);
        return fieldContainer;
    }

    // Método para criar campos de texto longo com rótulos
    private VBox createLabeledField(String labelText, TextArea field) {
        Label label = new Label(labelText);
        label.setFont(new Font("Arial", 16));
        field.setStyle("-fx-background-color: white; -fx-border-color: black;");
        field.setPrefRowCount(3); // Define altura padrão
        VBox fieldContainer = new VBox(5, label, field);
        fieldContainer.setAlignment(Pos.CENTER_LEFT);
        return fieldContainer;
    }

    // Método para salvar os dados do apartamento
    private void saveApartamento() {
        try {
            // Cria um objeto Apartamento com os dados do formulário
            Apartamento apartamento = new Apartamento(
                    numeroField.getText(),
                    blocoField.getText(),
                    Double.parseDouble(areaField.getText()),
                    proprietarioField.getText(),
                    descricaoField.getText(),
                    mesCompetenciaField.getText(),
                    Double.parseDouble(aluguelField.getText()),
                    Double.parseDouble(garagemField.getText()),
                    Double.parseDouble(valorCondominioField.getText()),
                    Double.parseDouble(consumoAguaField.getText()),
                    Double.parseDouble(valorAguaField.getText()),
                    Double.parseDouble(consumoGasField.getText()),
                    Double.parseDouble(valorGasField.getText()),
                    Double.parseDouble(valorLuzField.getText()),
                    Double.parseDouble(totalField.getText())
            );

            // Salva os dados no banco de dados
            ApartamentoDAO dao = new ApartamentoDAO();
            dao.cadastrarApartamento(apartamento);
            System.out.println("Apartamento cadastrado com sucesso!");
        } catch (Exception e) {
            // Trata erros durante o cadastro
            System.err.println("Erro ao cadastrar apartamento: " + e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        // Configura a janela principal da aplicação
        primaryStage.setTitle("Cadastro de Apartamentos");
        primaryStage.setScene(createCadastroApartamentosScene(primaryStage)); // Define a cena inicial
        primaryStage.show(); // Exibe a janela
    }

    public static void main(String[] args) {
        // Inicia a aplicação JavaFX
        launch(args);
    }
}
