package org.example;
public class Player {
    private String playerName;
    private String team;
    private int wickets;
    private int runs;

    public String getPlayerName() {
        return playerName;
    }

    public String getTeam() {
        return team;
    }

    public int getWickets() {
        return wickets;
    }

    public int getRuns() {
        return runs;
    }

    public Player(String player, String team, int wickets, int runs){
        this.playerName=player;
        this.team=team;
        this.wickets=wickets;
        this.runs=runs;
    }
}
