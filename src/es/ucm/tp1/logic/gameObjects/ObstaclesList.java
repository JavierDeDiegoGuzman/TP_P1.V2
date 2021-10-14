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
			System.out.println("Objeto añadido: " + String.valueOf(obstacle.getX())  + " " + String.valueOf(obstacle.getY()));
		}
	}

	public void fillBoard(String[][] board) {
		int index = 0;
		System.out.println(String.valueOf(board.length) + " " + String.valueOf(board[0].length));
		while(index < this.pointer && this.list[index].getX() < board.length) {
			System.out.println("Boarded: " + String.valueOf(this.list[index].getX())
			+ " " + String.valueOf(this.list[index].getY() + " " + String.valueOf(index)));
			board[this.list[index].getX()][this.list[index].getY()] = this.list[index].toString();
			index++;
		}
		
	}


}
