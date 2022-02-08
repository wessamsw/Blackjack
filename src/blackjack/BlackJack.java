package blackjack;

import java.util.Scanner;

public class BlackJack {

    private static Game game = new Game();
    private static Scanner hitorstand = new Scanner(System.in);
    private static int winners=0;
    private static boolean[] players=new boolean[4];
    public static void main(String[] args) {
        game.setCard();
        game.Enterplayers();
        GUI gui = new GUI();
        gui.runGUI(game.getDeck(), game.players[0].cards, game.players[1].cards,
                game.players[2].cards, game.players[3].cards);
        for (int i = 0; i < 4; i++)
                players[i]=false;
        for(int player=0;player<3;player++)
        {
            System.out.println("player "+(player+1)+" turn");
            System.out.println("1 to hit   2 to stand");
            while(hitorstand.nextInt()==1) {
                Card c = new Card(game.drawCard());
                game.players[player].cards[game.players[player].myHand] = c;
                game.players[player].myHand++;
                game.updateScore(player);
                gui.updatePlayerHand(c, player);
                System.out.println("player " + (player + 1) + " " + game.players[player].score);
                if (game.players[player].score > 21) {
                    System.out.println("player " + (player + 1) + " Busted");
                    game.players[player].lost = true;
                    break;
                }
                else if(game.players[player].score== 21) { winners++; players[player]=true; break;}
                System.out.println("1 to hit   2 to stand");
            }

        }
        game.playersMax();
       // System.out.println(game.getHighcore());


        while(game.players[3].score<game.getHighcore())
        {
            Card c = new Card(game.drawCard());
            game.players[3].cards[game.players[3].myHand] = c;
            game.players[3].myHand++;
            game.updateScore(3);
            gui.updateDealerHand(c, game.getDeck());
            if (game.players[3].score > 21) {
                System.out.println("Dealer Busted");
                game.players[3].lost= true;
                break;
            }
            else if(game.players[3].score== 21) { winners++; players[3]=true; break;}
            else if(!game.players[3].lost&& game.players[3].score>game.getHighcore()) {
                System.out.println("Dealer won!");
                //System.out.println(game.players[3].score);
                return;
            }
        }
        if(winners>1)
            System.out.println("PUSH!");
        else if (winners==1)
        {
            for(int i=0;i<4;i++)
            {
                if(players[i]) {
                    System.out.println("player " + (i + 1) + " BlackJacked!");
                    break;
                }
            }
        }
        else
        {
            int close=0;
            int index=0;
            int top = 0 ;
            for(int i=0;i<3;i++)
            {
                if(!game.players[i].lost&&game.players[i].score>top) {
                    close=1;
                    index = i;
                }
                else if (game.players[i].score==top)
                {
                    close++;
                }

            }
            if(close==0)
                System.out.println("no winner!");
            else if(close==1)
                System.out.println("player " + (index+1) + " won");
            else
                System.out.println("PUSH!");
        }
    }
}


