public class Card {
    private int rank;
    private int value;
    private int suit;
    private static String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Jack", "Queen", "King"};
    private static String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
    Card (int suit, int rank){
        this.suit = suit;
        this.rank = rank;
    }
    public String cardName(){
        return ranks[rank]+" of "+suits[suit];
    }

    public int getRank() {
        return rank;
    }

    public int getValue() {
        if(rank>=9){
            value=10;
        }else if(rank==0){
            value=11;
        }else{
            value=rank+1;
        }
        return value;
    }

    public int getSuit() {
        return suit;
    }

    public void setValue(int value){
        this.value = value;
    }
}
