
public class Die {
	
	//Current value of die
	private int value;
	
	private static final int MAX = 60;
	private static final int MIN = 10;
	
	public Die()
	{
		value = roll();
	}
	
	public Die(int value)
	{
		this.value = value;
	}
	
	public int getValue() {return value;}
	public void setValue(int value) {this.value = value;}
	
	public int roll()
	{
		value = (int) (((Math.random() * MAX) + MIN) / 10);
		return value;
	}
}
