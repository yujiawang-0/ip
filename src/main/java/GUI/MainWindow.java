package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import prime.core.Prime;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Prime prime;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/spike_the_human.jpg"));
    private Image oppyImage = new Image(this.getClass().getResourceAsStream("/images/Optimus.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setPrime(Prime p) {
        prime = p;

        // Display greeting immediately
        String greeting = prime.getGreeting();

        dialogContainer.getChildren().add(DialogBox.getPrimeDialog(greeting, oppyImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Prime's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = prime.getResponse(input);

        if (response.equals("__EXIT__")) {
            dialogContainer.getChildren().add(
                    DialogBox.getPrimeDialog("Goodbye, little one", oppyImage)
            );
            userInput.clear();

            // close the application after a short delay

            // Create 2 second delay
            PauseTransition pause = new PauseTransition(Duration.seconds(2));

            pause.setOnFinished(event -> javafx.application.Platform.exit());

            pause.play();

            return;
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPrimeDialog(response, oppyImage));
        userInput.clear();
    }
}
