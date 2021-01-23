public class Dealer extends Player{
    private Hand hand;
    Dealer(){
        hand = new Hand(deck);
        System.out.println("One of the dealer's cards is the " + hand.cardShow(0).cardName() + ".");
    }
    public void dealerTurn(){
        System.out.println("The dealer's hand has: " +hand.handName());
        if(checkBlackjack(super.getHand())){
            if(!checkBlackjack(hand)){
                blackjackWin();
            }else{
                draw();
            }
            return;
        }
        if(checkBlackjack(hand)){
            lose();
            return;
        }
        while(hand.handValue()<17){
            System.out.println("The dealer will hit.");
            hand.hit();
            System.out.println("The dealer's hand has: " +hand.handName());
            if(checkBust(hand)){
                System.out.println("The dealer busted!");
                win();
                break;
            }
        }
        if(hand.handValue()<=21){
            System.out.println("The dealer will stand.");
            winLose();
        }
    }
    public void winLose(){
        int playerValue = super.getHand().handValue();
        int dealerValue = hand.handValue();
        if((21-playerValue)<(21-dealerValue)) {
            win();
        }else if(playerValue==dealerValue){
            draw();
        }else{
            lose();
        }
    }
}
