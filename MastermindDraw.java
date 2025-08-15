import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class MastermindDraw extends JPanel implements ActionListener {

	private Color[] color = new Color[5];
	private ArrayList<String> guess;
	private ArrayList<Color[]> guesses;
	private ArrayList<int[]> results;
	private JLabel currentGuess;
	private MastermindGame game;
	private MastermindChecker checker;
	private JButton submit;
	private JButton undo;
	private int guessCount;
	private int turns;

	public MastermindDraw() {
		setOpaque(true);
		setLayout(new BorderLayout());

		guess = new ArrayList<>();
		guesses = new ArrayList<>();
		results = new ArrayList<>();
		color = new Color[5];
		guessCount = 0;
		turns = 6;

		JPanel buttons = new JPanel(new FlowLayout());
		buttons.setBackground(Color.white);
		buttons.setPreferredSize(new Dimension(730, 80));

		JButton red = new JButton("Red");
		red.setBackground(new Color(250, 110, 100));
		red.setOpaque(true);

		red.setBorderPainted(false);
		red.addActionListener(this);
		buttons.add(red);

		JButton blue = new JButton("Blue");
		blue.setBackground(new Color(120, 200, 250));
		blue.setOpaque(true);
		blue.setBorderPainted(false);
		blue.addActionListener(this);
		buttons.add(blue);

		JButton green = new JButton("Green");
		green.setBackground(new Color(180, 210, 150));
		green.setOpaque(true);
		green.setBorderPainted(false);
		green.addActionListener(this);
		buttons.add(green);

		JButton yellow = new JButton("Yellow");
		yellow.setBackground(new Color(250, 230, 120));
		yellow.setOpaque(true);
		yellow.setBorderPainted(false);
		yellow.addActionListener(this);
		buttons.add(yellow);

		JButton orange = new JButton("Orange");
		orange.setBackground(new Color(240, 160, 90));
		orange.setOpaque(true);
		orange.setBorderPainted(false);
		orange.addActionListener(this);
		buttons.add(orange);

		JButton white = new JButton("White");
		white.setBackground(Color.white);
		white.setOpaque(true);
		white.setBorderPainted(false);
		white.addActionListener(this);
		buttons.add(white);

		JButton magenta = new JButton("Magenta");
		magenta.setBackground(new Color(230, 130, 220));
		magenta.setOpaque(true);
		magenta.setBorderPainted(false);
		magenta.addActionListener(this);
		buttons.add(magenta);

		JButton pink = new JButton("Pink");
		pink.setBackground(Color.pink);
		pink.setOpaque(true);
		pink.setBorderPainted(false);
		pink.addActionListener(this);
		buttons.add(pink);

		undo = new JButton("Undo");
		undo.addActionListener(this);
		buttons.add(undo);

		submit = new JButton("Submit");
		submit.addActionListener(this);
		buttons.add(submit);

		currentGuess = new JLabel("Guess: ");
		buttons.add(currentGuess);

		add(buttons, BorderLayout.SOUTH);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < guesses.size(); i++) {
			drawGuess(g, guesses.get(i), i + 1);
			drawResult(g, results.get(i), i + 1);
		}
	}

	public void drawGuess(Graphics g, Color[] c, int row) { // draws one row of circles
		for (int i = 0; i < 5; i++) {
			g.setColor(Color.black);
			g.drawOval(30 * (i + 1), 50 * row, 20, 20);
			g.setColor(c[i]);
			g.fillOval(30 * (i + 1), 50 * row, 20, 20);
		}
	}

	public void drawResult(Graphics g, int[] x, int row) { // draws one row of results
		for (int i = 0; i < 5; i++) {
			int xCoord = 180 + (20 * (i + 1));
			g.setColor(Color.black);
			g.drawOval(xCoord, 50 * row, 10, 10);
			switch (x[i]) {
			case 2:
				g.setColor(Color.black);
				break;
			case 1:
				g.setColor(new Color(180, 180, 180));
				break;
			case 0:
				g.setColor(Color.white);
				break;
			}
			g.fillOval(xCoord, 50 * row, 10, 10);
		}
	}

	public void setColors(Color[] c) {
		Color[] colorCopy = new Color[5];
		for (int i = 0; i < 5; i++) {
			colorCopy[i] = c[i];
		}
		guesses.add(colorCopy);
		repaint();
	}

	public void getResult(int[] x) {
		int[] resultCopy = new int[5];
		for (int i = 0; i < 5; i++) {
			resultCopy[i] = x[i];
		}
		results.add(resultCopy);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();

		switch (s) {
		case "Red":
		case "Blue":
		case "Green":
		case "Yellow":
		case "Orange":
		case "White":
		case "Magenta":
		case "Pink":
			if (guess.size() < 5) {
				guess.add(s.substring(0, 1).toLowerCase());
				updateGuess();
			}
			break;
		case "Undo":
			if (!guess.isEmpty()) {
				guess.remove(guess.size() - 1);
				updateGuess();

			}
			break;
		case "Submit":
			if (guess.size() == 5) {
				guessCount++;
				for (int i = 0; i < 5; i++) {
					switch (guess.get(i)) {
					case "r":
						color[i] = new Color(250, 110, 110);
						break;
					case "b":
						color[i] = new Color(120, 200, 250);
						break;
					case "g":
						color[i] = new Color(180, 210, 150);
						break;
					case "y":
						color[i] = new Color(250, 230, 120);
						break;
					case "o":
						color[i] = new Color(240, 160, 90);
						break;
					case "w":
						color[i] = Color.white;
						break;
					case "m":
						color[i] = new Color(230, 130, 220);
						break;
					case "p":
						color[i] = Color.pink;
						break;
					}
				}

				setColors(color);

				String g = "";
				for (String c : guess) {
					g += c;
				}
				checker = new MastermindChecker(g, game.answer);
				int[] result = checker.returnExactAndPartial();
				getResult(result);

				boolean win = true;
				for (int i = 0; i < 5; i++) {
					if (result[i] != 2) {
						win = false;
						break;
					}
				}

				if (win) {
					JOptionPane.showMessageDialog(this, "You guessed the correct code!");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					System.exit(0);
				}

				else if (guessCount >= turns) {
					JOptionPane.showMessageDialog(this,
							"You ran out of turns. The correct code was " + game.answerToColors(game.answer));
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					System.exit(0);
				}

				guess.clear();
				updateGuess();
			}
			break;
		}

	}

	public void updateGuess() {
		String label = "Guess: ";
		for (String c : guess) {
			label += c;
		}
		currentGuess.setText(label);
	}

	public void setGame(MastermindGame game) {
		this.game = game;
		turns = game.getTurns();
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}

}
