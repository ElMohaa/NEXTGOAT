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
    public String getHomeTeamCrest() {
        return homeTeam.getCrest();
    }

    public String getAwayTeamCrest() {
        return awayTeam.getCrest();
    }

    public String getFormattedMatch() {
        if (utcDate == null) {
            return homeTeam.getShortName() + " vs " + awayTeam.getShortName() + " - Fecha no disponible";
        }

        try {
            OffsetDateTime matchDateTime = OffsetDateTime.parse(utcDate);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ',' HH:mm ");
            String formattedDate = matchDateTime.format(formatter);
            return homeTeam.getShortName() + " VS " + awayTeam.getShortName() + ": " + formattedDate;
        } catch (Exception e) {

            return homeTeam.getShortName() + " VS " + awayTeam.getShortName() + " - Fecha inválida";
        }
    }
    public String getFormattedDate() {
        if (utcDate == null) return "Fecha no disponible";
        try {
            OffsetDateTime matchDateTime = OffsetDateTime.parse(utcDate);
            return matchDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
        } catch (Exception e) {
            return "Fecha inválida";
        }
    }


    public class Team {
        private String name;
        private String crest;

        private String shortName; // Añadir shortName

        public String getName() {
            return name;
        }
        public String getCrest() {
            return crest;
        }


        public String getShortName() {
            return shortName;
        }
    }
}
