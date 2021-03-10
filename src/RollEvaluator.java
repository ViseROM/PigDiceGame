
public class RollEvaluator {
	
	private Die die1;
	private Die die2;
	
	public RollEvaluator()
	{
		this.die1 = null;
		this.die2 = null;
	}
	
	public RollEvaluator(Die die1, Die die2)
	{
		this.die1 = die1;
		this.die2 = die2;
	}
	
	public Die getDie1() {return die1;}
	public Die getDie2() {return die2;}
	
	public void setDie1(Die die1) {this.die1 = die1;}
	public void setDie2(Die die2) {this.die2 = die2;}
	public void setDice(Die die1, Die die2) {this.die1 = die1; this.die2 = die2;}
	
	public int getPigValue()
	{
		if(die1.getValue() == 1 && die2.getValue() != 1) //Rolled 1 pig
		{
			return 1;
		}
		else if(die1.getValue() != 1 && die2.getValue() == 1) // Rolled 1 pig
		{
			return 1;
		}
		else if(die1.getValue() == 1 && die2.getValue() == 1) // Rolled 2 pigs
		{
			return 2;
		}
		
		return 0;
	}
}
