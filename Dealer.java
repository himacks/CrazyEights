/**
 * Maxim Karpinsky
 * ID: 2405869
 * email: karpinsky@chapman.edu
 * Course: CPSC 231
 * Assignment: Programming Mastery Project 3-A: Let’s Play Cards!
 */

import java.util.LinkedList;
import java.util.List;


/**
 * Dealer class which enacts a deck being created with a dealer.
 */
public class Dealer
{
  private Deck m_deck;

  /**
   * Default constructor for the Dealer which creates a new random deck for the
   * dealer.
   */
  public Dealer()
  {
    m_deck = new Deck();
  }

  /**
   * Deals n amount of cards which removes them randomly from the deck and returns
   * them in a linked list.
   * @param  n  the number of cards to remove from the deck.
   * @return   a linked list containing all of the cards removed.
   */
  public LinkedList<Card> deals(int n)
  {

    LinkedList<Card> dealedCards = new LinkedList<Card>();

    // If the deck is not empty, it goes to deal the cards, if the deck is
    // empty the linked list is just returned empty.
    if (m_deck.size() != 0)
    {
      for(int i = 0; i < n; i++)
      {
        dealedCards.add(m_deck.deal());
      }
    }

    return dealedCards;
  }

  /**
   * Size method which returns the size of the deck, eg. how many cards in the
   * deck.
   * @return numerical value of the amount of cards in the deck.
   */
  public int size()
  {
    return m_deck.size();
  }

  /**
   * String representation of the deck which returns every card in the deck in
   * textual format.
   * @return string representation of the dealer's deck.
   */
  public String toString()
  {
    return m_deck.toString();
  }
}
