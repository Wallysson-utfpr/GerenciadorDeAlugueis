package ui;

import javafx.scene.layout.Priority; // Gerencia o crescimento de layout
import javafx.animation.KeyFrame; // Define uma ação em um ponto no tempo
import javafx.animation.Timeline; // Animações baseadas no tempo
import javafx.geometry.Pos; // Define alinhamento
import javafx.scene.Scene; // Representa a cena exibida no Stage
import javafx.scene.control.Label; // Rótulo de texto
import javafx.scene.layout.StackPane; // Layout empilhado
import javafx.scene.layout.VBox; // Layout vertical
import javafx.scene.paint.Color; // Permite uso de cores
import javafx.scene.text.Font; // Gerencia fontes de texto
import javafx.scene.text.FontWeight; // Define peso da fonte
import javafx.stage.Stage; // Representa a janela principal
import javafx.util.Duration; // Define duração para animações

// Tela inicial com animação de transição para a próxima tela
public class TelaInicial {

    // Criação da cena inicial
    public Scene createInicialScene(Stage stage) {
        // Label "BEM VINDO AO"
        Label lblBemVindo = new Label("BEM VINDO AO");
        lblBemVindo.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Define fonte e peso
        lblBemVindo.setTextFill(Color.web("#000000")); // Cor do texto

        // Label "PROPTECH"
        Label lblProptech = new Label("PROPTECH");
        lblProptech.setFont(Font.font("Arial", FontWeight.BOLD, 160)); // Fonte grande para destaque
        lblProptech.setTextFill(Color.web("#05343D")); // Cor do texto

        // Layout vertical contendo os dois labels
        VBox vbox = new VBox(5, lblBemVindo, lblProptech);
        vbox.setAlignment(Pos.CENTER); // Alinha ao centro
        VBox.setVgrow(vbox, Priority.ALWAYS); // Faz o VBox ocupar todo o espaço disponível

        // Layout empilhado para centralizar o conteúdo
        StackPane root = new StackPane(vbox);
        root.setStyle("-fx-background-color: #C0C0C0; -fx-padding: 30; -fx-border-radius: 10px; -fx-background-radius: 10px;");

        // Configura o estágio para tela cheia
        stage.setMaximized(true); // Maximiza a janela para ocupar toda a tela

        // Cena inicial
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());

        // Timeline para mudar automaticamente para a próxima tela após 1.5 segundos
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), e -> {
            TelaRelatorios telaRelatorios = new TelaRelatorios(); // Cria instância da próxima tela
            stage.setScene(telaRelatorios.createRelatoriosScene(stage)); // Transição para a próxima cena
        }));
        timeline.play(); // Inicia a animação de transição

        return scene;
    }
}
