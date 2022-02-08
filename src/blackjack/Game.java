package blackjack;
import java.util.*;

public class Game {

    Player[] players = new Player[4];
    private Card[] Deck = new Card[52];
    private Scanner names = new Scanner(System.in);
    private Random r = new Random();
    private int highcore;

    public int getHighcore() {
        return highcore;
    }

    public Card[] getDeck() {
        return Deck;
    }

    public void setCard() {
        int counter = 0;
        boolean ten = false;
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                if ((i % 10) + 1 == 10) ten = true;
                if (ten)
                    this.Deck[counter] = new Card(j, i, 10);
                else
                    this.Deck[counter] = new Card(j, i, (i % 10) + 1);
                counter++;
            }
        }
    }

    public Card drawCard() {
        int card = r.nextInt(52);
        while (true) {
            if (this.Deck[card] != null) {
                Card c = new Card(this.Deck[card]);
                this.Deck[card] = null;
                return c;
            } else
                card = r.nextInt(52);
        }
    }

    public void Enterplayers() {
        for (int i = 0; i < 4; i++) {
            if(i !=3) {
                System.out.println("Enter Player " + (i + 1) + " Name:");
                this.players[i] = new Player(names.next());
            }
            else
                this.players[i] = new Player("dealer");
            this.players[i].cards[0] = new Card(drawCard());
            this.players[i].cards[1] = new Card(drawCard());
            this.players[i].myHand += 2;
            updateScore(i);
        }
    }

    public void playersMax() {
        highcore=0;
        for (int i = 0; i < 3; i++) {
            if (players[i].score > highcore) {
                highcore = players[i].score;
            }
        }
    }

    public void updateScore(int index) {
        players[index].score = 0;
        for (int i = 0; i < players[index].myHand; i++)
        {
            players[index].score+=players[index].cards[i].getValue();
        }
    }
}
