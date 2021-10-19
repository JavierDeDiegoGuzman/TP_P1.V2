package es.ucm.tp1.logic.gameObjects;

public class CoinsList {

	private Coin[] list;
	private int pointer;
	private static final int MAX = 100;
	
	public CoinsList() {
		this.list = new Coin[MAX];
		this.pointer =0;
		
	}

	public void tryAddCoin(Coin Coin, double CoinFrecuency, double randomDouble, ObstaclesList obstacleList) {
		if(randomDouble > CoinFrecuency && !obstacleList.checkColision(Coin.getX(), Coin.getY())) {
			addCoin(Coin);
		}
	}
	
	public void addCoin (Coin Coin) {
		if(this.pointer < 100) {
			this.list[this.pointer] = Coin;
			pointer ++;
		}
	}

	public void fillBoard(String[][] board) {
		int index = 0;
		while(index < this.pointer && this.list[index].getX() < board.length) {
//			System.out.println(String.valueOf(this.list[index].getX()) + String.valueOf(this.list[index].getY()));
			board[this.list[index].getX()][this.list[index].getY()] = this.list[index].toString();
			index++;
		}
		
		
	}

	public void move() {
		for(int i = 0; i < this.pointer; i++) {
			this.list[i].move();
		}
	}

	public void removeDeadCoins() {
		while(this.list[0].getX() < 0 && this.pointer > 0) {
			for(int i = 0; i < this.pointer-1; i++) {
				this.list[i] = this.list[i+1];
			}
			this.pointer--;
		}
	}

	public boolean checkColision(int x, int y) {
		boolean found= false;
		int i = 0;
		while(!found && i < this.pointer) {
			found = this.list[i].inPosition(x, y);
			i++;
		}
		return found;
	}

	public void removeCoin(int y) {
		int index = 0;
		while(!(this.list[index].getX() == 0 && this.list[index].getY() == y)) {
			index++;
		}
		for(int i = 0; i < this.pointer-1; i++) {
			this.list[i] = this.list[i+1];
		}
		this.pointer--;
	}
	
	public int getNumberOfCoins() {
		return this.pointer;
	}
}
