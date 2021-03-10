
public class Game {

	private Player player1;
	private Player player2;
	private Die die1;
	private Die die2;
	
	private RollEvaluator rollEvaluator;
	
	//Current Player rolling
	private Player currentPlayer;
	
	private int turnScore;
	
	//Stores player who wins game
	private Player winner;
	
	//Score to reach in order to win
	private static final int SCORE_TARGET = 100;
	
	//Flag for game over
	private boolean gameOver;
	
	public Game()
	{
		die1 = new Die();
		die2 = new Die();
		rollEvaluator = new RollEvaluator();
		
		player1 = new Player("1");
		player2 = new Player("2");
		currentPlayer = player1;
		
		turnScore = 0;
		
		winner = null;
		gameOver = false;
	}
	
	public Die getDie1() {return die1;}
	public Die getDie2() {return die2;}
	public RollEvaluator getRollEvaluator() {return rollEvaluator;}
	public Player getPlayer1() {return player1;}
	public Player getPlayer2() {return player2;}
	public Player getCurrentPlayer() {return currentPlayer;}
	public int getTurnScore() {return turnScore;}
	public Player getWinner() {return winner;}
	public boolean isGameOver() {return gameOver;}
	
	public void roll()
	{
		//Roll both dice
		die1.roll();
		die2.roll();
		
		//Add dice values to turnScore
		turnScore += die1.getValue() + die2.getValue();
	}
	
	public int evaluateRoll()
	{
		rollEvaluator.setDice(die1, die2);
		
		int pigValue = rollEvaluator.getPigValue();
		
		switch(pigValue)
		{
			case 1:	//Rolled 1 pig
				turnScore = 0;
				return 1;
			case 2: //Rolled 2 pigs
				turnScore = 0;
				currentPlayer.setScore(0);
				return 2;
		}	
		
		//No pig was rolled
		return 0;
	}
	
	public void doneRolling()
	{
		currentPlayer.addToScore(turnScore);
		turnScore = 0;
		
		if(currentPlayer.getScore() >= SCORE_TARGET)
		{
			winner = currentPlayer;
			gameOver = true;
		}
	}
	
	public void nextPlayer()
	{
		if(currentPlayer.equals(player1) == true)
		{
			currentPlayer = player2;
		}
		else if(currentPlayer.equals(player2) == true)
		{
			currentPlayer = player1;
		}
	}
}
