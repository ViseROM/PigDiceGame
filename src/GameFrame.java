import javax.swing.JFrame;

/**
 * Write a description of class GameFrame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameFrame
{
    public static void main(String args[])
    {
        //Create window
        JFrame window = new JFrame("Pig Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create GamePanel object
        GamePanel panel = new GamePanel();
        
        //Store panel in content pane
        window.setContentPane(panel);
        
        window.pack();
        
        window.setResizable(false);
        
        //Put window in center of screen
        window.setLocationRelativeTo(null);
        
        //Make window visible
        window.setVisible(true);
    }
}
