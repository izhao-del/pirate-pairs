import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Player> players;
    private Deck deck; 

    public Game(int numPlayers) {

        players = new ArrayList<>();
        deck = new Deck();

        for (int i = 1; i <= numPlayers; i++) {
            players.add(new Player("Player " + i));
        }

        System.out.println("Game started with " + numPlayers + " players.");
    }
    public void play() {
        System.out.println("Game starts!");
        Deck deck = new Deck();
        
        for(Player player : players) {
            player.draw(deck);
        }
    }
}
