import java.awt.Graphics2D;

public class StateManager
{
    //For singleton
    private static StateManager stateManager;
    
    //Number of possible states
    private static final int NUM_STATES = 4;
    
    //State transition table
    private int [][] nextState;
    
    private State currentState;
    
    //private State previousState;
    private int currentStateIndex;
    
    private StateManager()
    {   
    	//State transition table
        nextState = new int[NUM_STATES][NUM_STATES];
        nextState[0][0] = 0; nextState[0][1] = 1; nextState[0][2] = 2;
        nextState[1][0] = 0; nextState[1][1] = 1; nextState[1][2] = 2;
        nextState[2][0] = 0; nextState[2][1] = 1; nextState[2][2] = 2;
        
        currentStateIndex = 0;
        
        currentState = new MenuState();
        //previousState = null;
    }
    
    public static StateManager instance()
    {
        if(stateManager == null)
        {
            stateManager = new StateManager();
        }
        
        return stateManager;
    }
    
    //Getter Methods
    public State getCurrentState(){return currentState;}
    //public State getPreviousState() {return previousState;}
    
    //public void clearPreviousState() {previousState = null;}
 
    
    public void changeState(int transition)
    {
    	if(nextState[currentStateIndex][transition] == -1)
    	{
    		return;
    	}
    	
        //previousState = currentState;
        currentStateIndex = nextState[currentStateIndex][transition];
        
        switch(currentStateIndex)
        {
        	case 0:
        		currentState = new MenuState();
        		break;
        	case 1:
        		currentState = new PlayState();
        		break;
        	case 2:
        		currentState = new RulesState();
        		break;
        }
    }
    
    public void update()
    {
        currentState.update();
    }
    
    public void draw(Graphics2D g)
    {
        currentState.draw(g);
    }
}