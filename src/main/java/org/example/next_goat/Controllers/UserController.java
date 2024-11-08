package org.example.next_goat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.next_goat.Clases.MejoraFisica;
import org.example.next_goat.Clases.UserSession;
import org.example.next_goat.DataBase.DataBaseConnection;

import java.io.IOException;

public class UserController {
    @FXML
    private Label resistenciaLabel;
    @FXML
    private Label tiroLabel;
    @FXML
    private Label paseLabel;
    @FXML
    private Label regateLabel;
    @FXML
    private Label defeLabel;
    @FXML
    private Label fisicoLabel;
    @FXML
    private Label mediaLabel;
    @FXML
    private Label nombreLabel;

    @FXML
    private void initialize() {
        // Obtener el id_usuario del usuario logueado desde la sesión
        int idUsuario = UserSession.getInstance().getIdUsuario();

        if (idUsuario != -1) {  // Verifica si el usuario está logueado
            loadMejoraFisicaData(idUsuario);
        } else {
            System.out.println("Usuario no logueado.");
        }
    }

    private void loadMejoraFisicaData(int userId) {
        // Obtener la mejora física del usuario desde la base de datos
        MejoraFisica mejoraFisica = DataBaseConnection.getMejoraFisicaByUserId(userId);
        String nombre=DataBaseConnection.getFullNameById(userId);

        // Si se obtiene la mejora física, actualizar los labels
        if (mejoraFisica != null) {
            resistenciaLabel.setText(String.valueOf(mejoraFisica.getRitmo()));
            tiroLabel.setText(String.valueOf(mejoraFisica.getTiro()));
            paseLabel.setText(String.valueOf(mejoraFisica.getPase()));
            regateLabel.setText(String.valueOf(mejoraFisica.getRegate()));
            defeLabel.setText(String.valueOf(mejoraFisica.getDefensa()));
            fisicoLabel.setText(String.valueOf(mejoraFisica.getFisico()));
            mediaLabel.setText(String.valueOf(mejoraFisica.getMedia()));

            nombreLabel.setText(nombre);
        } else {
            System.out.println("No se encontró la mejora física para el usuario.");
        }
    }

    public void actualizarLabelPase(int nuevoPase) {
        paseLabel.setText("Pase: " + nuevoPase);
    }
    @FXML
    private void buttonHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MainWindow.fxml"));
            Parent traView = loader.load();
            Scene laLigaScene = new Scene(traView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(laLigaScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void closeSession(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/UserLogin.fxml"));
            Parent traView = loader.load();
            Scene laLigaScene = new Scene(traView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(laLigaScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editUser(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/EditUser.fxml"));
            Parent traView = loader.load();
            Scene laLigaScene = new Scene(traView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(laLigaScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
