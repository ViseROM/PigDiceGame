import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.*;

public class PlayState extends State
{
	private Context context;
	
	//Dice images
	private DiceSprite die1;
	private DiceSprite die2;
	
	//Button images
	private Button menuButton;
	private Button newGameButton;
	private Button rollButton;
	private Button stopButton;
	
	//String to be displayed
	private String player1;
	private String player2;
	private String player1Score;
	private String player2Score;
	private String turnScore;
	
	private int[] dotLocation;
	private int dotIndex;
	private static final int NUM_LOCATIONS = 2;
	
	//Flag for game over
	private boolean gameOver;
	
	public PlayState()
	{
		context = Context.instance();
		context.newGame();
		
		//Create Buttons
		createButtons();
		
		//Create DiceSprites
        die1 = new DiceSprite("Die1");
        die2 = new DiceSprite("Die2");
        for(int i = 0; i < 6; i++)
        {
            String dieImage = "images/dice" + (i+1) + ".png";
            die1.addImage(dieImage);
            die2.addImage(dieImage);
        }
        
        die1.setCurrentImage(0);
        die2.setCurrentImage(1);
        
        die1.setX(((GamePanel.WIDTH / 2) - (die1.getWidth() / 2)) - 75);
        die1.setY((GamePanel.HEIGHT / 2) - die1.getHeight());
        
        die2.setX(((GamePanel.WIDTH / 2) - (die1.getWidth() / 2)) + 75);
        die2.setY((GamePanel.HEIGHT / 2) - die2.getHeight());
        
        player1 = "Player 1";
        player2 = "Player 2";
        turnScore = "Current";
        
        dotLocation = new int[] {400, 950};
        dotIndex = 0;
        
        gameOver = false;

	}
	
	private void createButtons()
	{
		//Create menuButton
		menuButton = new Button("Menu", "images/menuButton3.png", "images/menuButton4.png");
		menuButton.setX(0);
		menuButton.setY(0);
				
		//Create newGameButton
		newGameButton = new Button("New Game", "images/newGameButton1.png", "images/newGameButton2.png");
		newGameButton.setX(GamePanel.WIDTH - newGameButton.getWidth());
		newGameButton.setY(0);
				
		//Create rollButton
		rollButton = new Button("Roll", "images/rollButton1.png", "images/rollButton2.png");
		rollButton.setX((GamePanel.WIDTH / 2) - (rollButton.getWidth() / 2));
		rollButton.setY(GamePanel.HEIGHT - (rollButton.getHeight() + 100));
		        
		//Create stopButton
		stopButton = new Button("Stop", "images/stopButton1.png", "images/stopButton2.png");
		stopButton.setX((GamePanel.WIDTH / 2) - (stopButton.getWidth() / 2));
		stopButton.setY(GamePanel.HEIGHT - (stopButton.getHeight() + 25));
	}
	
	public void update()
	{	
		//Button updates
		updateButtons();
		
		//Perform actions if mouse clicked a button
        if(rollButton.isMouseClickingButton() == true)	//Mouse clicking rollButton
        {
        	//Disable buttons
        	rollButton.setDisabled(true);
        	stopButton.setDisabled(true);
        	rollButton.setMouseClickingButton(false);
        	
        	die1.setRolling(true);
        	die2.setRolling(true);
        	
        	die1.setY(0 - die1.getHeight());
            die2.setY(0 - die2.getHeight());
        	
        }
        else if(stopButton.isMouseClickingButton() == true)	//Mouse clicking stopButton
        {
        	stopButton.setMouseClickingButton(false);
        	
        	//Tell context that player is done rolling
        	context.doneRolling();
        	
        	//Check if game is over (Player scored enough points)
        	if(context.isGameOver() == true)
        	{	
        		//Set gameOver flag to true
            	gameOver = true;
        	}
        	else
        	{
        		//Tell context to go to next player
            	context.nextPlayer();
            	
            	++dotIndex;
            	if(dotIndex >= NUM_LOCATIONS)
            	{
            		dotIndex = 0;
            	}
        	}
        	
        }
        else if(menuButton.isMouseClickingButton() == true)	//Mouse clicking menuButton
        {
        	menuButton.setMouseClickingButton(false);
        	
        	//Go to MenuState
        	StateManager.instance().changeState(0);
        }
        else if(newGameButton.isMouseClickingButton() == true)	//Mouse clicking newGameButton
        {
        	newGameButton.setMouseClickingButton(false);
        	
        	//Create a new game
        	StateManager.instance().changeState(1);
        }
        
        //Rolling animation
        if(die1.isRolling() == true && die2.isRolling() == true)
        {
        	//Update die images
        	die1.update();
        	die2.update();
        	
        	if(die1.isRolling() == false && die2.isRolling() == false)
            {
        		//Roll dices
                context.roll();
                
                int die1Image = (context.getDie1Value() - 1);
                int die2Image = (context.getDie2Value() - 1);
                die1.setCurrentImage(die1Image);
                die2.setCurrentImage(die2Image);
                
                //Check if a pig was rolled
                int value = context.evaluateRoll();
                
                //If a pig was rolled
                if(value == 1 || value == 2)
                {
                	//Go to next player
                	context.nextPlayer();
                	
                	++dotIndex;
                	if(dotIndex >= NUM_LOCATIONS)
                	{
                		dotIndex = 0;
                	}
                }
                
                //Enable buttons
                rollButton.setDisabled(false);
                stopButton.setDisabled(false);
            }
        }
        
        if(gameOver == true)
        {
        	//Disable RollButton and StopButton
    		rollButton.setDisabled(true);
    		stopButton.setDisabled(true);
        }
	}
	
