package es.ucm.tp1.logic;

import java.util.Random;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.logic.gameObjects.ObstaclesList;
import es.ucm.tp1.logic.gameObjects.Player;

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
		
		updateBoard();
		
		
	}

	public void updateBoard() {
		for(int i = 0; i < getVisibility(); i++) {
			for(int j = 0; j < getRoadWidth(); j++) {
				if(this.player.inPosition(i, j))
					board [i][j]= this.player.toString();
				else
					board [i][j] = " ";
			}
		}
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

}
