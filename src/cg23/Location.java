package cg23;

public class Location {
	int x;
	int y;

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public boolean equals(Location l) {
		return ((this.x) == (l.x)) && ((this.y) == (l.y));
	}
}
