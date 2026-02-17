public class Player {

    private String name;
    private Card[] hand;     // fixed-size array for cards (size 1 for Pairs)
    private int handCount;   // number of cards currently in hand
    private int points;
    private boolean isActive;

    public Player(String name) {
        this.name = name;
        this.hand = new Card[1]; // only need 1 card in hand
        this.handCount = 0;
        this.points = 0;
        this.isActive = true;
    }

    // Draw a card from the deck
    public void draw(Deck deck) {
        if (handCount >= hand.length) return; // already has a card
        Card card = deck.drawCard();
        if (card != null) {
            hand[0] = card;
            handCount = 1;
            System.out.println(name + " drew a " + card.getValue());
        }
    }

    // Clear the hand (after stealing)
    public void clearHand() {
        hand[0] = null;
        handCount = 0;
    }

    // Add points
    public void addPoints(int value) {
        points += value;
    }

    public int getPoints() {
        return points;
    }

    // Check if player is active
    public boolean isActive() {
        return isActive;
    }

    public void deactivate() {
        isActive = false;
    }

    public String getName() {
        return name;
    }

    // Get the first (and only) card in hand
    public Card getCard() {
        if (handCount > 0) return hand[0];
        return null;
    }
}
