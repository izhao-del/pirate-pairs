public class Game {

    private Player[] players;
    private int numPlayers;
    private Deck deck;
    private Card[] discardPile;
    private int discardIndex;

    // setting up
    public Game(int numPlayers) {
        this.numPlayers = numPlayers;
        players = new Player[numPlayers];
        deck = new Deck();
        discardPile = new Card[55];
        discardIndex = 0;

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player("Player " + (i + 1));
        }

        System.out.println("Game started with " + numPlayers + " players.");
    }

    private Player findSmallestCardOwner(Player current) {
        Player target = null;
        int smallestValue = Integer.MAX_VALUE;

        for (int i = 0; i < players.length; i++) {
            Player p = players[i];
            if (p != current && p.isActive() && p.getCard() != null) {
                int value = p.getCard().getValue();
                if (value < smallestValue) {
                    smallestValue = value;
                    target = p;
                }
            }
        }
        return target;
    }

    // turn based play
    public void playGame() {
        int losingScore = (60 / numPlayers) + 1;
        System.out.println("Losing score is: " + losingScore + "\n");

        boolean gameOver = false;
        while (!gameOver) {
            System.out.println("New round starts!\n");

            // first draw
            for (int i = 0; i < players.length; i++) {
                if (players[i].isActive()) {
                    players[i].draw(deck);
                }
            }
            System.out.println();

            // turn taking
            for (int i = 0; i < players.length; i++) {
                Player player = players[i];
                if (!player.isActive()) continue;

                Player target = findSmallestCardOwner(player);

                if (target != null) {
                    // steal
                    Card stolen = target.getCard();
                    player.addPoints(stolen.getValue());
                    discardPile[discardIndex++] = stolen;
                    player.clearHand();
                    target.clearHand();

                    System.out.println(player.getName() + " stole a " +
                            stolen.getValue() + " from " + target.getName());
                } else {
                    //draw
                    player.draw(deck);
                    System.out.println(player.getName() + " chose to draw.");
                }
            }

            System.out.println("\nScores after this round:");
            int activeCount = 0;
            Player lastActive = null;
            for (int i = 0; i < players.length; i++) {
                Player player = players[i];
                System.out.println(player.getName() + ": " + player.getPoints());
                if (player.isActive()) {
                    activeCount++;
                    lastActive = player;
                }
            }

            for (int i = 0; i < players.length; i++) {
                Player player = players[i];
                if (player.isActive() && player.getPoints() > losingScore) {
                    player.deactivate();
                    System.out.println(player.getName() + " exceeded the losing score and is out!");
                }
            }

            System.out.println("\nPlayers remaining: " + activeCount + "\n");

            //check for win
            if (activeCount <= 1) {
                gameOver = true;
                if (lastActive != null) {
                    System.out.println("Game over! The winner is " +
                            lastActive.getName() + " with " +
                            lastActive.getPoints() + " points!");
                } else {
                    System.out.println("All players eliminated. No winner!");
                }
            }
        }
    }
}
