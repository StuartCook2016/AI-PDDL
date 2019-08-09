package aiRobotEditor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class EditorBoard extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener,ActionListener {
	private String Editor[][];
	private String ImageSelect[]={"W","T"};
	private String ImageCurrent = "W";
	private int Mx, My;
	private int indexInc = 0;
	private Image Wall;
	private Image Trap;
	private Image Empty;
	private FileWriter fileWriter;
	private final int HEIGHT;
	private final int WIDTH;

	public EditorBoard(int width, int height) {
		System.out.println(width + ","+ height);
		this.HEIGHT = height;
		this.WIDTH = width;
		Editor = new String[HEIGHT][WIDTH];

		createGrid();
		loadImages();

		setFocusable(true);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);

		KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		kfm.addKeyEventDispatcher(new KeyEventDispatcher() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED) {
					if (e.getKeyCode() == KeyEvent.VK_F1) {
						saveMap();
						return true;
					}
				}
				return false;
			}
		});
	}
	private void createGrid() {
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				Editor[y][x] = " ";
			}
		}
	}

	private void loadImages() {
		ImageIcon wall = new ImageIcon("images/wall.png");
		Wall = wall.getImage();

		ImageIcon trap = new ImageIcon("images/trap.png");
		Trap = trap.getImage();

		ImageIcon empty = new ImageIcon("images/empty.png");
		Empty = empty.getImage();
	}

	private void saveMap() {
		try {
			fileWriter = new FileWriter(JOptionPane.showInputDialog(null,"Enter the filename for save file:", "Map Editor Save", JOptionPane.QUESTION_MESSAGE));
			fileWriter.write(WIDTH+","+HEIGHT+"\r\n");

			for(int i = 0; i < HEIGHT; i++) {
				for(int j = 0; j < WIDTH; j++) {
					fileWriter.write(Editor[i][j]);
				}

				fileWriter.write("\r\n");
			}

			fileWriter.close();
		} catch(Exception e) {}
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
		}

		return img;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				String image = Editor[y][x];
				drawImg(g2d, imgFromString(image), x * 32, y * 32);
			}
		}

		drawImg(g2d, imgFromString(ImageCurrent), Mx, My);
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		int x = e.getX() / 32;
		int y = e.getY() / 32;

		if(e.getButton() == MouseEvent.BUTTON1) {
			Editor[y][x] = ImageCurrent;
		}
		else if (e.getButton() == MouseEvent.BUTTON3) {
			Editor[y][x] = " ";
		}

		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		Mx = e.getX() - 10;
		My = e.getY() - 10;

		repaint();
	}


	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int rotate = e.getWheelRotation();

		if(rotate < 0)
			indexInc = (indexInc > 0) ? indexInc - 1 : (ImageSelect.length - 1);
		else
			indexInc = (indexInc < (ImageSelect.length - 1)) ? indexInc + 1 : 0;

		ImageCurrent = ImageSelect[indexInc];

		repaint();
	}


	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {}
}
