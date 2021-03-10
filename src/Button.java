import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Write a description of class Button here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Button
{
    //Name of button
    private String name;
    
    //Button images
    private BufferedImage image1;
    private BufferedImage image2;
    
    //x and y position of button
    private int x;
    private int y;
    
    //Width and height of button
    private int width;
    private int height;
    
    //Flag to see if mouse is touching/clicking button
    private boolean mouseTouchingButton;
    private boolean mouseClickingButton;
    
    //Flag to see if button is disabled
    private boolean disabled;
    
    private MouseManager mouseManager;

    /**
     * Constructor for objects of class Button
     */
    public Button(String name, String address1, String address2)
    {
        //Obtain images
        try{
            //Obtain images from the image folder
            image1 = ImageIO.read(getClass().getResourceAsStream(address1));
            image2 = ImageIO.read(getClass().getResourceAsStream(address2));
            
            this.width = image1.getWidth();
            this.height = image2.getHeight();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error loading graphics");
            System.exit(0);
        }
        
        mouseTouchingButton = false;
        mouseClickingButton = false;
        
        disabled = false;
        
        mouseManager = MouseManager.instance();
    }
    
    public String getName(){return name;}
    public int getX(){return x;}
    public int getY(){return y;}
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    public boolean isMouseTouchingButton(){return mouseTouchingButton;}
    public boolean isMouseClickingButton(){return mouseClickingButton;}
    public boolean isDisabled(){return disabled;}
    
    public void setName(String name){this.name = name;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public void setMouseTouchingButton(boolean b){this.mouseTouchingButton = b;}
    public void setMouseClickingButton(boolean b){this.mouseClickingButton = b;}
    public void setDisabled(boolean b){this.disabled = b;}
    
    public void update()
    {	
    	if(mouseManager.getCurrentPoint() != null)
    	{
    		int mouseX = mouseManager.getCurrentPoint().getX();
        	int mouseY = mouseManager.getCurrentPoint().getY();
        	
        	//Check if mouse is touching button
        	if(atLocation(this, mouseX, mouseY) == true)
        	{
        		mouseTouchingButton = true;
        	}
        	else
        	{
        		mouseTouchingButton = false;
        	}
    	}
    	
    	if(disabled == true)
    	{
    		if(mouseManager.getPressedPoint() != null && mouseManager.getReleasedPoint() != null)
    		{
    			//Pressed location
    	    	int pressedX = mouseManager.getPressedPoint().getX();
    	    	int pressedY = mouseManager.getPressedPoint().getY();
    	    	
    	    	//Released location
    	    	int releasedX = mouseManager.getReleasedPoint().getX();
    	    	int releasedY = mouseManager.getReleasedPoint().getY();
    	    	
    	    	//Check if mouse has pressed button
    	    	if(atLocation(this, pressedX, pressedY) == true && atLocation(this, releasedX, releasedY) == true)
    	    	{
    	    		mouseManager.clearPressedPoint();
    	    		mouseManager.clearReleasedPoint();
    	    	}
    		}
    		return;
    	}
    	
    	//Check if mouse is clicking button
    	if(mouseManager.isMouseReleased() == true)
    	{
    		if(mouseManager.getPressedPoint() != null && mouseManager.getReleasedPoint() != null)
    		{
    			//Pressed location
    	    	int pressedX = mouseManager.getPressedPoint().getX();
    	    	int pressedY = mouseManager.getPressedPoint().getY();
    	    	
    	    	//Released location
    	    	int releasedX = mouseManager.getReleasedPoint().getX();
    	    	int releasedY = mouseManager.getReleasedPoint().getY();
    	    	
    	    	//Check if mouse has pressed button
    	    	if(atLocation(this, pressedX, pressedY) == true && atLocation(this, releasedX, releasedY) == true)
    	    	{
    	    		mouseClickingButton = true;
    	    		mouseManager.clearPressedPoint();
    	    		mouseManager.clearReleasedPoint();
    	    	}
    		}
    	}
    }
    
    public void draw(Graphics2D g)
    {
        if(mouseTouchingButton == false)
        {
            g.drawImage(image1, x, y, null);
        }
        else
        {
            g.drawImage(image2, x, y, null);
        }
    }
    
    private boolean atLocation(Button button, int mouseX, int mouseY)
    {
        int x = button.getX();
        int y = button.getY();
        int width = button.getWidth();
        int height = button.getHeight();
        
        if((mouseX >= x && mouseX <= (x + width)) && (mouseY >= y && mouseY <= (y + height)))
        {
            return true;
        }
        
        return false;
    }
}