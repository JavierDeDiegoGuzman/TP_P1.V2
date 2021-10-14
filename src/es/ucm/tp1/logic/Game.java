package es.ucm.tp1.logic;

import java.util.Random;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.logic.gameObjects.ObstaclesList;
import es.ucm.tp1.logic.gameObjects.Player;
import es.ucm.tp1.logic.gameObjects.Obstacle;

public class Game {
	
	public Level level;
	public Player player;
	public ObstaclesList obstacleList;
	public Random random;
	public boolean testMode;
	public String[][] board;
	
	public Game(Long seed, Level level, boolean isTestMode) {
		this.obstacleList = new ObstaclesList();
		this.random = new Random(seed);
		this.level = level;
		this.player = new Player(getRoadWidth()/2);
		this.board = new String [getVisibility()][getRoadWidth()];
		
		
		generateObjects();
		updateBoard();
		
		
	}
	
	public void generateObjects() {
		for(int x = this.level.getVisibility()/2; x < this.level.getLenght(); x++) {
			this.obstacleList.tryAddObstacle(new Obstacle(x, getRandomLane()), this.level.getObstacleFrecuency(), getRandomDouble());
		}
	}

	public void updateBoard() {
		for(int i = 0; i < getVisibility(); i++) {
			for(int j = 0; j < getRoadWidth(); j++) {
				board[i][j] = " ";
			}
		}
		this.obstacleList.fillBoard(board);
		this.player.fillBoard(board);
	}
		
	public String positionToString(int x, int y) {
		return this.board[x][y];
	}

	public int getVisibility() {
		return this.level.getVisibility();
	}

	public int getRoadWidth() {
		return this.level.getWidth();
	}
	
	public int getRandomLane() {
		return random.nextInt(this.level.getWidth());
	}
	
	public double getRandomDouble() {
		return this.random.nextDouble();
	}

	public void updateGame() {
		this.obstacleList.move();
		removeDeadObject();
		updateBoard();
	}

	public void removeDeadObject() {
		this.obstacleList.removeDeadObstacles();
	}

	public void parseCommand(String command) {
		
	}

}
