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
    private static final int IGUAL_ALTETISMO = 0;
    private static final int INCREMENTO_ATLETISMO_RITMO = 8;
    private static final int INCREMENTO_DELANTERO_FISICO = 10;

    private static final int DESCREMENTO_LESION = -7;

    int idUsuario = UserSession.getInstance().getIdUsuario();
    private UserController userController;


    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    // Método genérico para cargar y cambiar la escena
    private void changeScene(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent newView = loader.load();

            // Si estamos cargando Training.fxml, también pasamos el controlador de UserController
            if (fxmlPath.equals("/FXML/Training.fxml")) {
                UserController userController = (UserController) ((Node) event.getSource()).getScene().getWindow()
                        .getUserData();

                TrainingController trainingController = loader.getController();
                trainingController.setUserController(userController);
            }

            Scene newScene = new Scene(newView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actualizarMejoraFisica(int incrementoPase, int incrementoRitmo, int incrementoRegate,
                                        int incrementoTiro, int incrementoFisico, int incrementoDefensa,
                                        ActionEvent event, String siguienteEscena) {

        MejoraFisica mejoraFisica = DataBaseConnection.getMejoraFisicaByUserId(idUsuario);

        if (mejoraFisica != null) {
            int nuevoPase = mejoraFisica.getPase() + incrementoPase;
            int nuevoRitmo = mejoraFisica.getRitmo() + incrementoRitmo;
            int nuevoRegate = mejoraFisica.getRegate() + incrementoRegate;
            int nuevoTiro = mejoraFisica.getTiro() + incrementoTiro;
            int nuevoFisico = mejoraFisica.getFisico() + incrementoFisico;
            int nuevoDefensa = mejoraFisica.getDefensa() + incrementoDefensa;
            int media = mejoraFisica.calcularMedia(nuevoDefensa, nuevoPase, nuevoFisico, nuevoRegate, nuevoRitmo, nuevoTiro);

            // Actualizar los valores en el objeto MejoraFisica
            mejoraFisica.setPase(nuevoPase);
            mejoraFisica.setFisico(nuevoFisico);
            mejoraFisica.setRegate(nuevoRegate);
            mejoraFisica.setRitmo(nuevoRitmo);
            mejoraFisica.setTiro(nuevoTiro);
            mejoraFisica.setDefensa(nuevoDefensa);
            mejoraFisica.setMedia(media);

            boolean actualizado = DataBaseConnection.updateMejoraFisica(mejoraFisica);

            if (actualizado) {
                System.out.println("Valor de pase actualizado exitosamente.");

                if (userController != null) {
                    userController.actualizarLabelPase(nuevoPase);
                }
            } else {
                System.out.println("No se pudo actualizar el valor de Pase.");
            }
        }
        changeScene(event, siguienteEscena);
    }

    @FXML
    private void handleLaLigaButtonAction(ActionEvent event) {
        changeScene(event, "/FXML/LaLigaView.fxml");
    }

    @FXML
    private void handleEntreButtonAction(ActionEvent event) {
        changeScene(event, "/FXML/Training.fxml");
    }

    @FXML
    private void buttonAtletismo(ActionEvent event) {
        actualizarMejoraFisica(IGUAL_ALTETISMO, INCREMENTO_ATLETISMO_RITMO, IGUAL_ALTETISMO, IGUAL_ALTETISMO, INCREMENTO_DELANTERO_FISICO, IGUAL_ALTETISMO, event, "/FXML/Physical.fxml");
    }

    @FXML
    private void buttonRecovery(ActionEvent event) {
        actualizarMejoraFisica(DESCREMENTO_LESION, DESCREMENTO_LESION, DESCREMENTO_LESION, DESCREMENTO_LESION, DESCREMENTO_LESION, DESCREMENTO_LESION, event, "/FXML/Recovery.fxml");
    }

    @FXML
    private void buttonUser(ActionEvent event) {
        changeScene(event, "/FXML/User.fxml");
    }
}
