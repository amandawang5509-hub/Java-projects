import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MastermindWindow extends JPanel implements ActionListener {
	private CardLayout cardLayout;
	private JPanel panel;
	private MastermindDraw gamePanel;
	private MastermindGame mastermind;
	private int turns = 10;

	public MastermindWindow() {
		cardLayout = new CardLayout();
		panel = new JPanel(cardLayout);
		JPanel start = new JPanel();
		start.setLayout(new BoxLayout(start, BoxLayout.Y_AXIS));

		JLabel titleLabel = new JLabel("Welcome to Mastermind!");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 27));
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setBorder(new EmptyBorder(40, 0, 20, 0));
		start.add(titleLabel);
		panel.add(start, "start");

		JLabel instructionsLabel = new JLabel("Instructions (please read!!):");
		instructionsLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		instructionsLabel.setAlignmentX(CENTER_ALIGNMENT);
		instructionsLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		start.add(instructionsLabel);

		JTextArea instructions = new JTextArea(
				"There will be a secret code consisting of 5 colors out of the 8 possible colors: \n"
						+ "red, blue, green, yellow, orange, white, magenta, and pink.\n"
						+ "\n ***It is possible for the colors to repeat in the code.***\n" + "\n"
						+ "Easy: 8 turns. Medium: 6 turns. Hard: 4 turns. \n" + "\n"
						+ "Each turn, you can guess a combination of colors to get 5 circles, which will indicate the accuracy of your guess.\n"
						+ "Small black circles = right color in the right spot.\n"
						+ "Small gray circles = color is in the code but in the wrong spot.\n"
						+ "Small white circles = not in the correct code.\n" + "\n"
						+ "For example, 3 black, 1 gray, 1 white means: \n"
						+ "3 colors are exactly right, 1 color is partially right, and 1 color is not right.\n" + "\n"
						+ "If you guess the correct code within the amount of turns, you win!");
		instructions.setFont(new Font("SansSerif", Font.PLAIN, 13));
		instructions.setOpaque(false);
		instructions.setMaximumSize(new Dimension(750, 300));
		instructions.setBorder(new EmptyBorder(15, 20, 10, 20));
		start.add(instructions);

		JPanel chooseDifficulty = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel difficulty = new JLabel("Choose your difficulty:");
		difficulty.setFont(new Font("SansSerif", Font.PLAIN, 15));
		chooseDifficulty.add(difficulty);

		JButton easy = new JButton("Easy");
		JButton medium = new JButton("Medium");
		JButton hard = new JButton("Hard");
		easy.addActionListener(this);
		medium.addActionListener(this);
		hard.addActionListener(this);
		easy.setFont(new Font("SansSerif", Font.PLAIN, 15));
		medium.setFont(new Font("SansSerif", Font.PLAIN, 15));
		hard.setFont(new Font("SansSerif", Font.PLAIN, 15));

		easy.setOpaque(true);
		easy.setBorderPainted(false);
		easy.setBackground(new Color(120, 200, 250));
		medium.setOpaque(true);
		medium.setBorderPainted(false);
		medium.setBackground(new Color(180, 210, 150));
		hard.setOpaque(true);
		hard.setBorderPainted(false);
		hard.setBackground(new Color(250, 110, 100));

		chooseDifficulty.add(easy);
		chooseDifficulty.add(medium);
		chooseDifficulty.add(hard);

		chooseDifficulty.setMaximumSize(new Dimension(750, 50));
		chooseDifficulty.setBorder(new EmptyBorder(10, 10, 10, 10));
		start.add(chooseDifficulty);

		JButton play = new JButton("PLAY");
		play.setFont(new Font("SansSerif", Font.PLAIN, 23));
		play.addActionListener(this);
		play.setAlignmentX(CENTER_ALIGNMENT);
		start.add(Box.createVerticalStrut(15));
		start.add(play);

		gamePanel = new MastermindDraw();
		panel.add(gamePanel, "game");
		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);

	}

	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();

		switch (s) {
		case "Easy":
			turns = 8;
			break;
		case "Medium":
			turns = 6;
			break;
		case "Hard":
			turns = 4;
			break;
		case "PLAY":
			startMastermindGame();
		}
	}

	public void startMastermindGame() {
		mastermind = new MastermindGame(gamePanel);
		mastermind.setTurns(turns);
		mastermind.startGame();
		gamePanel.setGame(mastermind);
		cardLayout.show(panel, "game");
		gamePanel.repaint();
	}
}
