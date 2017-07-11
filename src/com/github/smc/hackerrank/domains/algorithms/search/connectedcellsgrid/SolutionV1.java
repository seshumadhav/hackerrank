package com.github.smc.hackerrank.domains.algorithms.search.connectedcellsgrid;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * https://www.hackerrank.com/challenges/connected-cell-in-a-grid
 * 
 * @author seshumadhav@gmail.com
 */
public class SolutionV1 {
	
	private int[][] a;
	private int n;
	private int m;
	
	private Set<Cell> largest = new HashSet<>();

    public SolutionV1(int[][] given, int n, int m) {
		this.a = given;
		this.n = n;
		this.m = m;
	}
    
	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    	Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        
        int[][] a = new int[n][m];
        
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < m; j++) {
        		a[i][j] = in.nextInt();
        	}
        }
        
        SolutionV1 s = new SolutionV1(a, n, m);
        s.run();
    }
	
	private static void print(int[][] a, int n, int m) {
		for (int i = 0; i < n; i++) {
        	for (int j = 0; j < m; j++) {
        		System.out.print(a[i][j] + " ");
        	}
        	
        	System.out.println("");
        }
	}
	
	public void run() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				Set<Cell> largestIKnow = getArea(i, j, new HashSet<>());
				
				if (largestIKnow.size() > largest.size()) {
					largest.clear();
					largest.addAll(largestIKnow);
				}
			}
		}
		
		System.out.println(largest.size());
	}
	
	private Set<Cell> getArea(int i, int j, Set<Cell> path) {
		System.out.println("Calling getArea(" + i + ", " + j + ", Path: " + toString(path) + ")");
		if (!canThisCellBeProcessed(i, j, path)) {
			System.out.println("getArea(" + i + ", " + j + "): 0" + "\n");
			return new HashSet<>(); // TBD
		}		
		
		Cell thisCell = Cell.of(i, j);
		path.add(thisCell);
		
		Set<Cell> myArea = new HashSet<>();
		myArea.add(thisCell);
		
		Cell[] adjs = getAdjacentCells(i, j);
		for (Cell adj : adjs) {
			if (canThisCellBeProcessed(adj.i, adj.j, path)) {
				Set<Cell> newPath = new HashSet<>();
				newPath.addAll(path);
				myArea.addAll(getArea(adj.i, adj.j, newPath));
			}
		}
		
		System.out.println("getArea(" + i + ", " + j + "): " + myArea.size() + "\nContents of area: " + toString(myArea) + "\n");
		return myArea;
	}
	
	private boolean canThisCellBeProcessed(int i, int j, Set<Cell> path) {
		boolean isValid = isValid(i, j); 
		boolean isFilled = isFilled(i, j);
		boolean isInPath = path.contains(Cell.of(i, j));
		
		return isValid && isFilled && !isInPath;
	}
	
	private static Cell[] getAdjacentCells(int i, int j) {
		Cell[] cells = new Cell[8];
		
		cells[0] = Cell.of(i, j+1);
		cells[1] = Cell.of(i+1, j+1);
		cells[2] = Cell.of(i+1, j);
		cells[3] = Cell.of(i+1, j-1);
		cells[4] = Cell.of(i, j-1);
		cells[5] = Cell.of(i-1, j-1);
		cells[6] = Cell.of(i-1, j);
		cells[7] = Cell.of(i-1, j+1);
		
		return cells;
	}
	
	private String toString(Set<Cell> pairs) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (Cell pair : pairs) {
			sb.append(pair.toString());
			sb.append(", ");
		}
		sb.append("]");
		
		return sb.toString();
	}
	
    private boolean isValid(int i, int j) {
    	return i >= 0 && i < n && j >=0 && j < m;
    }
    
    private  boolean isFilled(int i, int j) {
    	return isValid(i, j) && a[i][j] == 1; 
    }
}
