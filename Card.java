/**
 * Maxim Karpinsky
 * ID: 2405869
 * email: karpinsky@chapman.edu
 * Course: CPSC 231
 * Assignment: Programming Mastery Project 3-A: Let’s Play Cards!
 */

import java.util.Random;
import java.util.HashMap;

/**
 * Card class serves for creating individual cards with their properties like
 * suit and card value along with a string representation and comparison to
 * other cards.
 */
public class Card{

  private HashMap<Integer, String> suits;
  private HashMap<Integer, String> values;

  private int cardSuit;
  private int cardValue;

  /**
   * Creates the bounds for the card suit and value range so when a card is
   * generated or inputted it aligns with the matches in here.
   */
  private void createCardBounds()
  {
    suits = new HashMap<Integer, String>();
    values = new HashMap<Integer, String>();

    suits.put(0, "Hearts");
    suits.put(1, "Spades");
    suits.put(2, "Clubs");
    suits.put(3, "Diamonds");

    for(int i = 2; i <= 10; i++)
    {
        values.put(i, "" + i);
    }
    values.put(11, "Jack");
    values.put(12, "Queen");
    values.put(13, "King");
    values.put(14, "Ace");
  }

  /**
   * Default constructor for the card class generates a random card.
   */
  public Card()
  {
    createCardBounds();

    Random rand = new Random();
    cardSuit = rand.nextInt(4);
    cardValue = rand.nextInt(13) + 2;
  }

  /**
   * Overloaded constructor for card class with inputs to create a specific card.
   * @param suit   the suit of the card
   * @param value  the card value 2-10 or Ace, Jack, Queen, King (numerically represented)
   */
  public Card(int suit, int value)
  {
    createCardBounds();

    cardSuit = suit;
    cardValue = value;
  }

  /**
   * Copy constructor for card class
   * @param otherCard  the other card object to copy from
   */
  public Card(Card otherCard)
  {
    createCardBounds();

    cardSuit = otherCard.getSuit();
    cardValue = otherCard.getCardValue();
  }

  /**
   * Setter method for the suit of the card
   * @param suit  numerical value of the suit to set to
   */
  public void setSuit(int suit)
  {
    cardSuit = suit;
  }

  /**
   * Get method for the suit of the card
   * @return the suit of the card in its numerical representation.
   */
  public int getSuit()
  {
    return cardSuit;
  }

  public String getStringSuit(int num)
  {
    return suits.get(num);
  }

  public String getStringCardValue(int num)
  {
    return values.get(num);
  }

  /**
   * setter method for the value of the card
   * @param value  the numerical value of the card to be set to
   */
  public void setCardValue(int value)
  {
    cardValue = value;
  }
  /**
   * getter method for the value of the card
   * @return the numerical representation of the value of the card
   */
  public int getCardValue()
  {
    return cardValue;
  }

  /**
   * String representation of the card stating its suit and value
   * @return the string format of the card so its readable
   */
  public String toString()
  {
    return(values.get(getCardValue()) + " of " + suits.get(getSuit()));
  }

  /**
   * Comparison method to check if card is equal to another card based on if
   * their suits and values are the same.
   * @param  otherCard the other card object to compare to
   * @return true if the cards are equal or false if cards are not equal.
   */
  public boolean equals(Card otherCard)
  {
    return(getSuit() == otherCard.getSuit() &&
           getCardValue() == otherCard.getCardValue());
  }

}
