/**
 * Maxim Karpinsky
 * ID: 2405869
 * email: karpinsky@chapman.edu
 * Course: CPSC 231
 * Assignment: Programming Mastery Project 3-B: Crazy-8's
 */

/**
 * Simulator class simulates n amount of games and generates statistics for
 * the games ran.
 */
public class Simulation
{

  private int gamesWonP1;
  private int gamesWonP2;
  private int pointsCollectedP1;
  private int pointsCollectedP2;
  private int total8Played;
  private int totalLoserCards;
  private int numDraws;
  private double avgCardsHeldLoser;
  private double avgTimes8Played;
  private int timesStockOut;
  private Game GameInstance;


  /**
   * Simulates numGames amount of the games of Crazy 8 and adds to the
   * aggregated statistics.
   * @param numGames number of Crazy 8 games to simulate.
   */
  public void simulate(int numGames)
  {
    for(int i = 0; i < numGames; i++)
    {
      GameInstance = new Game();
      GameInstance.play();
      calculate(GameInstance);
    }
    calculate(numGames);
  }

  /**
   * Statistics to be calculated on top of each game, eg adding to the running
   * total points a player has, total number of 8 cards played, and more.
   * @param game  the current game instance to retreive statistics from.
   */
  public void calculate(Game game)
  {
    pointsCollectedP1 += game.getP1Score();
    pointsCollectedP2 += game.getP2Score();
    total8Played += game.get8Played();
    if(!game.isDraw())
    {
      totalLoserCards += game.getLoser().size();
    }
    else
    {
      numDraws++;
    }

    if (game.getStockSize() == 0)
    {
      timesStockOut++;
    }

    if (!game.isDraw() && game.getWinner().getPlayerNum() == 1)
    {
      gamesWonP1++;
    }
    else if (!game.isDraw() && game.getWinner().getPlayerNum() == 2)
    {
      gamesWonP2++;
    }
  }

  /**
   * Statistics to be calculated after all of the games have been run, this is
   * for the aggregated statistics like averages which can only be done when
   * the simulations are finished.
   * @param gamesPlayed the number of games ran in the simulation.
   */
  public void calculate(int gamesPlayed)
  {
    avgTimes8Played = total8Played / (gamesPlayed * 1.0);
    avgCardsHeldLoser = totalLoserCards / (gamesPlayed * 1.0);
  }

  /**
   * String method to return a cleanly formatted view of the statistics from the
   * simulation, including player's running score, amount of draws, amount of
   * wins per player, and numerous averages.
   */
  public void report()
  {
    System.out.println("Average Number of 8 Cards Played Per Game: " + String.format("%.2f", avgTimes8Played));
    System.out.println("Average Number of Cards Held By Loser at Finish Per Game: " + String.format("%.2f", avgCardsHeldLoser));
    System.out.println("Number of Draws: " + numDraws);
    System.out.println("Number of Games Won by P1: " + gamesWonP1);
    System.out.println("Number of Games Won by P2: " + gamesWonP2);
    System.out.println("Number of Times the Dealer Stock Became Empty: " + timesStockOut);
    System.out.println("Points Collected by P1: " +   pointsCollectedP1);
    System.out.println("Points Collected by P2: " +   pointsCollectedP2);
  }


  /**
   * Main method to run the simulation and start the script.
   * @param args  [description]
   */
  public static void main(String[] args)
  {
    Simulation gameSimulator = new Simulation();

    gameSimulator.simulate(100);
    gameSimulator.report();
  }
}
