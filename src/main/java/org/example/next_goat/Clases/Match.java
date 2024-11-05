package org.example.next_goat.Clases;

public class Match {
    private Team homeTeam;
    private Team awayTeam;
    private String date; // Podrías usar un tipo de dato más específico para la fecha.

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return homeTeam.toString() + " vs " + awayTeam.toString() + " on " + date; // Personaliza la cadena según tus necesidades
    }

    public class Team {
        private String name;

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return  name ;
        }
    }
}
