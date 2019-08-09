package aiRobot;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cg23.Grid;

@SuppressWarnings("serial")
public class MainMenu extends JFrame {
	static Scanner scanner;
	private int width = 400, height = 150;
	private JPanel mainMenu = new JPanel();
	private JButton editor = new JButton("Editor");
	private JButton search = new JButton("Search");


	public MainMenu() {
		createMenu();
		addButtons();
	}

	private void createMenu() {
		mainMenu.setLayout(null);
		this.add(mainMenu);
		this.setSize(width, height);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void addButtons() {

		editor.setBounds(50, 50, 128, 35);
		search.setBounds(width-(50+128), 50, 128, 35);
		mainMenu.add(editor);
		mainMenu.add(search);

		editor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int width, height;

				// Maybe use JSpinner instead?
				try {
					String widthString = JOptionPane.showInputDialog(null,"Enter Grid Width:", "Grid Width", JOptionPane.QUESTION_MESSAGE);
					width = Integer.parseInt(widthString);

					String heightString = JOptionPane.showInputDialog(null,"Enter Grid Height:", "Grid Height", JOptionPane.QUESTION_MESSAGE);
					height = Integer.parseInt(heightString);
				} catch (NumberFormatException e1) {
					width = -1;
					height = -1;
					System.out.println("String conversion failed.");
				}
				if(height > 0 || width > 0) {
					dispose();
					new aiRobotEditor.EditorFrame(width * 32, (height * 32) + 20, width, height);
				} else {
					JOptionPane.showMessageDialog(null, "Grid height/width must be positive integer.", "Grid Option Error.", JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int width, height;

				Grid grid = loadGrid();

				if(grid != null) {
					dispose();
					height = grid.height();
					width = grid.width();
					new aiRobotSearch.SearchFrame(width * 32, (height * 32) +20, grid);
				}
			}

		});
	}

	private static Grid loadGrid() {
		try {
			scanner = new Scanner(new File(JOptionPane.showInputDialog(null,"Enter the filename for save file:", "Map Editor Save", JOptionPane.QUESTION_MESSAGE)));
		} catch (HeadlessException e1) {
			return null;
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "That file couldn't be found.", "File Load Error.", JOptionPane.ERROR_MESSAGE);
			System.out.println("File Load error.");
			return null;
		} catch (NullPointerException e2) {
			return null;
		}

		String[] header = scanner.nextLine().split(",");

		int width = Integer.parseInt(header[0]);
		int height = Integer.parseInt(header[1]);

		Grid grid = new Grid(width,height);

		for(int i = height-1; i >= 0; i--) {
			String row = scanner.nextLine();
			for(int j = 0; j < width; j++) {
				char c = row.charAt(j);
				if(!(c == 'T' || c == 'W' || c == ' ')) {
					j--;
				} else {
					if(c == 'T' || c == 'W') {
						grid.setCell(String.valueOf(c), j, i);
					}
				}
			}
		}

		scanner.close();
		return grid;
	}
}
