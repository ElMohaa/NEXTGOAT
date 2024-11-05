package org.example.next_goat.Controllers;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.cell.PropertyValueFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.next_goat.Clases.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LaLigaController {
    @FXML
    private TextField equipoField; // Buscar equipo

    @FXML
    private ComboBox<Competition> leagueComboBox;  // Cambiado para usar el objeto Competition
    @FXML
    private ListView<String> matchesListView; // Para mostrar los partidos
    @FXML
    private TableView<Equipo> clasificacionTable; // Para mostrar la clasificación
    @FXML
    private TableColumn<Equipo, String> posicionCol;// Columna de posición
    @FXML
    private TableColumn<Equipo, String> escudoCol;
    @FXML
    private TableColumn<Equipo, String> equipoCol; // Columna de equipo
    @FXML
    private TableColumn<Equipo, String> puntosCol; // Columna de puntos

    private FootballApiClient footballApiClient = new FootballApiClient();

    // Método de inicialización
    @FXML
    public void initialize() {
        setupTableColumns(); // Configurar columnas de la tabla
        configureEscudoColumn();
        loadCompetitions(); // Cargar ligas al iniciar la ventana

        // Detectar selección en el ComboBox
        leagueComboBox.setOnAction(event -> {
            Competition selectedCompetition = leagueComboBox.getValue();
            if (selectedCompetition != null) {
                loadClasificacion(selectedCompetition);
                loadUpcomingMatches(selectedCompetition);// Cargar la clasificación de la liga seleccionada
            }
        });
    }
    private void configureEscudoColumn() {
        escudoCol.setCellValueFactory(new PropertyValueFactory<>("crest"));
        escudoCol.setCellFactory(column -> new TableCell<Equipo, String>() {
            private final ImageView imageView = new ImageView();

            {
                imageView.setFitWidth(23.5); // Ancho del escudo
                imageView.setFitHeight(23.5); // Altura del escudo
            }

            @Override
            protected void updateItem(String crestUrl, boolean empty) {
                super.updateItem(crestUrl, empty);
                if (crestUrl == null || empty) {
                    setGraphic(null);
                } else {
                    imageView.setImage(new Image(crestUrl, true));
                    setGraphic(imageView);
                }
            }
        });
    }

    // Configurar columnas de la tabla
    private void setupTableColumns() {
        posicionCol.setCellValueFactory(new PropertyValueFactory<>("posicion"));
        equipoCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        puntosCol.setCellValueFactory(new PropertyValueFactory<>("puntos"));
    }
    private void loadUpcomingMatches(Competition competition) {
        new Thread(() -> {
            try {
                String matchesData = footballApiClient.getUpcomingMatchesByCompetition(competition.getId());
                System.out.println("Aqui estan los equipos"+matchesData);
                Gson gson = new Gson();
                MatchesResponse matchesResponse = gson.fromJson(matchesData, MatchesResponse.class);

                // Actualizar la UI con los próximos partidos en el hilo principal
                Platform.runLater(() -> {
                    matchesListView.getItems().clear();
                    if (matchesResponse != null && matchesResponse.getMatches() != null) {
                        matchesResponse.getMatches().stream().limit(6).forEach(match ->
                                matchesListView.getItems().add(match.getFormattedMatch()));
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    // Cargar las competiciones (ligas)
    private void loadCompetitions() {
        new Thread(() -> {
            try {
                String jsonResponse = footballApiClient.getCompetitions();
                Gson gson = new GsonBuilder().create();
                CompetitionsResponse competitionsResponse = gson.fromJson(jsonResponse, CompetitionsResponse.class);

                // Extraer las competiciones directamente
                List<Competition> competitions = competitionsResponse.getCompetitions();

                // Actualizar el ComboBox en el hilo de la UI
                Platform.runLater(() -> {
                    leagueComboBox.getItems().clear();
                    leagueComboBox.getItems().addAll(competitions); // Añadir competiciones directamente
                });

            } catch (IOException e) {
                e.printStackTrace(); // Maneja la excepción como consideres apropiado
            }
        }).start();
    }


    // Cargar la clasificación de la liga seleccionada
    // Método para cargar la clasificación de la liga seleccionada
    private void loadClasificacion(Competition competition) {
        try {
            String clasificacionData = footballApiClient.getClasificacionByCompetition(competition.getId());
            //System.out.println("Clasificación de la liga: " + clasificacionData); // Depuración

            // Parsear la respuesta
            Gson gson = new Gson();
            ClasificacionResponse clasificacionResponse = gson.fromJson(clasificacionData, ClasificacionResponse.class);

            // Limpiar la tabla antes de agregar nuevos elementos
            clasificacionTable.getItems().clear();

            // Comprobar si clasificacionResponse y standings son nulos o vacíos
            List<Equipo> equipos = null;
            if (clasificacionResponse != null && clasificacionResponse.getStandings() != null && !clasificacionResponse.getStandings().isEmpty()) {
                equipos = clasificacionResponse.getStandings().get(0).getTable().stream()
                        .map(standing -> new Equipo(
                                standing.getPosition(),
                                standing.getTeam().getName(),
                                standing.getPoints(),
                                standing.getTeam().getCrest()
                        )).collect(Collectors.toList()); // Crear objetos Equipo a partir de los datos

            }

            if (equipos != null && !equipos.isEmpty()) {
                clasificacionTable.getItems().addAll(equipos);
            } else {
                System.out.println("No hay equipos en la clasificación o la respuesta es nula.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    // Buscar partidos por nombre del equipo
    @FXML
    private void handleBuscarEquipo() {
        String teamName = equipoField.getText().trim().toLowerCase(); // Convertir a minúsculas
        if (teamName.isEmpty()) {
            System.out.println("Por favor, ingrese un nombre de equipo.");
            return; // No hacer nada si el campo está vacío
        }

        try {
            // Obtener el ID del equipo
            int teamId = footballApiClient.getTeamIdByName(teamName);

            // Ahora obtener los partidos usando el ID del equipo
            String matchesData = footballApiClient.getMatchesByTeamId(teamId);
            System.out.println("Partidos del equipo: " + matchesData); // Depuración

            // Parsear la respuesta
            Gson gson = new Gson();
            MatchesResponse matchesResponse = gson.fromJson(matchesData, MatchesResponse.class);

            // Limpiar el ListView antes de agregar nuevos elementos
            matchesListView.getItems().clear();

            // Añadir los partidos al ListView
            if (matchesResponse != null && matchesResponse.getMatches() != null) {
                for (Match match : matchesResponse.getMatches()) {
                    matchesListView.getItems().add(match.toString());
                }
            } else {
                System.out.println("No hay partidos disponibles para el equipo.");
            }

        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
            e.printStackTrace(); // Imprimir la traza de la excepción
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
            e.printStackTrace(); // Imprimir la traza de la excepción
        }
    }

    @FXML
    private void buttonBack(ActionEvent event) {
        try {
            // Cargar la vista de LaLiga
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MainWindow.fxml"));
            Parent traView = loader.load();

            // Crear una nueva escena con la ventana de LaLiga
            Scene laLigaScene = new Scene(traView);

            // Obtener el stage actual y cambiar la escena
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(laLigaScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}