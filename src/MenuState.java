import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

public class MenuState extends State 
{
	private Button startButton;
	private Button rulesButton;
	private String title;
	
	private Transitions transitionManager;
	
	private boolean changeState;
	
	public MenuState()
	{
		title = "PIG DICE GAME";
		
		//Create buttons
		createButtons();

		transitionManager = new Transitions();
		changeState = false;
	}
	
	private void createButtons()
	{
		//Add startButton
		startButton = new Button("Start", "images/startButton1.png", "images/startButton2.png");
		startButton.setX((GamePanel.WIDTH / 2) - (startButton.getWidth() / 2));
		startButton.setY((GamePanel.HEIGHT / 2) - (startButton.getHeight()));
				
		//Add rulesButton
		rulesButton = new Button("Rules", "images/rulesButton1.png", "images/rulesButton2.png");
		rulesButton.setX((GamePanel.WIDTH / 2) - (rulesButton.getWidth() / 2));
		rulesButton.setY(startButton.getY() + startButton.getHeight() + 25);
	}
	
	public void update()
	{
		if(changeState == false)
		{
			startButton.update();
			rulesButton.update();
			
			//Check if its time to do transition animation
			transition();
		}
		else
		{
			//Update transition animation
			transitionManager.update();
			
			//If transition is finished
			if(transitionManager.isDone() == true)
			{
				//Change the state
				changeState();
			}
		}
	}
	
	public void draw(Graphics2D g)
	{   
		//Draw background
		drawBackground(g);
		
        //Draw title
		drawTitle(g);
        
       //Draw buttons
		drawButtons(g);
        
        if(changeState == true)
        {
        	//Draw transition
        	transitionManager.draw(g);
        }
	}
	
	private void drawBackground(Graphics2D g)
	{
		//Draw background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
	}
	
	private void drawTitle(Graphics2D g)
	{
		//Draw title
        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier New", Font.BOLD, 64));
        int titleLength = (int) g.getFontMetrics().getStringBounds(title, g).getWidth();
        g.drawString(title, (GamePanel.WIDTH / 2) - (titleLength / 2), 100);
	}
	
	private void drawButtons(Graphics2D g)
	{
		//Draw buttons
        startButton.draw(g);
        rulesButton.draw(g);
	}
    
    private void transition()
    {
    	if(startButton.isMouseClickingButton() == true)
    	{
    		startButton.setDisabled(true);
    		startButton.setMouseTouchingButton(false);
    		
    		rulesButton.setDisabled(true);
    		changeState = true;
    	}
    	else if(rulesButton.isMouseClickingButton() == true)
    	{
    		startButton.setDisabled(true);
    		
    		rulesButton.setDisabled(true);
    		rulesButton.setMouseTouchingButton(false);
    		changeState = true;
    	}
    }
    
    private void changeState()
    {
        if(startButton.isMouseClickingButton() == true)
        {
            startButton.setMouseClickingButton(false);
            startButton.setMouseTouchingButton(false);
            
            //Go to PlayState
            StateManager.instance().changeState(1);
        }
        else if(rulesButton.isMouseClickingButton() == true)
        {
        	rulesButton.setMouseClickingButton(false);
        	rulesButton.setMouseTouchingButton(false);
        	
        	//Go to RulesState
        	StateManager.instance().changeState(2);
        }
    }
}
