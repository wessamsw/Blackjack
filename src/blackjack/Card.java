package blackjack;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;

public class Card {
    private final int suit;
    private final int rank;
    private final int value;
    private static final String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private static final String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    private int xPosition;
    private int yPosition;

    public Card(Card card) {
        this.suit = card.suit;
        this.rank = card.rank;
        this.value = card.value;
        this.xPosition = card.xPosition;
        this.yPosition = card.yPosition;
    }

    public Card () { //default constructor of the class
        suit = 0;
        rank = 0;
        value = 0;
    }

    //initialising main attributes
    public Card (int s, int r, int v) {
        suit = s;
        rank = r;
        value = v;
    }

    //getters for each attribute
    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }




     public void printCard(Graphics2D g2, boolean dealerTurn, boolean faceDown, int cardNumber,int xpos_start_for_vertical_player, char player_position) throws IOException {
        BufferedImage deckImg = ImageIO.read(new File("![](../../cardSpriteSheet.png)"));
        int imgWidth = 950;
        int imgHeight = 392;

        BufferedImage[][] cardPictures = new BufferedImage[4][13];
        BufferedImage backOfACard = ImageIO.read(new File("![](../../backsideOfACard.jpg)"));

        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 13; r++) {
                cardPictures[c][r] = deckImg.getSubimage(r*imgWidth/13, c*imgHeight/4, imgWidth/13, imgHeight/4);
            }
        }

        if (dealerTurn) {
            yPosition = 220;
            xPosition = 370 - 75*cardNumber;
        }
        else if(player_position=='H') {
            yPosition = 220;
            xPosition = 700 + 75*cardNumber;
        }
        else if(player_position == 'D')
        {
            yPosition = 400;
            xPosition = xpos_start_for_vertical_player + 75*cardNumber;
        }
        else if(player_position == 'U')
        {
            yPosition = 50;
            xPosition = xpos_start_for_vertical_player + 75*cardNumber;
        }
        else if(player_position == 'C')
        {
            yPosition = 520;
            xPosition = 30 + 25*cardNumber;
        }


        if (faceDown)
        {
            g2.drawImage(backOfACard, xPosition, yPosition, null );
        }
        else
        {
            g2.drawImage(cardPictures[suit][rank], xPosition, yPosition, null);
        }

    }
}
