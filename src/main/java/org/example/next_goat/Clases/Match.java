package org.example.next_goat.Clases;

public class Match {
    private String homeTeam;
    private String awayTeam;
    private String date; // Podrías usar un tipo de dato más específico para la fecha.

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return homeTeam + " vs " + awayTeam + " on " + date; // Personaliza la cadena según tus necesidades
    }
}
