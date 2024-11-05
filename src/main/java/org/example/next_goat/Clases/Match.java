package org.example.next_goat.Clases;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Match {
    private Team homeTeam;
    private Team awayTeam;
    private String utcDate;

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public String getDate() {
        return utcDate;
    }

    public String getFormattedMatch() {
        if (utcDate == null) {
            return homeTeam.getShortName() + " vs " + awayTeam.getShortName() + " - Fecha no disponible";
        }

        try {
            OffsetDateTime matchDateTime = OffsetDateTime.parse(utcDate);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ',' HH:mm ");
            String formattedDate = matchDateTime.format(formatter);
            return homeTeam.getShortName() + " vs " + awayTeam.getShortName() + ": " + formattedDate;
        } catch (Exception e) {
            // Si ocurre un error al parsear, manejarlo aquí
            return homeTeam.getShortName() + " vs " + awayTeam.getShortName() + " - Fecha inválida";
        }
    }


    public class Team {
        private String name;
        private String shortName; // Añadir shortName

        public String getName() {
            return name;
        }

        public String getShortName() {
            return shortName; // Método para obtener shortName
        }
    }
}
