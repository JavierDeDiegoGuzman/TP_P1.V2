package es.ucm.tp1.logic;

import java.util.Random;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.logic.gameObjects.ObstaclesList;
import es.ucm.tp1.logic.gameObjects.Player;
import es.ucm.tp1.logic.gameObjects.Coin;
import es.ucm.tp1.logic.gameObjects.CoinsList;
import es.ucm.tp1.logic.gameObjects.Obstacle;

public class Game {
	
	private Level level;
	private Player player;
	private ObstaclesList obstacleList;
	private CoinsList coinlist;
	private Random random;
	private boolean testMode;
	private double time;
	private String[][] board;
	private long seed;
	private int cycles;
	private int totalObstacles;
	private int totalCoins;
	
	public Game(Long seed, Level level, boolean isTestMode) {
		this.level = level;
		this.seed = seed;
		reset();
	}
	
	public void reset() {
		this.obstacleList = new ObstaclesList();
		this.coinlist = new CoinsList();
		this.random = new Random(this.seed);
		this.player = new Player(getRoadWidth()/2);
		this.board = new String [getVisibility()][getRoadWidth()];
		this.cycles = 0;
		
		
		generateObjects();
		updateBoard();
		
		this.totalObstacles = this.obstacleList.getNumberOfObstacles();
		this.totalCoins = this.coinlist.getNumberOfCoins();
//		this.obstacleList.printListy y();
	}
	
	public void generateObjects() {
		int objectLane, coinLane;
		double oRandomDouble, cGenRandomDouble;
		for(int x = this.level.getVisibility()/2; x < this.level.getLenght(); x++) {
			
			objectLane = (int)(random.nextDouble()*getRoadWidth());
			oRandomDouble= random.nextDouble();
			coinLane = (int)(random.nextDouble()*getRoadWidth());
			cGenRandomDouble= random.nextDouble();
			
			if(oRandomDouble < level.getObstacleFrecuency())
				this.obstacleList.addObstacle(new Obstacle (x, objectLane));
			if(objectLane !=coinLane || (oRandomDouble > this.level.getObstacleFrecuency())) {
				if(cGenRandomDouble < level.getCoinFrecuency())
					this.coinlist.addCoin(new Coin (x,coinLane));
			}
		}
	}

	public void updateBoard() {
		for(int i = 0; i < getVisibility(); i++) {
			for(int j = 0; j < getRoadWidth(); j++) {
				board[i][j] = " ";
			}
		}
		this.obstacleList.fillBoard(board);
		this.coinlist.fillBoard(board);
		if(this.level.getLenght()-this.cycles <= this.getVisibility() && this.level.getLenght()-this.cycles > 0)
			for(int i = 0; i < this.getRoadWidth(); i++) {
				this.board[this.level.getLenght()-this.cycles-1 ][i] = "¦";
			}
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
		return (int)(this.random.nextDouble()*getRoadWidth());
	}
	
	public double getRandomDouble() {
		return this.random.nextDouble();
	}
	
	public int getCycles() {
		return this.cycles;
	}
	
	public int getTotalObstacles() {
		return this.totalObstacles;
	}
	
	public int getTotalCoins() {
		return this.totalCoins;
	}
	
	public boolean getTestMode() {
		return this.testMode;
	}
	
	
	public Level getLevel() {
		return this.level;
	}
	


	public void updateGame() {
		this.obstacleList.move();
		this.coinlist.move();
		removeDeadObject();
		checkColision();
		updateBoard();
		this.cycles++;
//		this.obstacleList.printList();
	}

	private void checkColision() {
		if(this.obstacleList.checkColision(this.player.getX(),this.player.getY())) {
			this.player.kill();
			this.obstacleList.removeObstacle(this.player.getY());
		}
		else if(this.coinlist.checkColision(this.player.getX(), this.player.getY())) {
			this.player.addCoin();
			this.totalCoins--;
			this.coinlist.removeCoin(this.player.getY());
		}
	}

	public void removeDeadObject() {
		this.obstacleList.removeDeadObstacles();
		this.coinlist.removeDeadCoins();
	}

//	public boolean parseCommand(String command) {
//		if(command.length() == 0);
//		else if(equalOrEquivalent(command, "none"));
//		else if(equalOrEquivalent(command, "q"))
//			this.player.goUp();
//		else if(equalOrEquivalent(command, "a"))
//			this.player.goDown(getRoadWidth());
//		else if(equalOrEquivalent(command, "a"))
//			this.player.goDown(getRoadWidth());
//		else
//			return false;
//		return true;
//	}
//	
//	public boolean equalOrEquivalent(String input, String command) {
//
//		if(input.equals(command))
//			return true;
//		else if(input.length() == 1 && command.charAt(0)==input.charAt(0)) {
//			return true;
//		}
//		return false;
//	}
	
	public void playerDown() {
		this.player.goDown(getRoadWidth());
	}

	public void playerUp() {
		this.player.goUp();

	}

	public boolean checkFinishLine() {
		return this.cycles > this.level.getLenght();
	}

	public boolean checkPlayerStatus() {
		return this.player.isAlive();
	}

	public int getDistance() {
		return this.level.getLenght() - this.cycles;
	}

	public Object getPlayerCoins() {
		return this.player.getCoins();
	}

	public void toggleTest() {
		this.testMode =true;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}


//	public String getInfo() {
//		StringBuilder str = new StringBuilder();
//		str.append(b)
//	}
	

}
