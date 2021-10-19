package es.ucm.tp1.logic.gameObjects;

public class Player {
	
	private int x;
	private int y;
	private int coinscount;
	private boolean alive;
	
	public Player(int y) {
		this.x = 0;
		this.y = y;
		this.coinscount = 0;
		this.alive =true;
	}

	public boolean inPosition(int x, int y) {
		if(this.x == x && this.y ==y)
			return true;
		else 
			return false;
	}
	
	public String toString() {
		if (isAlive())
			return ">";
		else
			return "@";
	}
	
	public void fillBoard(String [][] board) {
		board[this.x][this.y]=toString();
	}

	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

	public void kill() {
		this.alive=false;
	}

	public boolean isAlive() {
		return this.alive;
	}

	public void addCoin() {
		this.coinscount ++;
	}

	public void goUp() {
		if(this.y > 0)
			y--;
	}

	public void goDown(int i) {
		if(this.y < i-1)
			y++;
	}

	public Object getCoins() {
		return this.coinscount;
	}
}
