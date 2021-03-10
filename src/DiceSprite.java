import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.*;

/**
 * Write a description of class DiceSprite here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DiceSprite
{
    //Name of DiceSprite
    private String name;
    
    //To store images of DiceSprite
    private ArrayList<BufferedImage> images;
    
    //The currentImage of DiceSprite
    private BufferedImage currentImage;
    
    //Number of images that DiceSprite has
    private int numImages;
    
    //x and y coordinate of dice sprite
    private int x;
    private int y;
    
    //Change in x and y position
    private double dx;
    private double dy;
    
    //Width and height of dice sprite
    private int width;
    private int height;
    
    //Speed of DiceSprite
    private double speed;
    
    //To count number of bounces
    private int numBounces;
    
    //Flag to see if rolling animation is occurring
    private boolean rolling;
    
    /**
     * Constructor for objects of class DiceSprite
     */
    public DiceSprite(String name)
    {
        this.name = name;
        images = new ArrayList<BufferedImage>();
        currentImage = null;
        numImages = 0;
        
        this.speed = 15;
        this.dx = 0;
        this.dy = speed;
        
        numBounces = 0;
        rolling = false;
    }
    
    //Getter methods
    public int getX(){return x;}
    public int getY(){return y;}
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    public int getNumImages(){return numImages;}
    public boolean isRolling(){return rolling;}
    
    //Setter methods
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public void setWidth(int width){this.width = width;}
    public void setHeight(int height){this.height = height;}
    public void setRolling(boolean r){this.rolling = r;}
    
    /**
     * Method that adds an image to DiceSprite array
     */
    public void addImage(String address)
    {
        try
        {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream(address));
            width = image.getWidth();
            height = image.getHeight();
            images.add(image);
            ++numImages;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error loading graphics");
            System.exit(0);
        }
    }
    
    /**
     * Method that changes the current image of DiceSprite
     */
    public void setCurrentImage(int i)
    {
        if(i < 0 || i >= numImages)
        {
            currentImage = null;
        }
        else
        {
            currentImage = images.get(i);
        }
    }
    
    /**
     * Method that create a rolling/bouncing animation for DiceSprite
     */
    public void update()
    {
        //A bouncing animation
        //Check if ball's next position will go beyond the floor
        if(y + dy > ((GamePanel.HEIGHT / 2) - height))
        {
            if(numBounces < 6)
            {
                //Changes ball direction when hitting the floor
                dy = -dy * 0.75;
                ++numBounces;
                
                //Change image
                int value = (int)(Math.random() * numImages);
                setCurrentImage(value);
            }
            else
            {
                dy = speed;
                y = ((GamePanel.HEIGHT / 2) - height);
                numBounces = 0;
                rolling = false;
            }
        }
        else
        {
            dy += 5;
        }
        
        //Set the ball's x and y position
        y += dy;
    }
    
    /**
     * Method that draws the DiceSprite
     */
    public void draw(Graphics2D g)
    {
        g.drawImage(currentImage, x, y, null);
    }
}