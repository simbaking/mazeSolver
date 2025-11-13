package mazeSolver;

import java.util.*;

public class MazeSolver {
	
	public static int[][] maze = {
			{0, 0, 0, 0, 0},
			{0, 1, 0, 3, 0},
			{0, 2, 0, 2, 0},
			{0, 2, 2, 2, 0},
			{0, 0, 0, 0, 0}
	};// 0 = wall or already explored, 1 = start, 2 = empty, 3 = end, 4 = path walked
	
	public static int[][] mazePath;
	public static int[][] solvedMazePath;
	
	public static int DOWN = 1;
	public static int RIGHT = 2;
	public static int UP = 3;
	public static int LEFT = 4;

	public static void main(String[] args) {

		
		mazePath = new int[maze.length][maze[0].length];
		
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[i].length; j++) {
				mazePath[i][j] = maze[i][j];
			}
		}
		
		int[] start = getStart(mazePath);
		ArrayList<int[]> path = new ArrayList<int[]>();
		path.add(start);
		
		solve(mazePath, start, path);

		for(int i = 0; i < maze.length; i++) {
			
			for(int j = 0; j < maze[i].length; j++) {
				
				System.out.print(maze[i][j]);
				
			}
			
			System.out.println();
			
		}
		
		for(int i = 0; i < maze.length; i++) {
			
			System.out.print("-");
			
		}
		
		System.out.println();

		for(int i = 0; i < mazePath.length; i++) {
			
			for(int j = 0; j < mazePath[i].length; j++) {
				
				System.out.print(mazePath[i][j]);
				
			}
			
			System.out.println();
			
		}
		

	}

	public static int numOfOpenEdges(int[][] maze, int xCoordinate, int yCoordinate) {
		
		int openEdges = 0;
		
		for(int i = -1; i < 2; i++) {
			
			for(int j = -1; j < 2; j++) {
				
				if(Math.abs(i) + Math.abs(j) == 1) {
					
					if(xCoordinate + i >= 0 && yCoordinate + j >= 0 &&
							xCoordinate + i <= maze.length && yCoordinate + j <= maze[xCoordinate + i].length) {
						
						if(maze[xCoordinate + i][yCoordinate + j] != 0) {
							openEdges ++;
						}
						
					}
					
				}
				
			}
			
		}
		
		return openEdges;
				
	}

	public static boolean edgeIsOpen(int[][] maze, int xCoordinate, int yCoordinate, int direction) {
				
		if(direction == DOWN) {
			if(maze[xCoordinate + 1][yCoordinate] != 0) {
				return true;
			}
		}
		
		else if(direction == RIGHT) {
			if(maze[xCoordinate][yCoordinate + 1] != 0) {
				return true;
			}
		}
		
		else if(direction == UP) {
			if(maze[xCoordinate - 1][yCoordinate] != 0) {
				return true;
			}
		}
		
		else if(direction == LEFT) {
			if(maze[xCoordinate][yCoordinate - 1] != 0) {
				return true;
			}
		}
		
		return false;

	}

	public static int[] getNextTo(int[][] maze, int xCoordinate, int yCoordinate, int direction) {
				
		int[] nextTo = new int[2];
		
		if(direction == DOWN) {
			
			if(maze[xCoordinate + 1][yCoordinate] != 0) {
				
				nextTo[0] = xCoordinate + 1;
				nextTo[1] = yCoordinate;
				return nextTo;
				
			}
			
		}
		
		else if(direction == RIGHT) {
			
			if(maze[xCoordinate][yCoordinate + 1] != 0) {

				nextTo[0] = xCoordinate;
				nextTo[1] = yCoordinate + 1;
				return nextTo;
				
			}
			
		}
		
		else if(direction == UP) {
			
			if(maze[xCoordinate - 1][yCoordinate] != 0) {

				nextTo[0] = xCoordinate - 1;
				nextTo[1] = yCoordinate;
				return nextTo;
				
			}
			
		}
		
		else if(direction == LEFT) {
			
			if(maze[xCoordinate][yCoordinate - 1] != 0) {

				nextTo[0] = xCoordinate;
				nextTo[1] = yCoordinate - 1;
				return nextTo;
				
			}
			
		}
		
		return nextTo;

	}
	
	public static int[] getStart(int[][] maze) {
		
		int[] start = new int[2];
		
		for(int i = 0; i < maze.length; i++) {
			
			for(int j = 0; j < maze[i].length; j++) {
				
				if(maze[i][j] == 1) {
					
					start[0] = i;
					start[1] = j;
					
					return start;
							
				}
				
			}
			
		}
		
		return start;
		
	}
	
	public static int[] getEnd(int[][] maze) {
		
		int[] start = new int[2];
		
		for(int i = 0; i < maze.length; i++) {
			
			for(int j = 0; j < maze[i].length; j++) {
				
				if(maze[i][j] == 3) {
					
					start[0] = i;
					start[1] = j;
					
					return start;
							
				}
				
			}
			
		}
		
		return start;
		
	}
	
	public static void solve(int[][] maze, int[] start, ArrayList<int[]> path) {
		
		if(start[0] == getEnd(maze)[0] && start[1] == getEnd(maze)[1]) {
			
			path.add(start);
			
		}
		
		else if(numOfOpenEdges(maze, start[0], start[1]) == 1 && path.get(0) != start){
			
			maze[start[0]][start[1]] = 0;
			int[] backPath = path.get(0/*path.size() - 1*/);
			path.remove(path.size() - 1);
			solve(maze, backPath, path);
			
		}

		else if(numOfOpenEdges(maze, start[0], start[1]) == 2 || path.get(0) == start) {
			
			for(int i = -1; i < 2; i++) {
				
				for(int j = -1; j < 2; j++) {
					
					if(Math.abs(i) + Math.abs(j) == 1) {
						
						if(start[0] + i >= 0 && start[1] + j >= 0 &&
								start[0] + i <= maze.length && start[1] + j <= maze[start[0] + i].length) {
							
							int[] pathForward = new int[2];
							pathForward[0] = start[0] + i;
							pathForward[1] = start[1] + j;
							
							if(maze[start[0] + i][start[1] + j] != 0 &&
									(!path.stream().anyMatch(p -> p[0] == pathForward[0] && p[1] == pathForward[1]))) {
								
								
								path.add(pathForward);
								if(maze[start[0]][start[1]] != 1) {
									maze[start[0]][start[1]] = 4;
								}
								
								solve(maze, pathForward, path);
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		
	}
	
}
