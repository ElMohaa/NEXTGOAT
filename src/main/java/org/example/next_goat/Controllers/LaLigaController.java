package org.example.next_goat.Controllers;

import javafx.scene.control.*;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.next_goat.Clases.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LaLigaController {

    @FXML
    private ComboBox<Competition> leagueComboBox;
    @FXML
    private ListView<Match> matchesListView;
    @FXML
    private TableView<Equipo> clasificacionTable;
    @FXML
    private TableColumn<Equipo, String> posicionCol;
    @FXML
    private TableColumn<Equipo, String> escudoCol;
    @FXML
    private TableColumn<Equipo, String> equipoCol;
    @FXML
    private TableColumn<Equipo, String> puntosCol;

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
                loadUpcomingMatches(selectedCompetition);
            }
        });

        matchesListView.setCellFactory(param -> new ListCell<Match>() {
            private final ImageView homeCrestView = new ImageView();
            private final ImageView awayCrestView = new ImageView();
            private final Label teamsLabel = new Label();
            private final Label dateLabel = new Label();

            {
                homeCrestView.setFitHeight(20);
                homeCrestView.setFitWidth(20);
                awayCrestView.setFitHeight(20);
                awayCrestView.setFitWidth(20);
            }

            @Override
            protected void updateItem(Match match, boolean empty) {
                super.updateItem(match, empty);

                if (match == null || empty) {
                    setGraphic(null);
                } else {
                    homeCrestView.setImage(new Image(match.getHomeTeamCrest()));
                    awayCrestView.setImage(new Image(match.getAwayTeamCrest()));


                    teamsLabel.setText(match.getHomeTeam().getShortName() + " VS " + match.getAwayTeam().getShortName());
                    dateLabel.setText(match.getFormattedDate());

                    HBox teamsBox = new HBox(5, homeCrestView, teamsLabel, awayCrestView);
                    teamsBox.setStyle("-fx-alignment: center;");

                    VBox mainBox = new VBox(teamsBox, dateLabel);
                    mainBox.setStyle("-fx-alignment: center; -fx-spacing: 2;"); // Ajusta el espaciado si es necesario

                    setGraphic(mainBox);
                }
            }
        });
        leagueComboBox.setCellFactory(param -> new ListCell<Competition>() {
            private final ImageView logoView = new ImageView();
            private final Label nameLabel = new Label();

            @Override
            protected void updateItem(Competition competition, boolean empty) {
                super.updateItem(competition, empty);
                if (competition == null || empty) {
                    setGraphic(null);
                } else {
                    nameLabel.setText(competition.getName());
                    if (competition.getCode().equals("WC")){
                        competition.setCode("qatar");
                    }else if (competition.getCode().equals("DED")){
                        competition.setCode("ED");
                    }else if (competition.getCode().equals("EC")){
                        competition.setCode("ec");
                    }else if (competition.getCode().equals("BSA")){
                        competition.setCode("bsa");
                    }
                    String emblemUrl = "https://crests.football-data.org/" + competition.getCode() + ".png";
                    logoView.setImage(new Image(emblemUrl));
                    logoView.setFitHeight(23.5);
                    logoView.setFitWidth(23.5);

                    HBox box = new HBox(12, logoView, nameLabel);
                    setGraphic(box);
                }
            }
        });

        leagueComboBox.setButtonCell(new ListCell<Competition>() {
            private final ImageView logoView = new ImageView();
            private final Label nameLabel = new Label();

            @Override
            protected void updateItem(Competition competition, boolean empty) {
                super.updateItem(competition, empty);
                if (competition == null || empty) {
                    setGraphic(null);
                } else {
                    nameLabel.setText(competition.getName());


                    String emblemUrl = "https://crests.football-data.org/" + competition.getCode() + ".png";
                    logoView.setImage(new Image(emblemUrl));
                    logoView.setFitHeight(23);
                    logoView.setFitWidth(23);

                    HBox box = new HBox(14, logoView, nameLabel);
                    setGraphic(box);
                }
            }
        });


    }
    private void configureEscudoColumn() {
        escudoCol.setCellValueFactory(new PropertyValueFactory<>("crest"));
        escudoCol.setCellFactory(column -> new TableCell<Equipo, String>() {
            private final ImageView imageView = new ImageView();

            {
                imageView.setFitWidth(23.5);
                imageView.setFitHeight(23.5);
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
                //System.out.println("Aqui estan los equipos"+matchesData);
                Gson gson = new Gson();
                MatchesResponse matchesResponse = gson.fromJson(matchesData, MatchesResponse.class);

                // Actualizar la UI con los próximos partidos en el hilo principal
                Platform.runLater(() -> {
                    matchesListView.getItems().clear();
                    if (matchesResponse != null && matchesResponse.getMatches() != null) {
                        matchesListView.getItems().addAll(matchesResponse.getMatches().stream().limit(9).collect(Collectors.toList()));
                    }
                });

            } catch (Exception e) {
                showWindowError("You not hava2222e1 internet connection");

            }
        }).start();
    }


    // Cargar las ligas
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
                    leagueComboBox.getItems().addAll(competitions);
                });

            } catch (Exception e) {
                showWindowError("Something went wrong. Check your internet connection.");
            }
        }).start();
    }



    // Método para cargar la clasificación de la liga seleccionada
    private void loadClasificacion(Competition competition) {
        try {
            String clasificacionData = footballApiClient.getClasificacionByCompetition(competition.getId());
            //System.out.println("Clasificación de la liga: " + clasificacionData);

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

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    @FXML
    private void buttonBack(ActionEvent event) {
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
    public void showWindowError(String text){
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/error.fxml"));
                Parent dialogParent = loader.load();
                Scene dialogScene = new Scene(dialogParent);

                ErrorController puwc=loader.getController();
                puwc.setErrorText(text);

                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(dialogScene);
                stage.toFront();
                stage.show();
            } catch (Exception ex) {
                System.err.println("Error: "+ ex.getMessage());

            }
        });
    }



}