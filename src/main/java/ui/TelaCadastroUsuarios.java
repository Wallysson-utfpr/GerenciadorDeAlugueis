package ui;

import dao.UsuarioDAO;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modelo.Usuario;

import java.util.List;

public class TelaCadastroUsuarios extends Application {

    private boolean isAlterarSenha = false;

    public Scene createUsuarioScene(Stage stage, boolean isAlterarSenha) {
        this.isAlterarSenha = isAlterarSenha;

        // Top bar
        Label protechLabel = new Label("PROTECH");
        protechLabel.setFont(new Font("Arial", 24));
        protechLabel.setTextFill(Color.BLACK);

        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
        closeButton.setOnAction(e -> {
            TelaConfiguracoes telaConfiguracoes = new TelaConfiguracoes();
            stage.setScene(telaConfiguracoes.createConfiguracoesScene(stage));
        });

        HBox topBar = new HBox(10, protechLabel, new Region(), closeButton);
        HBox.setHgrow(topBar.getChildren().get(1), Priority.ALWAYS);
        topBar.setPadding(new Insets(5, 20, 5, 10));
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setBackground(new Background(new BackgroundFill(Color.web("#A0A0A0"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Title
        String titleText = isAlterarSenha ? "ALTERAR SENHA" : "CADASTRAR USUÁRIO";
        Label titleLabel = new Label(titleText);
        titleLabel.setFont(new Font("Arial", 30));
        titleLabel.setTextFill(Color.BLACK);
        HBox titleContainer = new HBox(titleLabel);
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.setPadding(new Insets(10));
        titleContainer.setBackground(new Background(new BackgroundFill(Color.web("#A0A0A0"), CornerRadii.EMPTY, Insets.EMPTY)));

        VBox topContainer = new VBox(topBar, titleContainer);
        topContainer.setBackground(new Background(new BackgroundFill(Color.web("#707070"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Form
        VBox formContainer = isAlterarSenha ? createAlterarSenhaPane(stage) : createCadastroUsuarioPane(stage);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topContainer);
        mainLayout.setCenter(formContainer);

        // Criação da cena e configuração para adaptar ao tamanho da janela
        Scene scene = new Scene(mainLayout, stage.getWidth(), stage.getHeight());
        stage.widthProperty().addListener((obs, oldVal, newVal) -> scene.setRoot(mainLayout));
        stage.heightProperty().addListener((obs, oldVal, newVal) -> scene.setRoot(mainLayout));
        stage.setMaximized(true); // Maximiza a janela

        return scene;    }

    private VBox createCadastroUsuarioPane(Stage stage) {
        Label nomeLabel = createLabel("Nome:");
        TextField nomeField = createTextField();

        Label loginLabel = createLabel("Login:");
        TextField loginField = createTextField();

        Label senhaLabel = createLabel("Senha:");
        PasswordField senhaField = createPasswordField();

        Label confirmarSenhaLabel = createLabel("Confirmar Senha:");
        PasswordField confirmarSenhaField = createPasswordField();

        Button saveButton = new Button("Cadastrar");
        saveButton.setStyle("-fx-background-color: #32CD32; -fx-text-fill: black; -fx-font-size: 18px;"); // Aumenta a fonte
        saveButton.setPadding(new Insets(10)); // Adiciona espaçamento interno
        VBox.setMargin(saveButton, new Insets(20, 0, 0, 0)); // Move o botão para baixo
        saveButton.setOnAction(e -> {
            String nome = nomeField.getText();
            String login = loginField.getText();
            String senha = senhaField.getText();
            String confirmarSenha = confirmarSenhaField.getText();

            if (nome.isEmpty() || login.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Erro", "Todos os campos devem ser preenchidos.");
                return;
            }

            if (!senha.equals(confirmarSenha)) {
                showAlert(Alert.AlertType.ERROR, "Erro", "As senhas não coincidem.");
                return;
            }

            Usuario usuario = new Usuario(nome, login, senha);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.cadastrarUsuario(usuario);

            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Usuário cadastrado com sucesso!");
            nomeField.clear();
            loginField.clear();
            senhaField.clear();
            confirmarSenhaField.clear();
        });

        VBox formPane = new VBox(12,
                nomeLabel, nomeField,
                loginLabel, loginField,
                senhaLabel, senhaField,
                confirmarSenhaLabel, confirmarSenhaField,
                saveButton
        );
        formPane.setAlignment(Pos.CENTER);
        formPane.setPadding(new Insets(24));
        formPane.setStyle("-fx-background-color: #C0C0C0;");

        return formPane;
    }

    private VBox createAlterarSenhaPane(Stage stage) {
        Label usuarioLabel = createLabel("Usuário:");
        ComboBox<String> usuarioComboBox = new ComboBox<>();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        for (Usuario usuario : usuarios) {
            usuarioComboBox.getItems().add(usuario.getLogin());
        }

        Label novaSenhaLabel = createLabel("Nova Senha:");
        VBox.setMargin(novaSenhaLabel, new Insets(20, 0, 0, 0)); // Margem superior para ajustar a posição
        PasswordField novaSenhaField = createPasswordField();

        Label confirmarNovaSenhaLabel = createLabel("Confirmar Nova Senha:");
        PasswordField confirmarNovaSenhaField = createPasswordField();

        Button alterarButton = new Button("Alterar Senha");
        alterarButton.setStyle("-fx-background-color: #FF8C00; -fx-text-fill: black; -fx-font-size: 18px;");
        alterarButton.setPadding(new Insets(10));
        VBox.setMargin(alterarButton, new Insets(20, 0, 0, 0)); // Ajuste de posição do botão

        alterarButton.setOnAction(e -> {
            String loginSelecionado = usuarioComboBox.getValue();
            String novaSenha = novaSenhaField.getText();
            String confirmarNovaSenha = confirmarNovaSenhaField.getText();

            if (loginSelecionado == null || novaSenha.isEmpty() || confirmarNovaSenha.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Erro", "Todos os campos devem ser preenchidos.");
                return;
            }

            if (!novaSenha.equals(confirmarNovaSenha)) {
                showAlert(Alert.AlertType.ERROR, "Erro", "As senhas não coincidem.");
                return;
            }

            Usuario usuario = usuarioDAO.buscarUsuarioPorLogin(loginSelecionado);
            if (usuario == null) {
                showAlert(Alert.AlertType.ERROR, "Erro", "Usuário não encontrado.");
                return;
            }

            usuarioDAO.alterarSenha(usuario.getId(), novaSenha);
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Senha alterada com sucesso!");

            novaSenhaField.clear();
            confirmarNovaSenhaField.clear();
            usuarioComboBox.getSelectionModel().clearSelection();
        });

        VBox formPane = new VBox(8, usuarioLabel, usuarioComboBox, novaSenhaLabel, novaSenhaField, confirmarNovaSenhaLabel, confirmarNovaSenhaField, alterarButton);
        formPane.setAlignment(Pos.CENTER);
        formPane.setPadding(new Insets(10));
        formPane.setStyle("-fx-background-color: #C0C0C0;");

        return formPane;
    }




    private TextField createTextField() {
        TextField textField = new TextField();
        textField.setPrefWidth(300);
        textField.setMinWidth(250); // Define a largura mínima
        textField.setMaxWidth(250); // Define a largura máxima
        return textField;
    }

    private PasswordField createPasswordField() {
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(300);
        passwordField.setMinWidth(250); // Define a largura mínima
        passwordField.setMaxWidth(250); // Define a largura máxima
        return passwordField;
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(new Font("Arial", 20)); // Aumentando a fonte dos labels
        return label;
    }


    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Usuários");
        primaryStage.setScene(createUsuarioScene(primaryStage, false)); // Default to Cadastro
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}