	public void draw(Graphics2D g)
	{
		//Draw background
        drawBackground(g);
        
        g.setColor(Color.RED);
        g.fillOval(dotLocation[dotIndex], ((GamePanel.HEIGHT / 6) - 20), 20, 20);
        
        int textLength;
        g.setColor(Color.BLACK);
        g.setFont(new Font ("Courier New", Font.BOLD, 38));
        
        //Draw Player 1 Name and score
        textLength = getTextLength(player1, g);
        g.drawString(player1, (GamePanel.WIDTH / 4) - (textLength / 2), GamePanel.HEIGHT / 6);
        player1Score = String.valueOf(context.getPlayer1Score());
        textLength = getTextLength(player1Score, g);
        g.drawString(player1Score, (GamePanel.WIDTH / 4) - (textLength / 2), ((GamePanel.HEIGHT / 6) + 50));
        
        //Draw Player 2 Name and score
        textLength = getTextLength(player2, g);
        g.drawString(player2, (GamePanel.WIDTH - (GamePanel.WIDTH / 4)) - (textLength / 2), GamePanel.HEIGHT / 6);
        player2Score = String.valueOf(context.getPlayer2Score());
        textLength = getTextLength(player2Score, g);
        g.drawString(player2Score, (GamePanel.WIDTH - (GamePanel.WIDTH / 4)) - (textLength / 2), ((GamePanel.HEIGHT / 6) + 50));
        
        g.setFont(new Font ("Courier New", Font.BOLD, 24));
        //Draw Turn Score
        textLength = getTextLength(turnScore, g);
        g.drawString(turnScore, ((GamePanel.WIDTH / 2) - (textLength / 2)), ((GamePanel.HEIGHT / 2) + 75));
        textLength = getTextLength(String.valueOf(context.getTurnScore()), g);
        g.drawString(String.valueOf(context.getTurnScore()), ((GamePanel.WIDTH / 2) - (textLength / 2)), ((GamePanel.HEIGHT / 2) + 125));
        
        
        //Draw dice
        drawDice(g);

        //Draw buttons
        drawButtons(g);
        
        //If the game is over
        if(gameOver == true)
        {
        	//Display the winner
        	displayWinner(g);
        }
	}
	
	private void updateButtons()
	{
		stopButton.update();
		rollButton.update();
		menuButton.update();
		newGameButton.update();
	}
	
	private void drawBackground(Graphics2D g)
	{
		if(dotIndex == 0)
        {
            g.setColor(new Color(240, 240, 240));
            g.fillRect(0, 0, (GamePanel.WIDTH / 2), GamePanel.HEIGHT);
            g.setColor(new Color(250, 250, 250));
            g.fillRect((GamePanel.WIDTH / 2), 0, (GamePanel.WIDTH / 2), GamePanel.HEIGHT);
        }
        else
        {
            g.setColor(new Color(250, 250, 250));
            g.fillRect(0, 0, (GamePanel.WIDTH / 2), GamePanel.HEIGHT);
            g.setColor(new Color(240, 240, 240));
            g.fillRect((GamePanel.WIDTH / 2), 0, (GamePanel.WIDTH / 2), GamePanel.HEIGHT);
        }
	}
	
	private void drawDice(Graphics2D g)
	{
		die1.draw(g);
        die2.draw(g);
	}
	
	private void drawButtons(Graphics2D g)
	{
		menuButton.draw(g);
        newGameButton.draw(g);
        rollButton.draw(g);
        stopButton.draw(g);
	}
	
	private void displayWinner(Graphics2D g)
	{
		 if(context.getWinner() != null)
	     {
			 //Draw winner text
			 g.setColor(Color.RED);
			 g.setFont(new Font("Courier New", Font.BOLD, 36));
			 int textLength = getTextLength("WINNER!", g);
	        	
			 if(context.getWinner().getName().equals("1") == true)
			 {
				 g.drawString("WINNER!", (GamePanel.WIDTH / 4) - (textLength / 2), GamePanel.HEIGHT / 3);
			 }
			 else if(context.getWinner().getName().equals("2") == true)
			 {
				 g.drawString("WINNER!", (GamePanel.WIDTH - (GamePanel.WIDTH / 4)) - (textLength / 2), GamePanel.HEIGHT / 3);
			 }
	     }
	}
	
	private int getTextLength(String text, Graphics2D g)
    {
        int length = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
        
        return length;
    }
}
