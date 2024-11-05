package org.example.next_goat.Clases;

import java.util.List;

public class ClasificacionResponse {
    private List<Standing> standings;

    public List<Standing> getStandings() {
        return standings;
    }

    public static class Standing {
        private List<EquipoData> table;

        public List<EquipoData> getTable() {
            return table;
        }
    }

    public static class EquipoData {
        private int position;
        private Team team;
        private int points;

        // Getters
        public int getPosition() {
            return position;
        }

        public Team getTeam() {
            return team;
        }

        public int getPoints() {
            return points;
        }
    }

    public static class Team {
        private String name;
        private String crest;

        // Getter
        public String getName() {
            return name;
        }
        public String getCrest() {
            return crest;
        }
    }
}
