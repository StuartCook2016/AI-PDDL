package aiRobotSearch;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import cg23.Grid;
import cg23.Location;
import cg23.Search;
import cg23.State;

public class SearchBoard extends JPanel implements ActionListener,MouseListener {
	private static final long serialVersionUID = 1L;
	private JFrame buttonFrame = new JFrame();
	private JPanel buttonPanel = new JPanel();
	private String[] search = { "A* Manhattan","A* Euclidean","Uniform Cost Search"};
	private JComboBox<String> searchList = new JComboBox<String>(search);
	private String searchType;
	private JButton runSearch = new JButton("Terminate");
	private JButton r1Button = new JButton("T800(r1)");
	private JButton r2Button = new JButton("T1000(r2)");
	private JButton g1Button = new JButton("Sarah Conner(g1)");
	private JButton g2Button = new JButton("John Conner(g2)");
	private Image Trap;
	private Image Wall;
	private Image Empty;
	private Image Robot1;
	private Image Robot2;
	private Image Goal1;
	private Image Goal2;
	private String selection;
	private final Grid grid;
	private Grid robotGrid;
	private final int HEIGHT;
	private final int WIDTH;

	// Alg Steeping
	private HashMap<String, ArrayList<Location>> paths;
	private Timer timer;
	private Location goal1;
	private Location goal2;
	private int STEP;
	private int MAX_STEPS;
	private int DELAY = 1500;

	private ActionListener stepAlg = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// Clean Grid
			robotGrid = grid.clone();
			System.out.println("=== STEP " + STEP + " ===");

			robotGrid.setCell("goal1", goal1);
			if(paths.containsKey("r2")) robotGrid.setCell("goal2", goal2);

			robotGrid.setCell("robot1", paths.get("r1").get(STEP));
			System.out.println("Move Robot 1 to " + paths.get("r1").get(STEP));

			if(paths.containsKey("r2")) {
				robotGrid.setCell("robot2", paths.get("r2").get(STEP));
				System.out.println("Move Robot 2 to " + paths.get("r2").get(STEP));
			}

			repaint();
			STEP++;
			if(STEP >= MAX_STEPS) {
				timer.stop();
				System.out.println("Animation Done.");
			}
		}
	};

	public SearchBoard(Grid grid) {
		this.HEIGHT = grid.height();
		this.WIDTH = grid.width();
		this.grid = grid;
		this.robotGrid = grid.clone();
		this.selection = null;
		searchList.setSelectedIndex(0);
		searchType = "manhattan";
		buttonFrame();
		addbutton();
		loadImages();
		addMouseListener(this);
		setFocusable(true);
	}

	private void buttonFrame() {
		buttonFrame.setSize(300, 200);
		buttonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buttonFrame.setVisible(true);
		buttonFrame.setResizable(false);
	}

	private void addbutton() {
		runSearch.setBounds(75, 135, 130, 30);
		runSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Run Search type: " + searchType);
				Location r1 = robotGrid.find("robot1");
				Location g1 = robotGrid.find("goal1");
				Location r2 = robotGrid.find("robot2");
				Location g2 = robotGrid.find("goal2");
				State path;

				if(r1 != null && g1 != null) {
					if(r2 != null & g2 != null) {
						System.out.println("Running Cooperative Algorithm");
						path = Search.aStar(r1, g1, r2, g2, grid, searchType);
						System.out.println(path.pathString());
					} else {
						System.out.println("Running single Algorithm");
						path = Search.aStar(r1, g1, grid, searchType);
						System.out.println(path.pathString());
					}
				} else {
					System.out.println("Couldn't run algorithm, Robot1/Goal1 null");
					return;
				}

				goal1 = g1;
				goal2 = g2;
				paths = path.path();

				STEP = 0;
				MAX_STEPS = paths.get("r1").size();
				timer = new Timer(DELAY, stepAlg);
				timer.start();
			}
    });

		searchList.setBounds(75, 104, 150, 30);
		searchList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> selectSearch = (JComboBox<String>) e.getSource();
				String searchName = (String) selectSearch.getSelectedItem();
				switch(searchName) {
					case "A* Manhattan":
						searchType = "manhattan";
						break;
					case "A* Euclidean":
						searchType = "euclidean";
						break;
					case "Uniform Cost Search":
						searchType = "none";
						break;
				}

				System.out.println(searchType);
			}
		});

		r1Button.setBounds(10, 1, 135, 50);
		r1Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selection = "robot1";
			}
		});

		r2Button.setBounds(147, 1, 135, 50);
		r2Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selection = "robot2";
			}
		});

		g1Button.setBounds(10, 52, 135, 50);
		g1Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selection = "goal1";
			}
		});

		g2Button.setBounds(147, 52, 135, 50);
		g2Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selection = "goal2";
			}
		});

		buttonPanel.setLayout(null);
		buttonFrame.add(buttonPanel);
		buttonPanel.add(r1Button);
		buttonPanel.add(r2Button);
		buttonPanel.add(g1Button);
		buttonPanel.add(g2Button);
		buttonPanel.add(searchList);
		buttonPanel.add(runSearch);
	}

	private void loadImages() {
		ImageIcon wall = new ImageIcon("images/wall.png");
		Wall = wall.getImage();

		ImageIcon trap = new ImageIcon("images/trap.png");
		Trap = trap.getImage();

		ImageIcon empty = new ImageIcon("images/empty.png");
		Empty = empty.getImage();

		ImageIcon robot1 = new ImageIcon("images/robot1.png");
		Robot1 = robot1.getImage();

		ImageIcon robot2 = new ImageIcon("images/robot2.png");
		Robot2 = robot2.getImage();

		ImageIcon goal1 = new ImageIcon("images/goal1.png");
		Goal1 = goal1.getImage();

		ImageIcon goal2 = new ImageIcon("images/goal2.png");
		Goal2 = goal2.getImage();
	}

	private void drawImg(Graphics2D g2d, Image img, int x, int y) {
		g2d.drawImage(img, x, y, null);
	}

	private Image imgFromString(String image) {
		Image img = null;
		switch(image) {
			case "W": 	img = Wall;
						break;
			case "T":	img = Trap;
						break;
			case " ":   img = Empty;
						break;
			case "robot1":  img = Robot1;
							break;
			case "robot2":  img = Robot2;
							break;
			case "goal1":	img = Goal1;
							break;
			case "goal2":	img = Goal2;
							break;
		}

		return img;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				String image = robotGrid.get(x,(HEIGHT-y-1));
				drawImg(g2d, imgFromString(image), x * 32, y * 32);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX() / 32;
		int y = HEIGHT - 1 - (e.getY() / 32);
		String originalCell = grid.get(x, y);

		if(e.getButton() == MouseEvent.BUTTON1) {
			if(originalCell.equals(" ") && !robotGrid.contains(selection)) {
				robotGrid.setCell(this.selection, x, y);
			}
		}

		if(e.getButton() == MouseEvent.BUTTON3) {
			robotGrid.setCell(" ", x, y);
		}

		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
