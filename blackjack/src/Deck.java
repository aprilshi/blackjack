import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private static ArrayList<Card> deck;
    Deck(){
        deck = new ArrayList<Card>();
        for(int i=0; i<4; i++){
            for(int j=0; j<13; j++){
                deck.add(new Card(i,j));
            }
        }
    }

    public void shuffle(){
        System.out.println("Shuffling the deck...");
        Collections.shuffle(deck);
    }

    public Card drawCard(){
        return deck.remove(0);
    }

    public static int deckSize(){
        return deck.size();
    }
}
