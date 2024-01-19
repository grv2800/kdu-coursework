package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;


public class Main {
    public static String location="/home/hp/Downloads/IPL_2021-data.csv";

    static Logger logger=Logger.getLogger(Main.class.getName());
    static List<Team> teams=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        loadCsv(location);
        Scanner scanner = new Scanner(System.in);
           logger.info("Enter your choice: ");
           logger.info("1. To display Bowlers with at least 40 wickets for a team");
           logger.info("2. To display Details of highest wicket-taker and run-scorer for a team");
           logger.info("3. To display Top 3 run-scorers and top 3 wicket-takers of the season");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    logger.info("Enter team's name: ");
                    String teamName = scanner.nextLine();
                    displayBowlersWith40Wickets(teamName);
                    break;

                case "2":
                    logger.info("Enter team's name: ");
                    teamName = scanner.nextLine();
                    displayHighestWicketTakerAndRunScorer(teamName);
                    break;

                case "3":
                    displayTop3RunScorersAndWicketTakers();
                    break;

                default:
                    logger.info("invalid choice");
                    break;
        }
    }


    public static void loadCsv(String location) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(location));
        try {
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {

                String[] arr = line.split(",");
                String playerName = arr[0].trim();
                String teamName = arr[1].trim();
                int wickets = Integer.parseInt(arr[2].trim());
                int runs = Integer.parseInt(arr[3].trim());

                Player player = new Player(playerName, teamName, wickets, runs);
                addToTeam(player);
            }

        } catch (Exception e) {
            logger.info("exception caught");
        } finally {
            bufferedReader.close();
        }
    }

    static void addToTeam(Player player) {
        Team team = teams.stream()
                .filter(t -> t.getTeamName().equals(player.getTeam()))
                .findFirst()
                .orElseGet(() -> {
                    Team newTeam = new Team(player.getTeam());
                    teams.add(newTeam);
                    return newTeam;
                });
        team.addPlayer(player);
    }



    private static void displayBowlersWith40Wickets(String teamName) {
            Optional<Team> optionalTeam = teams.stream()
                    .filter(team -> team.getTeamName().equalsIgnoreCase(teamName))
                    .findFirst();

            if (optionalTeam.isPresent()) {
                Team team = optionalTeam.get();
                List<Player> bowlers = team.getPlayers().stream()
                        .filter(player -> player.getWickets() >= 40)
                        .toList();

                logger.info("Bowlers with at least 40 wickets");
                for (Player bowler : bowlers) {
                    String temp="Player:" + bowler.getPlayerName() + "Wickets:" + bowler.getWickets();
                    logger.info(temp);
                }
            } else {
                logger.info("Team not found!");
            }
        }

        private static void displayHighestWicketTakerAndRunScorer(String teamName) {
            Optional<Team> optionalTeam = teams.stream()
                    .filter(team -> team.getTeamName().equalsIgnoreCase(teamName))
                    .findFirst();

            if (optionalTeam.isPresent()) {
                Team team = optionalTeam.get();
                Optional<Player> highestWicketTaker = team.getPlayers().stream()
                        .max(Comparator.comparingInt(Player::getWickets));

                Optional<Player> highestRunScorer = team.getPlayers().stream()
                        .max(Comparator.comparingInt(Player::getRuns));

                logger.info("Details of the highest wicket-taker");
                highestWicketTaker.ifPresent(player ->
                        logger.info("Player: " + player.getPlayerName() + ", Wickets: " + player.getWickets()));

                logger.info("Details of the highest run-scorer" );
                highestRunScorer.ifPresent(player ->
                        logger.info("Player: " + player.getPlayerName() + ", Runs: " + player.getRuns()));
            } else {
                logger.info("Team not found!");
            }
        }

        private static void displayTop3RunScorersAndWicketTakers() {
            List<Player> allPlayers = teams.stream()
                    .flatMap(team -> team.getPlayers().stream())
                    .toList();

            List<Player> top3RunScorers = allPlayers.stream()
                    .sorted(Comparator.comparingInt(Player::getRuns).reversed())
                    .limit(3)
                    .toList();

            List<Player> top3WicketTakers = allPlayers.stream()
                    .sorted(Comparator.comparingInt(Player::getWickets).reversed())
                    .limit(3)
                    .toList();

            logger.info("Top 3 run-scorers of the season:");
            for (Player player : top3RunScorers) {
                logger.info("Player: " + player.getPlayerName() + ", Runs: " + player.getRuns());
            }
            logger.info("Top 3 wicket-takers of the season:");
            for (Player player : top3WicketTakers) {
                logger.info("Player: " + player.getPlayerName() + ", Wickets: " + player.getWickets());
            }
        }
}
