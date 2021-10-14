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
		while(this.list[0].getX() < 0) {
			for(int i = 0; i < this.pointer-1; i++) {
				this.list[i] = this.list[i++];
			}
		}
	}


}
