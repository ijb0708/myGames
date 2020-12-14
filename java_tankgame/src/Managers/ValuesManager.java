package Managers;

public class ValuesManager {
	private int stage;
	private int score;
	
	private static ValuesManager instance = new ValuesManager();
	
	public static ValuesManager getInstance() {
		return instance;
	}
	
	private ValuesManager () {
		stage=1;
		score=0;
	}
	
	public void upStage() {
		stage++;
	}
	
	private void upScore() {
		
	}
	
	public int getStage() {
		return stage;
	}
	
	public int getScore() {
		return score;
	}
	
	public void resetStage() {
		stage=1;
	}
	
	public void resetScore() {
		score=0;
	}
}
