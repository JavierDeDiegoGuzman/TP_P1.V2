package es.ucm.tp1.logic.gameObjects;

public class Player {
	
	public int x;
	public int y;
	public boolean alive;
	
	public Player(int y) {
		this.x = 0;
		this.y = y;
		this.alive =true;
	}

	public boolean inPosition(int x, int y) {
		if(this.x == x && this.y ==y)
			return true;
		else 
			return false;
	}
	
	public String toString() {
		if (alive)
			return ">";
		else
			return "@";
	}

}
