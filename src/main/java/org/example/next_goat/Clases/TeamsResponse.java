package org.example.next_goat.Clases;

import java.util.List;

public class TeamsResponse {
    private List<Team> teams;

    public List<Team> getTeams() {
        return teams;
    }

    public static class Team {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
