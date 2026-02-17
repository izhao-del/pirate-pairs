import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    public void draw(Deck deck) {
        Card card = deck.drawCard();
        if (card != null) {
            hand.add(card);
            System.out.println(name + " drew a " + card.getValue());
        }
    }  

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }
}
