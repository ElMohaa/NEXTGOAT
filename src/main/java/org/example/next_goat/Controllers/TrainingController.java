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

public class TrainingController {
    // Obtener el id_usuario del usuario logueado
    int idUsuario = UserSession.getInstance().getIdUsuario();

    private UserController userController;  // Referencia al UserController para actualizar el Label

    // Método para recibir la instancia del UserController
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    // Método genérico para cargar y cambiar la escena
    private void changeScene(ActionEvent event, String fxmlPath) {
        try {
            // Cargar la vista desde el archivo FXML especificado
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent newView = loader.load();

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
    private void handleBackButtonAction(ActionEvent event) {
        changeScene(event, "/FXML/MainWindow.fxml");
    }

    @FXML
    private void handleDelanteroButtonAction(ActionEvent event) {


        // Obtener la mejora física del usuario desde la base de datos
        MejoraFisica mejoraFisica = DataBaseConnection.getMejoraFisicaByUserId(idUsuario);

        if (mejoraFisica != null) {
            // Incrementar el valor de Pase en el objeto MejoraFisica
            int nuevoPase = mejoraFisica.getPase() + 7;
            int nuevoRitmo = mejoraFisica.getRitmo() + 4;
            int nuevoRegate = mejoraFisica.getRegate() + 10;
            int nuevoTiro = mejoraFisica.getTiro() + 10;
            int nuevoFisico = mejoraFisica.getFisico() + 4;
            int nuevoDefensa = mejoraFisica.getDefensa() + 2;
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
        changeScene(event, "/FXML/ForwardAndMid/Forward.fxml");
    }

    @FXML
    private void handleCentroButtonAction(ActionEvent event) {
        // Obtener la mejora física del usuario desde la base de datos
        MejoraFisica mejoraFisica = DataBaseConnection.getMejoraFisicaByUserId(idUsuario);

        if (mejoraFisica != null) {
            // Incrementar el valor de Pase en el objeto MejoraFisica
            int nuevoPase = mejoraFisica.getPase() + 8;
            int nuevoRitmo = mejoraFisica.getRitmo() + 4;
            int nuevoRegate = mejoraFisica.getRegate() + 10;
            int nuevoTiro = mejoraFisica.getTiro() + 8;
            int nuevoFisico = mejoraFisica.getFisico() + 4;
            int nuevoDefensa = mejoraFisica.getDefensa() + 2;
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
        changeScene(event, "/FXML/ForwardAndMid/Midfielder.fxml");
    }

    @FXML
    private void handleDefensaButtonAction(ActionEvent event) {
        // Obtener la mejora física del usuario desde la base de datos
        MejoraFisica mejoraFisica = DataBaseConnection.getMejoraFisicaByUserId(idUsuario);

        if (mejoraFisica != null) {
            // Incrementar el valor de Pase en el objeto MejoraFisica
            int nuevoPase = mejoraFisica.getPase() + 5;
            int nuevoRitmo = mejoraFisica.getRitmo() + 4;
            int nuevoRegate = mejoraFisica.getRegate() + 2;
            int nuevoTiro = mejoraFisica.getTiro() + 2;
            int nuevoFisico = mejoraFisica.getFisico() + 5;
            int nuevoDefensa = mejoraFisica.getDefensa() + 10;
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
        changeScene(event, "/FXML/Defense/Defense.fxml");
    }

    @FXML
    private void handlePorteroButtonAction(ActionEvent event) {
        // Obtener la mejora física del usuario desde la base de datos
        MejoraFisica mejoraFisica = DataBaseConnection.getMejoraFisicaByUserId(idUsuario);

        if (mejoraFisica != null) {
            // Incrementar el valor de Pase en el objeto MejoraFisica
            int nuevoPase = mejoraFisica.getPase() + 5;
            int nuevoRitmo = mejoraFisica.getRitmo() + 4;
            int nuevoRegate = mejoraFisica.getRegate() + 2;
            int nuevoTiro = mejoraFisica.getTiro() + 2;
            int nuevoFisico = mejoraFisica.getFisico() + 3;
            int nuevoDefensa = mejoraFisica.getDefensa() + 10;
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
        changeScene(event, "/FXML/Doorman/Goalkeeper.fxml");
    }
}
