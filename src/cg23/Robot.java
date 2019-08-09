package cg23;

public class Robot {
	private Location location;
	private String name;

	public Robot(String name, Location location) {
		this.name = name;
		this.location = location;
	}
	
	public Robot(String name, int x, int y) {
		this.name = name;
		this.location = new Location(x,y);
	}

	public int x() {
		return location.x;
	}

	public int y() {
		return location.y;
	}
	
	public Location location() {
		return location;
	}
	
	public String name() {
		return name;
	}
	
	public String toString() {
		return (name + ": " + location.toString());
	}
}
