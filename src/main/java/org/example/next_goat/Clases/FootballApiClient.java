package org.example.next_goat.Clases;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class FootballApiClient {
    // Base URL para todas las solicitudes
    private static final String BASE_URL = "https://api.football-data.org/v4";

    // Token de autenticación de la API
    private static final String API_KEY = "e6e28ccf195848d380518569beb75823";

    // Método para obtener las competiciones
    public String getCompetitions() throws IOException {
        String endpoint = "/competitions";
        return sendRequest(endpoint);
    }

    // Método para buscar partidos por nombre del equipo
    public String getMatchesByTeamName(String teamName) throws IOException {
        String endpoint = "/teams?name=" + teamName;
        return sendRequest(endpoint);
    }

    // Método para buscar el equipo por nombre y obtener su ID
    public int getTeamIdByName(String teamName) throws IOException {
        String endpoint = "/teams?name=" + URLEncoder.encode(teamName, StandardCharsets.UTF_8);
        String jsonResponse = sendRequest(endpoint);

        if (jsonResponse == null || jsonResponse.isEmpty()) {
            throw new RuntimeException("Respuesta vacía de la API para el equipo: " + teamName);
        }

        Gson gson = new Gson();
        TeamsResponse teamsResponse = gson.fromJson(jsonResponse, TeamsResponse.class);

        if (teamsResponse.getTeams() != null && !teamsResponse.getTeams().isEmpty()) {
            return teamsResponse.getTeams().get(0).getId(); // Retornar el ID del primer equipo encontrado
        } else {
            throw new RuntimeException("No se encontró el equipo con el nombre: " + teamName);
        }
    }



    // Método para obtener los partidos del equipo usando su ID
    public String getMatchesByTeamId(int teamId) throws IOException {
        String endpoint = "/teams/" + teamId + "/matches?status=SCHEDULED";
        return sendRequest(endpoint);
    }
    //Obtener los proximos partidos de una competitcion
    public String getUpcomingMatchesByCompetition(int competitionId) throws IOException {
        String endpoint = "/competitions/" + competitionId + "/matches?status=SCHEDULED&limit=8";
        return sendRequest(endpoint);
    }

    // Método para obtener la clasificación por ID de competición
    public String getClasificacionByCompetition(int competitionId) throws IOException {
        String endpoint = "/competitions/" + competitionId + "/standings"; // URL para la clasificación
        return sendRequest(endpoint);
    }

    // Método genérico para realizar la solicitud HTTP
    private String sendRequest(String endpoint) throws IOException {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        connection.setRequestProperty("X-Auth-Token", API_KEY);

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return content.toString();
        } else {
            throw new RuntimeException("HTTP error code : " + responseCode);
        }
    }
}