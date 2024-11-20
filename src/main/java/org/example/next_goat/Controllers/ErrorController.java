package org.example.next_goat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorController {

    private String errorText;

    @FXML
    private Label textError;
    @FXML
    private Button okgoodbutton;
    @FXML
    private Button okbutton;

    public ErrorController() {
    }

    public ErrorController(String errorText) {
        textError.setText(errorText);
    }
    public String getErrorText() {
        return errorText;
    }
    public void setErrorText(String errorText) {
        this.errorText = errorText;
        textError.setText(errorText);
    }

    @FXML
    private void cerrVentana(ActionEvent event) {
        Stage stage = (Stage) okbutton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cerrVentanaGood(ActionEvent event) {
        Stage stage = (Stage) okgoodbutton.getScene().getWindow();
        stage.close();
    }

}
