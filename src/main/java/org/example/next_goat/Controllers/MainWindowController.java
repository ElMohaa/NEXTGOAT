package org.example.next_goat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.next_goat.Clases.MejoraFisica;
import org.example.next_goat.Clases.UserSession;
import org.example.next_goat.DataBase.DataBaseConnection;

import java.io.IOException;

public class MainWindowController {
    // Obtener el id_usuario del usuario logueado
    int idUsuario = UserSession.getInstance().getIdUsuario();

    private UserController userController;  // Referencia al UserController para actualizar el Label

    // Método para recibir la instancia del UserController
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    @FXML
    private void handleButtonAction(ActionEvent event, String fxmlPath) {
        try {
            // Cargar la vista desde el archivo FXML especificado
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent newView = loader.load();

            // Si estamos cargando Training.fxml, también pasamos el controlador de UserController
            if (fxmlPath.equals("/FXML/Training.fxml")) {
                // Obtiene el controlador de UserController
                UserController userController = (UserController) ((Node) event.getSource()).getScene().getWindow()
                        .getUserData();  // Recuperamos el UserController que se pasó previamente

                // Recupera el controlador de TrainingController
                TrainingController trainingController = loader.getController();

                // Le pasamos el controlador de UserController a TrainingController
                trainingController.setUserController(userController);
            }

            // Crear una nueva escena con la vista cargada
            Scene newScene = new Scene(newView);

            // Obtener el stage actual y cambiar la escena
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLaLigaButtonAction(ActionEvent event) {
        handleButtonAction(event, "/FXML/LaLigaView.fxml");
    }

    @FXML
    private void handleEntreButtonAction(ActionEvent event) {
        handleButtonAction(event, "/FXML/Training.fxml");
    }

    @FXML
    private void buttonAtletismo(ActionEvent event) {
        // Obtener la mejora física del usuario desde la base de datos
        MejoraFisica mejoraFisica = DataBaseConnection.getMejoraFisicaByUserId(idUsuario);

        if (mejoraFisica != null) {
            // Incrementar el valor de Pase en el objeto MejoraFisica
            int nuevoPase = mejoraFisica.getPase() ;
            int nuevoRitmo = mejoraFisica.getRitmo() + 8;
            int nuevoRegate = mejoraFisica.getRegate();
            int nuevoTiro = mejoraFisica.getTiro();
            int nuevoFisico = mejoraFisica.getFisico() + 10;
            int nuevoDefensa = mejoraFisica.getDefensa();
            int media=mejoraFisica.calcularMedia(nuevoDefensa,nuevoPase,nuevoFisico,nuevoRegate,nuevoRitmo,nuevoTiro);

            mejoraFisica.setPase(nuevoPase);
            mejoraFisica.setFisico(nuevoFisico);
            mejoraFisica.setRegate(nuevoRegate);
            mejoraFisica.setRitmo(nuevoRitmo);
            mejoraFisica.setTiro(nuevoTiro);
            mejoraFisica.setDefensa(nuevoDefensa);
            mejoraFisica.setFisico(media);

            // Actualizar el valor de Pase en la base de datos
            boolean actualizado = DataBaseConnection.updateMejoraFisica(mejoraFisica);

            if (actualizado) {
                System.out.println("Valor de pase actualizado exitosamente.");

                // Si el usuario está registrado en UserController, actualizar el Label pase
                if (userController != null) {
                    userController.actualizarLabelPase(nuevoPase);
                }
            } else {
                System.out.println("No se pudo actualizar el valor de Pase.");
            }
        }
        handleButtonAction(event, "/FXML/Physical.fxml");
    }

    @FXML
    private void buttonRecovery(ActionEvent event) {
        // Obtener la mejora física del usuario desde la base de datos
        MejoraFisica mejoraFisica = DataBaseConnection.getMejoraFisicaByUserId(idUsuario);

        if (mejoraFisica != null) {
            // Incrementar el valor de Pase en el objeto MejoraFisica
            int nuevoPase = mejoraFisica.getPase() -7;
            int nuevoRitmo = mejoraFisica.getRitmo() -7;
            int nuevoRegate = mejoraFisica.getRegate() -7;
            int nuevoTiro = mejoraFisica.getTiro() -7;
            int nuevoFisico = mejoraFisica.getFisico() -7;
            int nuevoDefensa = mejoraFisica.getDefensa() -7;
            int media=mejoraFisica.calcularMedia(nuevoDefensa,nuevoPase,nuevoFisico,nuevoRegate,nuevoRitmo,nuevoTiro);

            mejoraFisica.setPase(nuevoPase);
            mejoraFisica.setFisico(nuevoFisico);
            mejoraFisica.setRegate(nuevoRegate);
            mejoraFisica.setRitmo(nuevoRitmo);
            mejoraFisica.setTiro(nuevoTiro);
            mejoraFisica.setDefensa(nuevoDefensa);
            mejoraFisica.setFisico(media);

            // Actualizar el valor de Pase en la base de datos
            boolean actualizado = DataBaseConnection.updateMejoraFisica(mejoraFisica);

            if (actualizado) {
                System.out.println("Valor de pase actualizado exitosamente.");

                // Si el usuario está registrado en UserController, actualizar el Label pase
                if (userController != null) {
                    userController.actualizarLabelPase(nuevoPase);
                }
            } else {
                System.out.println("No se pudo actualizar el valor de Pase.");
            }
        }
        handleButtonAction(event, "/FXML/Recovery.fxml");
    }

    @FXML
    private void buttonUser(ActionEvent event) {
        handleButtonAction(event, "/FXML/User.fxml");
    }
}
