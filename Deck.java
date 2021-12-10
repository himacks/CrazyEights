/**
 * Maxim Karpinsky
 * ID: 2405869
 * email: karpinsky@chapman.edu
 * Course: CPSC 231
 * Assignment: Programming Mastery Project 3-A: Let’s Play Cards!
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.lang.IllegalArgumentException;

/**
 * Deck class which acts as a deck of cards that can be read and able to deal
 * amount of cards out of deck.
 */
public class Deck
{

  private LinkedList<Card> m_cards;

  /**
   * Default constructor which generates a deck of 52 card objects in a linked list.
   */
  public Deck()
  {
    m_cards = new LinkedList<Card>();

    for (int i = 0; i <= 3; i++)
    {
      for (int j = 2; j <= 14; j++)
      {
        m_cards.add(new Card(i, j));
      }
    }
  }

  /**
   * Get Method which returns the linked list of the cards in the deck.
   * @return [description]
   */
  public LinkedList<Card> getCards()
  {
    return m_cards;
  }

  /**
   * Copy Constructor which creates a deep copy of the inputted deck
   * @param otherDeck  the other deck object to be copied from
   */
  public Deck(Deck otherDeck)
  {
    m_cards = new LinkedList<Card>();

    LinkedList<Card> otherDeckCards = otherDeck.getCards();

    for (int i = 0; i < otherDeckCards.size(); i++)
    {
      Card cardToDuplicate = otherDeckCards.get(i);
      m_cards.add(new Card(cardToDuplicate.getSuit(),
                           cardToDuplicate.getCardValue()));
    }
  }

  /**
   * Size method to find out the size of the deck eg. how many cards are in the deck.
   * @return numeric value of the cards currently in the deck
   */
  public int size()
  {
    return m_cards.size();
  }

  /**
   * Deal method which picks a random card out of the deck, removes it, and returns
   * the deck.
   * @return the removed card object
   * @throws IllegalArgumentException when deck is empty and this method is called,
   * self explanatory but you can't draw a card when the deck is empty.
   */
  public Card deal()
  {
    Random rand = new Random();
    try
    {
      return m_cards.remove(rand.nextInt(m_cards.size()));
    }
    catch(Exception e)
    {
      throw new IllegalArgumentException("Can't deal card because deck is empty.");
    }
  }

  /**
   * String representation of the deck displaying each card inside the deck.
   * @return string representation of deck.
   */
  public String toString()
  {
    String deckCards = "";

    for (int i = 0; i < m_cards.size(); i++)
    {
      deckCards += m_cards.get(i).toString() + "\n";
    }

    return deckCards;
  }
}
