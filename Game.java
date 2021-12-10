import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Game
{
  private Player p1, p2;
  private Dealer dealer;
  private LinkedList<Card> starterPile;

  private Player winner;
  private Player loser;
  private int p1Score;
  private int p2Score;
  private int num8Played;
  private int cardsHeldLoser;
  private int stockSize;
  private boolean drawGame = false;

  public Game()
  {
    dealer = new Dealer();
    starterPile = new LinkedList<Card>();

    starterPile.addAll(dealer.deals(1));

    p1 = new Player(1, dealer, starterPile);
    p2 = new Player(2, dealer, starterPile);

    winner = null;
    p1Score = 0;
    p2Score = 0;
    num8Played = 0;
    cardsHeldLoser = 0;
    stockSize = getStockSize();
  }

  public int getStockSize()
  {
    return dealer.size();
  }

  public void play()
  {
    Player currentPlayer = p1;
    Player otherPlayer = p2;
    boolean gameFinished = false;
    int playsCantBePlayed = 0;
    while(!gameFinished)
    {
      Card topCard = starterPile.getLast();
      Card referenceCard = new Card();
      int topCardSuit = topCard.getSuit();

      if(playsCantBePlayed >= 2)
      {
        break;
      }

      System.out.println("Top Card on Pile : " + topCard.toString());
      System.out.println("Deck Size: " + dealer.size());
      System.out.println();
      System.out.println("Player " + currentPlayer.getPlayerNum() + " Turn");
      System.out.println();

      if(topCard.getCardValue() == 8)
      {
        topCardSuit = currentPlayer.newSuit();
        num8Played++;
        System.out.println("An Eight Card was played, chosen suit : " + referenceCard.getStringSuit(topCardSuit));
        System.out.println();
      }

      Card playedCard = currentPlayer.takeTurn(topCardSuit);

      while(playedCard == null && dealer.size() != 0 && currentPlayer.size() != 0)
      {
        //need to catch when the dealer deck is empty, or if the player deck is empty
        System.out.println("Can't Play, Drawing Card");
        System.out.println();
        currentPlayer.drawCard();
        playedCard = currentPlayer.takeTurn(topCardSuit);
      }

      if(playedCard != null)
      {
        starterPile.add(playedCard);
        playsCantBePlayed = 0;
        System.out.println("Played " + playedCard.toString());
        System.out.println();
      }
      else if(dealer.size() == 0)
      {

        System.out.println("Dealer Deck is Empty!");
        System.out.println();
        if(playsCantBePlayed < 2)
        {
          playsCantBePlayed++;
          System.out.println("Player " + currentPlayer.getPlayerNum() + " Can't Play. Passing Turn.");
          System.out.println();
        }
        else
        {
          gameFinished = true;
          break;
        }
      }

      if(currentPlayer.size() == 0)
      {

        gameFinished = true;
        winner = currentPlayer;
        break;
      }

      if(currentPlayer.equals(p1))
      {
        currentPlayer = p2;
        otherPlayer = p1;
      }
      else
      {
        currentPlayer = p1;
        otherPlayer = p2;
      }
    }

    if(currentPlayer.size() < otherPlayer.size())
    {
      winner = currentPlayer;
    }
    else if (currentPlayer.size() > otherPlayer.size())
    {
      winner = otherPlayer;
      otherPlayer = currentPlayer;
    }
    else
    {
      drawGame = true;
    }

    if(winner != null)
    {
      System.out.println("-----------------");
      System.out.println("| Player " + winner.getPlayerNum() + " Won! |");
      System.out.println("-----------------");
      System.out.println();

      if(winner.getPlayerNum() == 1)
      {
        p1Score += otherPlayer.getHandScore();
        winner = p1;
        loser = p2;
      }
      else
      {
        p2Score += otherPlayer.getHandScore();
        winner = p2;
        loser = p1;
      }
    }
    else
    {
      System.out.println("-------------");
      System.out.println("| Draw Game |");
      System.out.println("-------------");
      System.out.println();

    }
  }

  public Player getWinner()
  {
    return winner;
  }

  public boolean isDraw()
  {
    return drawGame;
  }

  public Player getLoser()
  {
    return loser;
  }

  public int getP1Score()
  {
    return p1Score;
  }

  public int getP2Score()
  {
    return p2Score;
  }

  public int get8Played()
  {
    return num8Played;
  }

}
