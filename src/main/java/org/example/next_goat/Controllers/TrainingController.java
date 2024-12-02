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
    private static final int INCREMENTO_DELANTERO_PASE = 7;
    private static final int INCREMENTO_DELANTERO_RITMO = 4;
    private static final int INCREMENTO_DELANTERO_REGATE = 10;
    private static final int INCREMENTO_DELANTERO_TIRO = 10;
    private static final int INCREMENTO_DELANTERO_FISICO = 4;
    private static final int INCREMENTO_DELANTERO_DEFENSA = 2;

    private static final int INCREMENTO_CENTRO_PASE = 8;
    private static final int INCREMENTO_CENTRO_RITMO = 4;
    private static final int INCREMENTO_CENTRO_REGATE = 10;
    private static final int INCREMENTO_CENTRO_TIRO = 8;
    private static final int INCREMENTO_CENTRO_FISICO = 4;
    private static final int INCREMENTO_CENTRO_DEFENSA = 2;

    private static final int INCREMENTO_DEFENSA_PASE = 5;
    private static final int INCREMENTO_DEFENSA_RITMO = 4;
    private static final int INCREMENTO_DEFENSA_REGATE = 2;
    private static final int INCREMENTO_DEFENSA_TIRO = 2;
    private static final int INCREMENTO_DEFENSA_FISICO = 5;
    private static final int INCREMENTO_DEFENSA_DEFENSA = 10;

    private static final int INCREMENTO_PORTERO_PASE = 5;
    private static final int INCREMENTO_PORTERO_RITMO = 4;
    private static final int INCREMENTO_PORTERO_REGATE = 2;
    private static final int INCREMENTO_PORTERO_TIRO = 2;
    private static final int INCREMENTO_PORTERO_FISICO = 3;
    private static final int INCREMENTO_PORTERO_DEFENSA = 10;

    // Obtener el id_usuario del usuario logueado
    int idUsuario = UserSession.getInstance().getIdUsuario();
    private UserController userController;

    // Método para recibir la instancia del UserController
    public void setUserController(UserController userController) {
        this.userController = userController;
    }


    private void changeScene(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent newView = loader.load();

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
    private void handleBackButtonAction(ActionEvent event) {
        changeScene(event, "/FXML/MainWindow.fxml");
    }

    @FXML
    private void handleDelanteroButtonAction(ActionEvent event) {
        actualizarMejoraFisica(INCREMENTO_DELANTERO_PASE, INCREMENTO_DELANTERO_RITMO, INCREMENTO_DELANTERO_REGATE,
                INCREMENTO_DELANTERO_TIRO, INCREMENTO_DELANTERO_FISICO, INCREMENTO_DELANTERO_DEFENSA,
                event, "/FXML/ForwardAndMid/Forward.fxml");
    }

    @FXML
    private void handleCentroButtonAction(ActionEvent event) {
        actualizarMejoraFisica(INCREMENTO_CENTRO_PASE, INCREMENTO_CENTRO_RITMO, INCREMENTO_CENTRO_REGATE,
                INCREMENTO_CENTRO_TIRO, INCREMENTO_CENTRO_FISICO, INCREMENTO_CENTRO_DEFENSA,
                event, "/FXML/ForwardAndMid/Midfielder.fxml");
    }

    @FXML
    private void handleDefensaButtonAction(ActionEvent event) {
        actualizarMejoraFisica(INCREMENTO_DEFENSA_PASE, INCREMENTO_DEFENSA_RITMO, INCREMENTO_DEFENSA_REGATE,
                INCREMENTO_DEFENSA_TIRO, INCREMENTO_DEFENSA_FISICO, INCREMENTO_DEFENSA_DEFENSA,
                event, "/FXML/Defense/Defense.fxml");
    }

    @FXML
    private void handlePorteroButtonAction(ActionEvent event) {
        actualizarMejoraFisica(INCREMENTO_PORTERO_PASE, INCREMENTO_PORTERO_RITMO, INCREMENTO_PORTERO_REGATE,
                INCREMENTO_PORTERO_TIRO, INCREMENTO_PORTERO_FISICO, INCREMENTO_PORTERO_DEFENSA,
                event, "/FXML/Doorman/Goalkeeper.fxml");
    }
}
