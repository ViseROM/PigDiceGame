import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

public class Text 
{
	//To store the text
	private String text;
	
	//Font name of text
	private String fontName;
	
	//Font of text
	private Font font;
	
	//Font size
	private int size;
	
	//Color of text
	private Color color;

	//x position of text
	private int x;
	
	//y position of text
	private int y;
	
	private static final int DEFAULT_SIZE = 12;
	private static final Font DEFAULT_FONT = new Font("Courier New", Font.BOLD, DEFAULT_SIZE);
	private static final Color DEFAULT_COLOR = Color.BLACK;
	
	public Text(String text, String fontName, int size, Color color)
	{
		this.text = text;
		this.fontName = fontName;
		this.size = size;
		this.color = color;
		
		createFont();
	}
	
	public Text(String text, Font font, Color color)
	{
		this.text = text;
		this.font = font;
		this.fontName = font.getFontName();
		this.size = font.getSize();
		this.color = color;
	}
	
	public Text(String text)
	{
		this.text = text;
		this.font = DEFAULT_FONT;
		this.fontName = DEFAULT_FONT.getFontName();
		this.size = DEFAULT_SIZE;
		this.color = DEFAULT_COLOR;
	}
	
	public String getText() {return text;}
	public String getFontName() {return fontName;}
	public Font getFont() {return font;}
	public Color getColor() {return color;}
	public int getSize() {return size;}
	public int getX() {return x;}
	public int getY() {return y;}
	
	public void setText(String text) {this.text = text;}
	public void setFontName(String fontName) {this.fontName = fontName; createFont();}
	public void setSize(int size) {this.size = size; createFont();}
	public void setFont(Font font) {this.font = font;}
	public void setColor(Color color) {this.color = color;}
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	
	
	private void createFont() {font = new Font(fontName, Font.BOLD, size);}
	
	public void update()
	{
		
	}
	
	public void draw(Graphics2D g)
	{
		//Draw text
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, x, y);
	}
}
