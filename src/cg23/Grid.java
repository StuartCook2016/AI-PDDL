package cg23;

import java.util.ArrayList;

public class Grid implements Cloneable {
	private int width;
	private int height;
	private String[][] grid;
 	public Grid(int x, int y) {
		this.width = x;
		this.height = y;
		createGrid(width,height);
	}

 	private void createGrid(int x, int y) {
 		grid = new String[y][x];
 		for(int i = 0; i < height; i++) {
 			for(int j = 0; j < width; j++) {
 				grid[i][j] = " ";
 			}
 		}
 	}

 	public void setCell(String type, int x, int y) {
 		grid[y][x] = type;
 	}

 	public String get(int x, int y) {
 		return (grid[y][x]);
 	}

 	public String get(Location location) {
		return get(location.x, location.y);
	}

 	public ArrayList<Location> adjacentTo(int x, int y) {
 		ArrayList<Location> neighbours = new ArrayList<Location>();

 		// up
    try { if(!(grid[y+1][x].equals("W"))) neighbours.add(new Location(x, y+1));
 		} catch (ArrayIndexOutOfBoundsException e) {}

 		// down
    try { if(!(grid[y-1][x].equals("W"))) neighbours.add(new Location(x, y-1));
 		} catch (ArrayIndexOutOfBoundsException e) {}

 		// left
    try { if(!(grid[y][x-1].equals("W"))) neighbours.add(new Location(x-1, y));
 		} catch (ArrayIndexOutOfBoundsException e) {}

 		// right
    try { if(!(grid[y][x+1].equals("W"))) neighbours.add(new Location(x+1, y));
 		} catch (ArrayIndexOutOfBoundsException e) {}

 		return neighbours;
 	}

 	public Double cost(Location to) {
 		String type = get(to);
		if(type.equals("W")) {
 			return null;
		} else if(type.equals("T")) {
			return 5.0;
		} else if(type.equals(" ")){
			return 1.0;
		} else {
			return null;
		}
	}

	public ArrayList<Location> adjacentTo(Location location) {
		return adjacentTo(location.x, location.y);
	}

	public int height() { return height; }

	public int width() { return width; }

	public String toString() {
		String grid = new String();
		for(int i = height-1; i>=0;i--) {
			for(int j = 0; j < width; j++) {
				grid = grid + this.get(j,i);
			}

			grid = grid + "\r\n";
		}
		return grid;
	}

	public void setCell(String string, Location location) {
		setCell(string, location.x, location.y);

	}

	public boolean contains(String selection) {
		for(int i = height-1; i>=0;i--) {
			for(int j = 0; j < width; j++) {
				if(this.get(j,i).equals(selection)) {
					return true;
				}
			}
		}
		return false;
	}

	public Location find(String s) {
		for(int i = height-1; i>=0;i--) {
			for(int j = 0; j < width; j++) {
				if(this.get(j,i).equals(s)) {
					return new Location(j,i);
				}
			}
		}
		return null;
	}

	public Grid clone() {
		Grid grid = new Grid(width,height);
		for(int i = height-1; i>=0;i--) {
			for(int j = 0; j < width; j++) {
				grid.setCell(this.get(j, i), j,i);
			}
		}
		return grid;
	}
}
