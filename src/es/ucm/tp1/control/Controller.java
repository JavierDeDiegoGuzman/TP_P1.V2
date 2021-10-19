package es.ucm.tp1.control;

import java.util.Scanner;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.view.GamePrinter;

public class Controller {

	private static final String PROMPT = "Command > ";

	private static final String UNKNOWN_COMMAND_MSG = "Unknown command";

	/* @formatter:off */
	private static final String[] HELP = new String[] {
		"Available commands:",
		"[h]elp: show this help",
		"[i]nfo: prints gameobjet info",
		"[n]one | []: update",
		"[q]: go up",
		"[a]: go down",
		"[e]xit: exit game",
		"[r]eset: reset game",
		"[t]est: enables test mode",	
	};
	private static final String[] INFO = new String[] {
	"Available objects:",
	"	[Car] the racing car",
	"	[Coin] gives 1 coin to the player",
	"	[Obstacle] hits car",
	};
	/* @formatter:off */

	private Game game;

	private Scanner scanner;
	
	private GamePrinter printer;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		this.printer = new GamePrinter(game);
	}

	public void printGame() {
		System.out.println(printer);
	}
	
	public void updateGame() {
		this.game.updateGame();
	}
	
	public void promtMovement() {
		String command;
		boolean error;
		do {
		error = false;
		System.out.print(this.PROMPT);
		command = this.scanner.nextLine();
		if (!this.parseCommand(command))
			error = true;
		}while(error);
	}
	

	public boolean parseCommand(String command) {
		if(this.game.getTestMode())
			System.out.println("[DEBUG] Executing: " + command);
		if(command.length() == 0);
		else if(equalOrEquivalent(command, "none"));
		else if(equalOrEquivalent(command, "q"))
			this.game.playerUp();
		else if(equalOrEquivalent(command, "a"))
			this.game.playerDown();
		else if(equalOrEquivalent(command, "a"))
			this.game.playerDown();
		else if(equalOrEquivalent(command, "reset"))
			this.game.reset();
		else if(equalOrEquivalent(command, "test")) {
			this.game.toggleTest();
			return false;
		}
		else if(equalOrEquivalent(command, "help")) {
			for(int i = 0; i < HELP.length; i++)
				System.out.println(HELP[i]);
			return false;
		}
		else if(equalOrEquivalent(command, "info")) {
			for(int i = 0; i < HELP.length; i++)
				System.out.println(INFO[i]);
			return false;
		}
		else {
			System.out.println(UNKNOWN_COMMAND_MSG);
			return false;
		}
		return true;
	}
	
	public boolean equalOrEquivalent(String input, String command) {

		if(input.equalsIgnoreCase(command))
			return true;
		else if(input.length() == 1 && command.charAt(0)==input.charAt(0)) {
			return true;
		}
		return false;
	}
	
	public boolean gameFinised() {

		boolean output = false;

		if(!this.game.checkFinishLine())
			if(this.game.checkPlayerStatus())
				output = true;
		return output;
	}
	

	public void printEndMessage() {
		System.out.println(printer.endMessage());
	}
	

	public void run() {
//		printGame();
		this.game.setTime(System.currentTimeMillis());
		while(gameFinised()) {
		printGame();
		promtMovement();
		updateGame();
		}
		printGame();
		System.out.print(this.printer.endMessage());
	}
}
