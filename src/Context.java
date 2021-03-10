
public class Context {
	private static Context context;
	private Game game;
	
	private Context()
	{
		game = new Game();
	}
	
	public static Context instance()
	{
		if(context == null)
		{
			context = new Context();
		}
		
		return context;
	}
	
	public void roll() {game.roll();}
	public int evaluateRoll() {return game.evaluateRoll();}
	public void doneRolling() {game.doneRolling();}
	public void nextPlayer() {game.nextPlayer();}
	public int getDie1Value(){return (game.getDie1().getValue());}
    public int getDie2Value(){return (game.getDie2().getValue());}
    public int getPlayer1Score(){return (game.getPlayer1().getScore());}
    public int getPlayer2Score(){return (game.getPlayer2().getScore());}
    public String getCurrentPlayerName(){return (game.getCurrentPlayer().getName());}
    public int getCurrentPlayerScore(){return (game.getCurrentPlayer().getScore());}
    public int getTurnScore(){return game.getTurnScore();}
    public boolean isGameOver() {return game.isGameOver();}
    public Player getWinner() {return game.getWinner();}
    public void newGame() {game = new Game();}

}
