package es.ucm.tp1.logic.gameObjects;

public class Coin {

	private int x;
	private int y;
	
	public Coin(int x, int y) {
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
		return "¢";
	}

	public void move() {
		this.x -= 1;
	}

	public boolean inPosition(int x, int y) {
		return this.x == x && this.y == y;
	}
}
