
public class MouseManager {
	
	//For singleton
	public static MouseManager mouseManager;
	
	private boolean mousePressed;
	private boolean mouseReleased;
	
	private Point pressedPoint;
	private Point releasedPoint;
	private Point currentPoint;
	
	private MouseManager()
	{
		mousePressed = false;
		mouseReleased = false;
		
		pressedPoint = null;
		releasedPoint = null;
		currentPoint = null;
	}
	
	public static MouseManager instance()
	{
		if(mouseManager == null)
		{
			mouseManager = new MouseManager();
		}
		
		return mouseManager;
	}
	
	public boolean isMousePressed() {return mousePressed;}
	public boolean isMouseReleased() {return mouseReleased;}
	public Point getPressedPoint() {return pressedPoint;}
	public Point getReleasedPoint() {return releasedPoint;}
	public Point getCurrentPoint() {return currentPoint;}
	
	public void setMousePressed(boolean b) {mousePressed = b;}
	public void setMouseReleased(boolean b) {mouseReleased = b;}
	public void setPressedPoint(int mouseX, int mouseY) {pressedPoint = new Point(mouseX, mouseY);}
	public void setReleasedPoint(int mouseX, int mouseY) {releasedPoint = new Point(mouseX, mouseY);}
	public void setCurrentPoint(int mouseX, int mouseY) {currentPoint = new Point(mouseX, mouseY);}
	
	public void clearPressedPoint() {pressedPoint = null;}
	public void clearReleasedPoint() {releasedPoint = null;}
	public void clearCurrentPoint() {currentPoint = null;}
}
