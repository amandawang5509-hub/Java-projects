
public class MastermindChecker {

	int[] guessColors = new int[5];
	int[] answer = new int[5];
	int[] results = new int[5]; // use 0 as incorrect, 1 as partially correct, 2 as completely correct

	public MastermindChecker(String guess, String answer) {
		for (int i = 0; i < 5; i++) {
			this.answer[i] = Integer.valueOf(answer.substring(i, i + 1));
			guessColors[i] = colorToInt(guess, i + 1);
			results[i] = 0;
		}
		exactChecker();
		partialChecker();
	}

	public int[] returnExactAndPartial() {
		return results;
	}

	public int colorToInt(String guess, int i) {
		switch (guess.substring(i - 1, i)) {
		case "r":
			return 1;
		case "b":
			return 2;
		case "g":
			return 3;
		case "y":
			return 4;
		case "o":
			return 5;
		case "w":
			return 6;
		case "m":
			return 7;
		case "p":
			return 8;
		default:
			return 0;
		}
	}

	public void exactChecker() {
		for (int i = 0; i < 5; i++) {
			if (answer[i] == guessColors[i]) {
				results[i] = 2;
				answer[i] = -1;
				guessColors[i] = -1;
			}
		}
	}

	public void partialChecker() {
		for (int i = 0; i < 5; i++) {
			if (results[i] == 2) {
				continue;
			}
			for (int j = 0; j < 5; j++) {
				if (answer[j] == -1) {
					continue;
				}
				if (answer[j] == guessColors[i]) {
					results[i] = 1;
					answer[j] = -1;
					guessColors[i] = -1;
					break;
				}

			}
		}
	}
}