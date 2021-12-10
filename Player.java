import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Player
{
  private int playerNum;
  private LinkedList<Card> ownedCards;
  private LinkedList<Card> starterPileRef;
  private Dealer dealerRef;
  private Random rand;


  public Player(int num, Dealer dealer, LinkedList<Card> startpile)
  {
    playerNum = num;
    ownedCards = new LinkedList<Card>();
    starterPileRef = startpile;
    dealerRef = dealer;
    rand = new Random();

    ownedCards.addAll(dealerRef.deals(5));

  }

  public int getPlayerNum()
  {
    return playerNum;
  }

  public boolean equals(Player otherP)
  {
    return this.getPlayerNum() == otherP.getPlayerNum();
  }

  public void drawCard()
  {
    ownedCards.addAll(dealerRef.deals(1));
  }

  public int size()
  {
    return ownedCards.size();
  }

  public Card takeTurn(int topCardSuit)
  {

    int topCardValue = starterPileRef.getLast().getCardValue();

    int eightCardIndex = -1;
    int cardToPlayIndex = -1;
    Card playedCard = null;

    printHand();

    for(int i = 0; i < ownedCards.size(); i++)
    {
      Card currentOwnedCard = ownedCards.get(i);
      if(currentOwnedCard.getCardValue() == topCardValue ||
         currentOwnedCard.getSuit() == topCardSuit && currentOwnedCard.getCardValue() != 8)
      {
        cardToPlayIndex = i;
      }
      else if(currentOwnedCard.getCardValue() == 8)
      {
        eightCardIndex = i;
      }
    }

    if(cardToPlayIndex != -1 && eightCardIndex == -1)
    {
      return ownedCards.remove(cardToPlayIndex);
    }
    else if(cardToPlayIndex != -1 && eightCardIndex != -1)
    {
      if(rand.nextInt(10) == 0)
      {
        playedCard = ownedCards.remove(eightCardIndex);
      }
      else
      {
        playedCard = ownedCards.remove(cardToPlayIndex);
      }
    }
    else if(cardToPlayIndex == -1 && eightCardIndex != -1)
    {
      playedCard = ownedCards.remove(eightCardIndex);
    }

    return playedCard;
  }

  public void printHand()
  {
    System.out.println("-----Player " + getPlayerNum() + " Hand-----");
    for(Card card : ownedCards)
    {
      System.out.println(card.toString());
    }
    System.out.println("-----------------------");
    System.out.println();
  }


  public int newSuit()
  {
    return rand.nextInt(4);
  }

  public int getHandScore()
  {
    int score = 0;

    for(Card card : ownedCards)
    {
      if(card.getCardValue() >= 11 && card.getCardValue() <= 13)
      {
        score += 10;
      }
      else if(card.getCardValue() == 8)
      {
        score += 50;
      }
      else if(card.getCardValue() == 14)
      {
        score += 1;
      }
    }

    return score;
  }


}
