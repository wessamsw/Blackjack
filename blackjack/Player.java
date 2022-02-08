package blackjack;

public class Player {
    private String name;
    int score=0 , myHand=0;
    Card[] cards=new Card[11];
    boolean lost;
    public Player(String name) {
        this.name = name;
        lost = false;
    }

}
