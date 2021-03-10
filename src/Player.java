public class Player {
	
	private String name;
	private int score;
	
	public Player(String name)
	{
		this.name = name;
		this.score = 0;
	}
	
	public String getName() {return name;}
	public int getScore() {return score;}
	
	public void setName(String name) {this.name = name;}
	public void setScore(int score) {this.score = score;}
	
	public void addToScore(int amount)
	{
		this.score += amount;
	}
	
	public void removeFromScore(int amount)
	{
		this.score -= this.score;
	}
}
