public class Deck {

    private Card[] cards;
    private int deckIndex;  

    public Deck() {
        cards = new Card[55]; 
        deckIndex = 0;
        buildDeck();
        shuffle();
    }

    // build deck
    private void buildDeck() {
        int index = 0;
        for (int value = 1; value <= 10; value++) {
            for (int count = 0; count < value; count++) {
                cards[index++] = new Card(value);
            }
        }
    }

    // shuffle
    public void shuffle() {
        for (int i = cards.length - 1; i > 0; i--) {
            int j = (int)(Math.random() * (i + 1));
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    public Card drawCard() {
        if (deckIndex >= cards.length) return null;  // deck empty
        return cards[deckIndex++];
    }

    // check empty deck
    public boolean isEmpty() {
        return deckIndex >= cards.length;
    }
}
