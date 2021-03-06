package es.ucm.tp1.logic.gameObjects;

public class ObstaclesList {
	
	private Obstacle[] list;
	private int pointer;
	private static final int MAX = 100;
	
	public ObstaclesList() {
		this.list = new Obstacle[MAX];
		this.pointer =0;
		
	}

	public void tryAddObstacle(Obstacle obstacle, double obstacleFrecuency, double randomDouble) {
		if(randomDouble > obstacleFrecuency)
			addObstacle(obstacle);
	}
	
	public void addObstacle (Obstacle obstacle) {
		if(this.pointer < 100) {
			this.list[this.pointer] = obstacle;
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

	public void removeDeadObstacles() {
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

	public void removeObstacle(int y) {
		int index = 0;
		while(!(this.list[index].getX() == 0 && this.list[index].getY() == y)) {
			index++;
		}
		for(int i = 0; i < this.pointer-1; i++) {
			this.list[i] = this.list[i+1];
		}
		this.pointer--;
	}

	public int getNumberOfObstacles() {
		return this.pointer;
	}

}
