import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;
    private Deck deck;
    private String currentHand;
    private int handValue;
    private int aces; //number of aces in a hand
    Hand(Deck deck){
        this.deck = deck;
        hand = new ArrayList<Card>();
        for(int i=0; i<2; i++) {
            hand.add(deck.drawCard());
        }
    }

    public Card cardShow(int index){
        return hand.get(index);
    }

    public String handName(){
        currentHand = "";
        for(int i=0; i<hand.size(); i++){
            currentHand += hand.get(i).cardName();
            if(i<hand.size()-1){
                currentHand+= ", ";
            }
        }
        return currentHand;
    }

    public int handValue(){
        handValue=0;
        for(int i=0; i<hand.size(); i++){
            handValue += hand.get(i).getValue();
            if(hand.get(i).getValue()==11){
                aces++;
            }
            while (aces>0 && handValue>21){
                handValue-=10;
                aces--;
            }
        }
        return handValue;
    }

    public void hit(){
        hand.add(deck.drawCard());
    }

    public int handSize(){
        return hand.size();
    }

}
