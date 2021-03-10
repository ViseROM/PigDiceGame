import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class RulesState extends State 
{
	
	private String title;
	
	private Button menuButton;
	private Button startButton;
	
	private ArrayList<String> rules;
	
	private Utility utility;
	
	public RulesState()
	{
		title = "RULES";
		
		//Create buttons
		menuButton = new Button("Menu", "images/menuButton1.png", "images/menuButton2.png");
		menuButton.setX(100);
		menuButton.setY(GamePanel.HEIGHT - GamePanel.HEIGHT / 6);
		
		startButton = new Button("Start", "images/startButton1.png", "images/startButton2.png");
		startButton.setX(GamePanel.WIDTH - startButton.getWidth() - 100);
		startButton.setY(GamePanel.HEIGHT - GamePanel.HEIGHT / 6);
		
		loadRules();
		
		utility = new Utility();
	}
	
	private void loadRules()
	{
		rules = new ArrayList<String>();
		
		//Obtain rules file
		try {
			File file = new File("src/files/rules.txt");
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine())
			{
				rules.add(scanner.nextLine());
			}
			
			scanner.close();
			
		}catch(FileNotFoundException e) {
			System.out.println("Error obtaining rules file");
			e.printStackTrace();
		}
	}
	
	public void update()
	{
		//Update buttons
		menuButton.update();
		startButton.update();
		
		//Check if buttons have been clicked
		if(menuButton.isMouseClickingButton() == true)
		{
			menuButton.setMouseClickingButton(false);
			
			//Go to menu state
			StateManager.instance().changeState(0);
		}
		else if(startButton.isMouseClickingButton() == true)
		{
			startButton.setMouseClickingButton(false);
			
			//Go to PlayState
			StateManager.instance().changeState(1);
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
		
		//Draw rules
		displayRules(g);
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
		int textWidth = utility.getTextWidth(title, g);
		g.drawString(title, ((GamePanel.WIDTH / 2) - (textWidth / 2)), 100);
	}
	
	private void drawButtons(Graphics2D g)
	{
		//Draw buttons
		menuButton.draw(g);
		startButton.draw(g);
	}
	
	private void displayRules(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.PLAIN, 24));
		
		for(int i = 0; i < rules.size(); i++)
		{
			g.drawString(rules.get(i), 20, (150 + (i * 30)));
		}
			
	}
}
