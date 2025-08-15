import java.awt.Color;
import java.util.Arrays;

public class MastermindGame {
	String answer;
	private MastermindDraw graphics;
	private int turns;

	public MastermindGame(MastermindDraw graphics) {
		this.graphics = graphics;
	}

	public void startGame() {
		setAnswer();
		graphics.setTurns(turns);
	}

	public void setTurns(int x) {
		turns = x;
	}

	public int getTurns() {
		return turns;
	}

	public void setAnswer() {// set randomized code
		answer = String.valueOf((int) (8 * Math.random() + 1));
		for (int i = 0; i < 4; i++) {
			answer += String.valueOf((int) (8 * Math.random() + 1));
		}
	}

	public Color drawCircles(String guess, int i) {// convert guess to colors
		switch (guess.substring(i - 1, i)) {
		case "r":
			return new Color(250, 110, 100);
		case "b":
			return new Color(120, 200, 250);
		case "g":
			return new Color(180, 210, 150);
		case "y":
			return new Color(250, 230, 120);
		case "o":
			return new Color(240, 160, 90);
		case "w":
			return Color.white;
		case "m":
			return new Color(230, 130, 220);
		case "p":
			return Color.pink;
		default:
			return Color.white;
		}
	}

	public String answerToColors(String answer) {// convert to colors to print
		String newAnswer = "";
		for (int i = 0; i < 5; i++) {
			switch (answer.substring(i, i + 1)) {
			case "1":
				newAnswer += "r";
				break;
			case "2":
				newAnswer += "b";
				break;
			case "3":
				newAnswer += "g";
				break;
			case "4":
				newAnswer += "y";
				break;
			case "5":
				newAnswer += "o";
				break;
			case "6":
				newAnswer += "w";
				break;
			case "7":
				newAnswer += "m";
				break;
			case "8":
				newAnswer += "p";
				break;
			}
		}
		return newAnswer;
	}
}
