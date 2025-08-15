import javax.swing.JFrame;

public class MastermindStart {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Mastermind");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(750, 600);
		MastermindWindow window = new MastermindWindow();
		frame.add(window);
		frame.setVisible(true);
	}

}
