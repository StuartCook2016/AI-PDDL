package cg23;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Search {
	public static void main(String[] args) {
		Robot r1 = new Robot("r1", 0, 4);
		Robot goal1 = new Robot("r1", 5, 2);

		HashMap<String, Robot> robots = new HashMap<String, Robot>();
		robots.put(r1.name(), r1);

		HashMap<String, Robot> gRobots = new HashMap<String, Robot>();
		gRobots.put(goal1.name(), goal1);

		Grid g = exampleGrid();

		State goalState = new State(g, gRobots, 0, null, null, "manhattan");
		State startState = new State(g, robots, 0, null, goalState, "manhattan");

		State search = search(startState, goalState);

		System.out.println("=====================");
		System.out.println("A Star (single robot)");
		System.out.println(search.pathString() + search.weightFromPath());
		System.out.println("=====================");

		Robot r2 = new Robot("r1", 6, 0);
		Robot goal2 = new Robot("r1", 2, 4);

		robots.put(r2.name(), r2);
		gRobots.put(goal2.name(), goal2);

		goalState = new State(g, gRobots, 0, null, null, "manhattan");
		startState = new State(g, robots, 0, null, goalState, "manhattan");

		search = search(startState, goalState);
		System.out.println("=====================");
		System.out.println("A Star (single robot)");
		System.out.println(search.pathString() + search.weightFromPath());
		System.out.println("=====================");

		r2 = new Robot("r2", 6, 0);
		goal2 = new Robot("r2", 2, 4);

		robots.put(r2.name(), r2);
		gRobots.put(goal2.name(), goal2);

		robots.put(r1.name(), r1);
		gRobots.put(goal1.name(), goal1);

		goalState = new State(g, gRobots, 0, null, null, "manhattan");
		startState = new State(g, robots, 0, null, goalState, "manhattan");

		search = search(startState, goalState);
		System.out.println("=====================");
		System.out.println("A Star (multi robot)");
		System.out.println(search.pathString() + search.weightFromPath());
		System.out.println("=====================");

		search = aStar(new Location(0,4), new Location(5,2), new Location(6,0), new Location(2,4), g, "manhattan");

		System.out.println("=====================");
		System.out.println("A Star (multi robot2)");
		System.out.println(search.pathString() + search.weightFromPath());
		System.out.println("=====================");

	}

	public static State aStar(Location r1, Location g1, Grid g, String heuristic) {
		HashMap<String, Robot> robots = new HashMap<String, Robot>();
		HashMap<String, Robot> goal = new HashMap<String, Robot>();

		Robot robot1 = new Robot("r1", r1);
		Robot goal1 = new Robot("r1", g1);
		robots.put(robot1.name(), robot1);
		goal.put(goal1.name(), goal1);

		return aStar(robots, goal, g, heuristic);
	}

	public static State aStar(Location r1, Location g1, Location r2, Location g2, Grid g, String heuristic) {
		HashMap<String, Robot> robots = new HashMap<String, Robot>();
		HashMap<String, Robot> goal = new HashMap<String, Robot>();

		Robot robot1 = new Robot("r1", r1);
		Robot goal1 = new Robot("r1", g1);
		Robot robot2 = new Robot("r2", r2);
		Robot goal2 = new Robot("r2", g2);
		robots.put(robot1.name(), robot1);
		goal.put(goal1.name(), goal1);
		robots.put(robot2.name(), robot2);
		goal.put(goal2.name(), goal2);

		return aStar(robots, goal, g, heuristic);
	}

	private static State aStar(HashMap<String,Robot> start, HashMap<String,Robot> goal, Grid g, String heuristic) {
		State goalState = new State(g, goal, 0, null, null, heuristic);
		State startState = new State(g, start, 0, null, goalState, heuristic);
		return search(startState, goalState);
	}
	static int i = 0;
	private static State search(State startState, State goalState) {
		PriorityQueue<State> fringe = new PriorityQueue<State>();
		HashSet<String> explored = new HashSet<String>();
		fringe.add(startState);
		while(!fringe.isEmpty()) {
			i++;
			State state = fringe.poll();
			if(state.equals(goalState)) {
				System.out.println(i);
				return state;
			}
			// explore node
			if(!(explored.contains(state.robots().toString()))) {
				explored.add(state.robots().toString());
				ArrayList<State> neighbours = state.neighbours();
				for(State n : neighbours) {
					if(!(explored.contains(n.robots().toString()))) fringe.add(n);
				}
			}
		}
		return null;
	}

	public static Grid exampleGrid() {
		Grid grid = new Grid(8, 5);
		grid.setCell("W", 0, 2);
		grid.setCell("W", 2, 0);
		grid.setCell("W", 3, 0);
		grid.setCell("W", 3, 1);
		grid.setCell("W", 3, 3);
		grid.setCell("W", 4, 3);
		grid.setCell("W", 5, 1);
		grid.setCell("W", 7, 1);
		grid.setCell("T", 3, 2);

		return grid;
	}
}
