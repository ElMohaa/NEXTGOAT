package org.example.next_goat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.next_goat.DataBase.DataBaseConnection;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RutinaController {

    @FXML
    private ComboBox<String> posicionComboBox;

    @FXML
    private VBox rutinaVBox;

    @FXML
    public void initialize() {

        posicionComboBox.getItems().addAll("GOALKEEPER", "DEFENSE", "MIDFIELDER", "FORWARD");


        posicionComboBox.setOnAction(event -> {
            String posicionSeleccionada = posicionComboBox.getValue();
            cargarRutinas(posicionSeleccionada);
        });
    }

    private void cargarRutinas(String posicion) {

        Map<String, List<String>> rutinasSemanales = DataBaseConnection.getRutinasPorPosicion(posicion);


        rutinaVBox.getChildren().clear();


        String[] diasDeSemana = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};

        for (String dia : diasDeSemana) {
            if (rutinasSemanales.containsKey(dia)) {
                // Crear un VBox para agrupar las actividades del día
                VBox diaVBox = new VBox();
                diaVBox.getStyleClass().add("vbox-dia");
                rutinaVBox.getChildren().add(diaVBox);

                // Crear un Label para el día de la semana
                Label diaLabel = new Label(dia);
                diaLabel.getStyleClass().add("label-dia");
                diaVBox.getChildren().add(diaLabel);

                // Crear un VBox para contener las actividades de ese día
                VBox actividadesVBox = new VBox(10);
                diaVBox.getChildren().add(actividadesVBox);

                Set<String> actividadesUnicas = new HashSet<>();

                // Añadir las actividades correspondientes para ese día
                List<String> rutinas = rutinasSemanales.get(dia);
                for (String rutina : rutinas) {
                    List<String> actividades = DataBaseConnection.getActividadesPorRutina(rutina);

                    for (String actividad : actividades) {
                        // Separar la actividad en título, duración y descripción
                        String[] partes = actividad.split(" - ");
                        if (partes.length < 3) {
                            continue; // Si la actividad no tiene 3 partes, saltarla
                        }

                        String titulo = partes[0];
                        String duracion = partes[1];
                        String descripcion = partes[2];

                        // Crear una clave única para la actividad (combinando título, duración y descripción)
                        String actividadClave = titulo + " - " + duracion + " - " + descripcion;

                        // Comprobar si la actividad ya se ha añadido (evitar duplicados)
                        if (!actividadesUnicas.contains(actividadClave)) {

                            actividadesUnicas.add(actividadClave);


                            Label tituloLabel = new Label(titulo);
                            tituloLabel.getStyleClass().add("label-titulo");

                            Label duracionLabel = new Label("Durattion: " + duracion);
                            duracionLabel.getStyleClass().add("label-duracion");

                            Label descripcionLabel = new Label(descripcion);
                            descripcionLabel.getStyleClass().add("label-descripcion");
                            descripcionLabel.setWrapText(true);


                            VBox actividadVBox = new VBox(5);
                            actividadVBox.getStyleClass().add("vbox-actividad");
                            actividadVBox.getChildren().addAll(tituloLabel, duracionLabel, descripcionLabel);
                            actividadesVBox.getChildren().add(actividadVBox);
                        }
                    }
                }
            }
        }
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

    @FXML
    private void buttonHome(ActionEvent event) {
        changeScene(event, "/FXML/MainWindow.fxml");
    }
    @FXML
    private void buttonUser(ActionEvent event) {
        changeScene(event, "/FXML/User.fxml");
    }
}
