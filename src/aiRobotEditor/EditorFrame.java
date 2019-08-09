package aiRobotEditor;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class EditorFrame extends JFrame {
	public EditorFrame(int WIDTH,int HEIGHT, int gridWidth, int gridHeight) {
		this.setTitle("AI Robot Map Editor");
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new EditorBoard(gridWidth, gridHeight));
		this.setVisible(true);
		this.setResizable(false);
	}
}
