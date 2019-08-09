package aiRobotSearch;

import javax.swing.JFrame;

import cg23.Grid;

@SuppressWarnings("serial")
public class SearchFrame extends JFrame {

	public SearchFrame(int width, int height, Grid grid) {
		this.setTitle("AI Robot Search");
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new SearchBoard(grid));
		this.setVisible(true);
		this.setResizable(false);
	}

}
