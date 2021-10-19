package es.ucm.tp1.view;

import es.ucm.tp1.logic.Game;
//import es.ucm.tp1.logic.gameobjects.Coin;
import es.ucm.tp1.logic.gameObjects.Obstacle;
import es.ucm.tp1.utils.*;

public class GamePrinter {

	private static final String SPACE = " ";

	private static final String ROAD_BORDER_PATTERN = "═";

	private static final String LANE_DELIMITER_PATTERN = "─";

	private static final int CELL_SIZE = 7;

	private static final int MARGIN_SIZE = 2;

	private static final String CRASH_MSG = String.format("Player crashed!%n");

	private static final String WIN_MSG = String.format("Player wins!%n");

	private static final String DO_EXIT_MSG = "Player leaves the game";

	private static final String GAME_OVER_MSG = "[GAME OVER] ";

	private static final String DISTANCE_MSG = "Distance: ";

	private static final String COINS_MSG = "Coins: ";

	private static final String CYCLE_MSG = "Cycle: ";

	private static final String TOTAL_OBSTACLES_MSG = "Total obstacles: ";

	private static final String TOTAL_COINS_MSG = "Total coins: ";

	private static final String ELAPSED_TIME_MSG = "Elapsed Time: ";

	private static final String NEW_RECORD_MSG = "New record!: ";

	private static final String RECORD_MSG = "Record: ";

	private static final String NEW_LINE = System.lineSeparator();

	private String indentedRoadBorder;

	private String indentedLanesSeparator;

	private String margin;

	private Game game;

	public GamePrinter(Game game) {
		this.game = game;

		this.margin = StringUtils.repeat(SPACE, MARGIN_SIZE);

		String roadBorder = ROAD_BORDER_PATTERN + StringUtils.repeat(ROAD_BORDER_PATTERN, (CELL_SIZE + 1) * game.getVisibility());
		this.indentedRoadBorder = String.format("%n%s%s%n", margin, roadBorder);

		String laneDelimiter = StringUtils.repeat(LANE_DELIMITER_PATTERN, CELL_SIZE);
		String lanesSeparator = SPACE + StringUtils.repeat(laneDelimiter + SPACE, game.getVisibility() - 1)	+ laneDelimiter + SPACE;

		this.indentedLanesSeparator = String.format("%n%s%s%n", margin, lanesSeparator);
	}

	protected StringBuilder getInfo() {
		StringBuilder str = new StringBuilder();
		
		str.append(DISTANCE_MSG);
		str.append(String.valueOf(this.game.getDistance())+'\n');
		str.append(COINS_MSG);
		str.append(String.valueOf(this.game.getPlayerCoins())+'\n');
		str.append(CYCLE_MSG);
		str.append(String.valueOf(this.game.getCycles())+'\n');
		str.append(TOTAL_OBSTACLES_MSG);
		str.append(String.valueOf(this.game.getTotalObstacles())+'\n');
		str.append(TOTAL_COINS_MSG);
		str.append(String.valueOf(this.game.getTotalCoins())+'\n');
		if(!this.game.getTestMode()) 
			str.append("Elapsed Time:" + String.valueOf((System.currentTimeMillis()-this.game.getTime())/1000));
		
		return str;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		// Game Status

		str.append(getInfo());

		// Paint game

		str.append(indentedRoadBorder);

		String verticalDelimiter = SPACE;

		for (int y = 0; y < game.getRoadWidth(); y++) {
			str.append(this.margin).append(verticalDelimiter);
			for (int x = 0; x < game.getVisibility(); x++) {
				str.append(StringUtils.centre(game.positionToString(x, y), CELL_SIZE)).append(verticalDelimiter);
			}
			if (y < game.getRoadWidth() - 1) {
				str.append(this.indentedLanesSeparator);
			}
		}
		str.append(this.indentedRoadBorder);

		return str.toString();
	}

	public String endMessage() {

		String s = GAME_OVER_MSG;

		if(!this.game.checkPlayerStatus())
			s += CRASH_MSG;
		else if(this.game.checkFinishLine())
			s += WIN_MSG;
		
		if(!this.game.getTestMode())
			s += "Elapsed Time:" + String.valueOf((System.currentTimeMillis()-this.game.getTime())/1000);

		return s;
	}

}
