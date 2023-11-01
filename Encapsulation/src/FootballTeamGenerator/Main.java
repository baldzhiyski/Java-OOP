package FootballTeamGenerator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        Map<String,Team> teams = new LinkedHashMap<>();
        while (!command.equalsIgnoreCase("end")){
            String[] tokens = command.split(";");
            switch (tokens[0]){
                case"Add":
                    addingPlayer(teams, tokens);
                    break;
                case "Team":
                    creatingTeam(teams, tokens);
                    break;
                case "Remove":
                    removePlayer(teams, tokens);
                    break;
                case "Rating":
                    getRatings(teams, tokens);
                    break;
            }


            command=scan.nextLine();
        }
    }

    private static void getRatings(Map<String, Team> teams, String[] tokens) {
        String teamName = tokens[1];
        if(!teams.containsKey(teamName)){
            System.out.printf("Team %s does not exist.\n",teamName);
        }else{
            System.out.printf("%s - %.0f\n",teamName, teams.get(teamName).getRating());
        }
    }

    private static void removePlayer(Map<String, Team> teams, String[] tokens) {
        if(!teams.get(tokens[1]).containsPlayer(tokens[2])){
            System.out.printf("Player %s is not in %s team.\n"
            , tokens[2], tokens[1]);
        }else{
            teams.get(tokens[1]).removePlayer(tokens[2]);
        }
    }

    private static void creatingTeam(Map<String, Team> teams, String[] tokens) {
        try {
            Team team = new Team(tokens[1]);
            teams.putIfAbsent(tokens[1],team);
        }catch (IllegalArgumentException e ){
            System.out.println(e.getMessage());
        }
    }

    private static void addingPlayer(Map<String, Team> teams, String[] tokens) {
        if(teams.containsKey(tokens[1])){
            try {
                Player player = new Player(tokens[2],
                        Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]),
                        Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]),
                        Integer.parseInt(tokens[7]));
                teams.get(tokens[1]).addPlayer(player);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

        }else{
            System.out.printf("Team %s does not exist.\n", tokens[1]);
        }
    }
}
