import java.util.Scanner;

public class Player {
    protected static int money;
    private static int bet;
    private static String name;
    private static Hand hand;
    private static Dealer dealer;
    protected static Deck deck;

    public static void playerIntro(){
        deck = new Deck();
        System.out.println("What's your name?");
        Scanner name = new Scanner(System.in);
        setName(name.nextLine());
        System.out.println("Welcome to Blackjack, " + getName() + "!");
        System.out.println("How many dollars do you have?");
        Scanner money = new Scanner(System.in);
        setMoney(money.nextInt());
        System.out.println("You have " +getMoney()+ " dollars.");
        playerStart();
    }

    public static void playerStart(){
        takeBet();
        shuffle();
        hand = new Hand(deck);
        dealer = new Dealer();
        System.out.println("Your hand has: " +hand.handName());
        if(checkBlackjack(hand)){
            System.out.println("You got a blackjack! Let's check if the dealer does too...");
            dealer.dealerTurn();
        }
        HitOrStand();
    }

    public static void takeBet(){
        System.out.println("How many dollars do you want to bet? You currently have "+money+" dollars.");
        Scanner betAmount = new Scanner(System.in);
        bet = betAmount.nextInt();
        while(bet>money){
            System.out.println("Your bet must be less than your money, which is "+money+" dollars.");
            bet = betAmount.nextInt();
        }
        while(bet<=0){
            System.out.println("Your bet must be greater than zero dollars.");
            bet = betAmount.nextInt();
            while(bet>money){
                System.out.println("Your bet must be less than your money, which is "+money+" dollars.");
                bet = betAmount.nextInt();
            }
        }
        System.out.println("You bet "+bet+" dollars.");
    }

    public static void setMoney(int money) {
        Player.money = money;
    }
    public static void setName(String name) {
        Player.name = name;
    }

    public static Hand getHand(){
        return hand;
    }

    public static String getName() {
        return name;
    }

    public static int getMoney() {
        return money;
    }

    public static void HitOrStand(){
        System.out.println("Will you hit or stand?");
        Scanner hitScan = new Scanner(System.in);
        String hitStand = hitScan.nextLine().toLowerCase();
        while(!inputCorrect(hitStand, "hit", "stand")){
            System.out.println("You must input either 'hit' or 'stand'.");
            hitStand = hitScan.nextLine().toLowerCase();
        }
        while(hitStand.equals("hit")){
            hand.hit();
            System.out.println("Your hand has: " + getHand().handName());
            if(checkBust(hand)){
                System.out.println("You busted!");
                lose();
                break;
            }
            System.out.println("Will you hit or stand?");
            hitStand = hitScan.nextLine();
            while(!inputCorrect(hitStand, "hit", "stand")){
                System.out.println("You must input either 'hit' or 'stand'.");
                hitStand = hitScan.nextLine().toLowerCase();
            }
        }
        if(hitStand.equals("stand")){
            dealer.dealerTurn();
        }

    }

    public static boolean inputCorrect(String input, String target, String target2){
        if(input.equals(target)||input.equals(target2)){
            return true;
        }
        return false;
    }

    public static boolean checkBust(Hand bustHand){
        if(bustHand.handValue()>21){
            return true;
        }
        return false;
    }

    public static boolean checkBlackjack(Hand hand){
        if(hand.handValue()==21 && hand.handSize()==2){
            return true;
        }
        return false;
    }

    protected static void blackjackWin(){
        bet+=(bet/2);
        win();
    }

    private static void shuffle(){
        if(deck.deckSize()==52 || deck.deckSize()<40) {
            deck = new Deck();
            deck.shuffle();
        }
    }

    protected static void lose(){
        money-=bet;
        System.out.println("You lost " + bet+" dollars. You now have " +money+ " dollars.");
        playAgain();
    }
    protected static void win(){
        money+=bet;
        System.out.println("You won " + bet+" dollars. You now have " +money+ " dollars.");
        playAgain();
    }
    protected static void draw(){
        System.out.println("Draw! You still have "+money+" dollars.");
        playAgain();
    }
    protected static void playAgain(){
        if(money<=0){
            System.out.println("You have no more money!");
            return;
        }
            System.out.println("Play again? Y/N");
            Scanner play = new Scanner(System.in);
            String yn = play.nextLine().toLowerCase();
            while (!inputCorrect(yn, "y", "n")) {
                System.out.println("You must input either 'Y' or 'N'.");
                yn = play.nextLine().toLowerCase();
            }
            if (yn.equals("y")) {
                playerStart();
            }
            if (yn.equals("n")) {
                System.out.println("You ended with " + money + " dollars.");
                return;
            }
    }
}
