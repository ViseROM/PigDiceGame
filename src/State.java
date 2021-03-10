import java.awt.Graphics2D;

/**
 * Abstract class State - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class State
{
    protected State()
    {
        
    }
    
    protected abstract void update();
    protected abstract void draw(Graphics2D g);
}
