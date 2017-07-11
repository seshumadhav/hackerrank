package com.github.smc.hackerrank.domains.algorithms.search.connectedcellsgrid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * https://www.hackerrank.com/challenges/connected-cell-in-a-grid
 * 
 * @author seshumadhav@gmail.com
 */
public class Solution {
	
	private int[][] a;
	private int n;
	private int m;
	
	private int largestSoFar = 0;

    public Solution(int[][] given, int n, int m) {
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
        
        Solution s = new Solution(a, n, m);
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
				int largestISawNow = getArea(i, j);
				if (largestISawNow > largestSoFar) {
					largestSoFar = largestISawNow;
				}
			}
		}
		
		System.out.println(largestSoFar);
	}
	
	private int getArea(int i, int j) {
		if (!isValid(i, j) || !isFilled(i, j)) {
			return 0;
		}

		List<Cell> addedCells = new ArrayList<>();
		Stack<Cell> stack = new Stack<>();
		Cell cell = Cell.of(i, j);

		stack.add(cell);
		addedCells.add(cell);

		int depth = 0;
		while (!stack.empty()) {
			Cell thisCell = stack.pop();
			depth++;

			Cell[] adjCells = getAdjacentCells(thisCell.i, thisCell.j);
			for (Cell c : adjCells) {
				if (isValid(c.i, c.j) && isFilled(c.i, c.j) && !addedCells.contains(c)) {
					stack.push(c);
					addedCells.add(c);
				}
			}
		}

		return depth;
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

class Cell {
	int i;
	int j;
	
	public Cell(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public static Cell of(int i, int j) {
		return new Cell(i, j);
	}

	@Override
	public boolean equals(Object b) {
		Cell that = (Cell) b;

		return that.i == this.i && that.j == this.j;
	}
	
	public static Cell none() {
		return new Cell(-2, -2);
	}
	
	public String toString() {
		return "(" + i + ", " + j + ")";
	}
	
	@Override
	public int hashCode() {
		return i + j;
	}
}
