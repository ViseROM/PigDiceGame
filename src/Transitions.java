import java.awt.Graphics2D;
import java.awt.Color;

public class Transitions {
	private long timer;
	private long delay;
	private int width;
	private int height;
	private boolean done;
	
	public Transitions()
	{
		timer = System.nanoTime();
		delay = 10;
		width = 0;
		height = GamePanel.HEIGHT;
		done = false;
	}
	
	public boolean isDone() {return done;}
	
	public void update()
	{
		if(width >= GamePanel.WIDTH)
		{
			done = true;
			return;
		}
		
		if(((System.nanoTime() - timer)) / 1000000 > delay)
		{
			width += 25;
			timer = System.nanoTime();
		}
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
	}
}
