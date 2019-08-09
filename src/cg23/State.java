package cg23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class State implements Comparable<State> {
	private Grid grid;
	private HashMap<String,Robot> robots;
	private double weight;
	private State cameFrom;
	private State goal;
	private String heuristic;

	public State(Grid grid, HashMap<String,Robot> robots, double weight, State cameFrom, State goal, String heuristic) {
		this.grid = grid;
		this.robots = robots;
		this.weight = weight;
		this.cameFrom = cameFrom;
		this.goal = goal;
		this.heuristic = heuristic;
	}

	public ArrayList<State> neighbours() {
		HashMap<Robot, ArrayList<Location>> adjacent = new HashMap<Robot, ArrayList<Location>>();

		// get all possible next steps
		for(Robot r : robots.values()) {
			adjacent.put(r, grid.adjacentTo(r.location()));
		}

		return getStates(adjacent);
	}

	private ArrayList<State> getStates(HashMap<Robot, ArrayList<Location>> adjacent) {
		int a_size = adjacent.size();

		if(a_size > 2 || a_size < 1) {
			System.err.println("Algorithm does not support " + a_size + " robots.");
			return null;
		}

		ArrayList<State> states = new ArrayList<State>();

		if(a_size == 1) {
           ArrayList<Location> r1 = adjacent.get(robots.get("r1"));

           for(Location l : r1) {
                   Location goalLoc = goal.robots().get("r1").location();
                   double weight = this.weightFromPath() + calcHeuristic(l, goalLoc) + grid.cost(l);
                   HashMap<String, Robot> r = new HashMap<String, Robot>();
                   Robot rob1 = new Robot("r1", l);
                   r.put(rob1.name(), rob1);
                   State s = new State(grid, r, weight, this, goal, this.heuristic);
                   states.add(s);
           }

		} else {
			// return combination of all possible next states.
			ArrayList<Location> r1 = adjacent.get(robots.get("r1"));
			ArrayList<Location> r2 = adjacent.get(robots.get("r2"));
			r1.add(robots.get("r1").location());
			r2.add(robots.get("r2").location());

			// Nested loop over adjacent states of robot1 & robot 2
			for(Location l1 : r1) {
				for(Location l2 : r2) {
					Location goal1 = goal.robots().get("r1").location();
					Location goal2 = goal.robots().get("r2").location();

					// Get cost of this move, staying still at goal doesnt cost.
					Location last1 = goal.robots().get("r1").location();
					Location last2 = goal.robots().get("r2").location();

					double gridCost1 = (last1.equals(goal1)) ? 0.0 : grid.cost(l1);
					double gridCost2 = (last2.equals(goal2)) ? 0.0 : grid.cost(l2);

					// heuristic cost (h)
					double heuristic = manhattan(l1, goal1) + manhattan(l2, goal2);

					// Backward cost.
					double backwardWeight = this.weightFromPath();

					// full weight of this adjacent state
					double weight = heuristic + gridCost1 + gridCost2 + backwardWeight;

					// Initialise adjacent state
					HashMap<String, Robot> robots = new HashMap<String, Robot>();
	                Robot robot1 = new Robot("r1", l1);
	                Robot robot2 = new Robot("r2", l2);
	                robots.put(robot1.name(), robot1);
	                robots.put(robot2.name(), robot2);

	                State s = new State(grid, robots, weight, this, goal, this.heuristic);

	                // Add state to possible adjacent states, if it is valid
	                if(valid_state(s)) states.add(s);
				}
			}
		}

		return states;
	}

	private Double calcHeuristic(Location l, Location goalLoc) {
		switch(heuristic) {
			case "manhattan": return manhattan(l, goalLoc);
			case "euclidean": return euclidean(l,goalLoc);
			case "none": return 0.0;
		}
		System.out.println("Heuristic not defined, defaulting to manhattan");
		return manhattan(l, goalLoc);
	}

	// Checks whether a state is allowed
	private boolean valid_state(State s) {
		Location r1 = s.robots().get("r1").location();
		Location r2 = s.robots().get("r2").location();

		// if robots are on top of each other, not valid
		if(r1.equals(r2)) return false;

		Location last_r1 = s.cameFrom.robots().get("r1").location();
		Location last_r2 = s.cameFrom.robots().get("r2").location();

		// If robot 'switch' positions, not valid (head on collision)
		if(r1.equals(last_r2) && r2.equals(last_r1)) return false;

		return true;
	}

	// Manhattan heuristic
	private Double manhattan(Location goal, Location location) {
		return (double) (Math.abs(goal.x - location.x) + Math.abs(goal.y - location.y));
	}

	// Euclidean distance squared heuristic
	private Double euclidean(Location l, Location g) {
		double x1 = l.x;
		double x2 = g.x;
		double y1 = l.y;
		double y2 = g.y;
		double xsq = (x1-x2) * (x1-x2);
		double ysq = (y1-y2) * (y1-y2);

		return Math.sqrt((xsq + ysq));
	}

	public Double weightFromPath() {
		double weight = 0.0;

		// for each robot
		for(Entry<String,ArrayList<Location>> paths : this.path().entrySet()) {

			Iterator<Location> path = paths.getValue().iterator();
			Location goalLoc = this.goal.robots().get(paths.getKey()).location();

			// Skip first node, as cost to first state is 0.
			Location last = path.next();

			while(path.hasNext()) {
				Location current = path.next();
				// Staying still at goal doesnt cost
				if(!(last.equals(goalLoc))) weight += grid.cost(current);

				last = current;
			}
		}

		return weight;
	}

	public HashMap<String,ArrayList<Location>> path() {
		HashMap<String, ArrayList<Location>> path = new HashMap<String, ArrayList<Location>>();
		State current = this;
		for(String r : current.robots().keySet()) {
			path.put(r, new ArrayList<Location>());
		}

		while(true) {
			for(Robot r : current.robots().values()) {
				ArrayList<Location> currentPath = path.get(r.name());
				currentPath.add(r.location());
				path.put(r.name(), currentPath);
			}
			if(current.cameFrom != null) {
				current = current.cameFrom;
			} else {
				break;
			}
		}

		// For each path in hashmap, reverse.
		for(ArrayList<Location> l : path.values()) {
			Collections.reverse(l);
		}

		return path;
	}

	public boolean equals(State s) {
		return (robots.toString()).equals(s.robots().toString());
	}

	public HashMap<String,Robot> robots() {
		return robots;
	}

	@Override
	public int compareTo(State o) {
		return Double.valueOf(this.weight).compareTo(o.weight);
	}

	public String pathString() {
		String path = new String();

		HashMap<String, ArrayList<Location>> paths = this.path();

		// for each robot
		for(Entry<String, ArrayList<Location>> entry : paths.entrySet()) {
			path = path + entry.getKey() + ":";

			// for each location on path of robot
			for(Location l : entry.getValue()) {
				path = path + " " + l.toString();
			}

			path = path + "\n";
		}

		return path;
	}

	public String heuristic() {
		return heuristic;
	}
}
