package es.ucm.tp1.logic.gameObjects;

public class Obstacle {

	public int x;
	public int y;
	
	public Obstacle(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public String toString() {
		return "░";
	}

	public void move() {
		this.x -= 1;
	}
}
