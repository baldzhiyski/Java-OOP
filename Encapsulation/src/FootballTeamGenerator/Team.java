package FootballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        setName(name);
        this.players = new ArrayList<>();
    }

    private void setName(String name) {
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void addPlayer(Player player){
        players.add(player);
    }
    public boolean containsPlayer(String name){
        for (Player player : this.players) {
            if(player.getName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }
    public void removePlayer(String name){
        for (Player player : players) {
            if(player.getName().equalsIgnoreCase(name)){
                players.remove(player);
                return;
            }
        }
    }
    public double getRating(){
        return players.stream()
                .mapToDouble(Player::overallSkillLevel)
                .sum();
    }

}
