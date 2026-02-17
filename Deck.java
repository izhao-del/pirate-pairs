import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        buildDeck();
        shuffle();
    }

    private void buildDeck() {
        for (int i = 1; i <= 10; i++) {
            for(int j = 0; j < i; j++) { 
                cards.add(new Card(i));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }  

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
