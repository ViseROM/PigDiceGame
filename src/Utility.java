import java.awt.Graphics2D;

public class Utility {
	
	public Utility()
	{
		
	}
	
	public int getTextWidth(String text, Graphics2D g)
    {
        int length = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
        
        return length;
    }
}
