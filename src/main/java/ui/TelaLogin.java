package ui;

import dao.UsuarioDAO; // Importa o DAO responsável pela manipulação de dados dos usuários
import javafx.geometry.Pos; // Define alinhamento
import javafx.scene.Scene; // Representa a cena exibida no Stage
import javafx.scene.control.*; // Importa componentes da interface como Label, TextField, PasswordField, Button, etc.
import javafx.scene.image.Image; // Manipula imagens
import javafx.scene.image.ImageView; // Exibe imagens
import javafx.scene.layout.StackPane; // Layout para empilhar elementos
import javafx.scene.layout.VBox; // Layout vertical
import javafx.scene.paint.Color; // Permite uso de cores
import javafx.scene.text.Font; // Gerencia fontes de texto
import javafx.scene.text.FontWeight; // Define peso da fonte
import javafx.stage.Stage; // Representa a janela principal
import modelo.Usuario; // Importa o modelo de dados do usuário

// Classe que implementa a tela de login
public class TelaLogin {

    // Método para criar a cena de login
    public Scene createLoginScene(Stage stage) {
        // Configurar o estágio para abrir maximizado
        stage.setMaximized(true);

        // Adicionando logo da aplicação
        ImageView icon = new ImageView(new Image(getClass().getResource("/images/logoatt.png").toExternalForm())); // Caminho para o logo
        icon.setPreserveRatio(true); // Mantém a proporção da imagem
        icon.setFitWidth(450); // Define a largura
        icon.setFitHeight(300); // Define a altura

        // Configuração do rótulo e campo de entrada para "Usuário"
        Label lblUsuario = new Label("USUÁRIO:");
        lblUsuario.setFont(Font.font("Arial", FontWeight.BOLD, 18)); // Define fonte e tamanho
        lblUsuario.setTextFill(Color.BLACK); // Define cor do texto

        TextField txtUsuario = new TextField(); // Campo de entrada para usuário
        txtUsuario.setMaxWidth(300); // Define largura máxima
        txtUsuario.setStyle("-fx-background-radius: 15px; -fx-border-radius: 15px; -fx-padding: 10 15; -fx-font-size: 16px;");

        VBox vboxUsuario = new VBox(5, lblUsuario, txtUsuario); // Agrupa o rótulo e o campo
        vboxUsuario.setAlignment(Pos.CENTER);
        vboxUsuario.setTranslateY(-60); // Move o conjunto "Usuário" para cima

        // Configuração do rótulo e campo de entrada para "Senha"
        Label lblSenha = new Label("SENHA:");
        lblSenha.setFont(Font.font("Arial", FontWeight.BOLD, 18)); // Define fonte e tamanho
        lblSenha.setTextFill(Color.BLACK); // Define cor do texto

        PasswordField txtSenha = new PasswordField(); // Campo de entrada para senha
        txtSenha.setMaxWidth(300); // Define largura máxima
        txtSenha.setStyle("-fx-background-radius: 15px; -fx-border-radius: 15px; -fx-padding: 10 15; -fx-font-size: 16px;");

        VBox vboxSenha = new VBox(5, lblSenha, txtSenha); // Agrupa o rótulo e o campo
        vboxSenha.setAlignment(Pos.CENTER);
        vboxSenha.setTranslateY(-70); // Move o conjunto "Senha" para cima

        // Configuração do botão de "Conectar"
        Button btnConectar = new Button("Conectar");
        btnConectar.setStyle("-fx-background-color: #007ACC; -fx-text-fill: white; -fx-padding: 10 30; -fx-font-size: 18px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        btnConectar.setTranslateY(-60); // Move o botão para cima
        btnConectar.setOnAction(e -> conectar(txtUsuario.getText(), txtSenha.getText(), stage)); // Evento ao clicar no botão

        // Ações ao pressionar Enter nos campos de texto
        txtUsuario.setOnAction(e -> conectar(txtUsuario.getText(), txtSenha.getText(), stage));
        txtSenha.setOnAction(e -> conectar(txtUsuario.getText(), txtSenha.getText(), stage));

        // Agrupando todos os elementos em um layout vertical
        VBox vbox = new VBox(30, icon, vboxUsuario, vboxSenha, btnConectar);
        vbox.setAlignment(Pos.CENTER); // Centraliza o layout
        vbox.setStyle("-fx-background-color: #B4BAB6; -fx-padding: 40; -fx-border-radius: 15px; -fx-background-radius: 15px;");

        // Configuração do StackPane para alinhar o VBox ao centro
        StackPane root = new StackPane(vbox);
        root.setStyle("-fx-background-color: #444444;");

        // Retorna a cena criada
        return new Scene(root, 800, 600);
    }

    // Método para autenticar o usuário
    private void conectar(String usuario, String senha, Stage stage) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(); // Cria instância do DAO para buscar usuário
        Usuario usuarioEncontrado = usuarioDAO.buscarUsuarioPorLogin(usuario); // Busca o usuário pelo login

        if (usuarioEncontrado != null && usuarioEncontrado.getSenha().equals(senha)) {
            // Autenticação bem-sucedida, muda para a tela inicial
            TelaInicial telaInicial = new TelaInicial();
            stage.setScene(telaInicial.createInicialScene(stage));
            System.out.println("Login bem-sucedido!");
        } else {
            // Exibe mensagem de erro caso o login falhe
            mostrarMensagemErro("Usuário ou senha inválidos");
        }
    }

    // Método para exibir mensagens de erro
    private void mostrarMensagemErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR); // Cria um alerta de erro
        alert.setTitle("Erro de Login");
        alert.setHeaderText(null); // Remove cabeçalho
        alert.setContentText(mensagem); // Define a mensagem de erro
        alert.showAndWait(); // Exibe o alerta
    }
}
