package com.github.smc.hackerrank.domains.algorithms.warmup.comparetriplets;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a0 = in.nextInt();
        int a1 = in.nextInt();
        int a2 = in.nextInt();
        int b0 = in.nextInt();
        int b1 = in.nextInt();
        int b2 = in.nextInt();
        
        awardPoints(a0, a1, a2, b0, b1, b2);
    }
    
    public static void awardPoints(int a0, int a1, int a2, int b0, int b1, int b2) {
    	Points points = new Points();
    	points.compareAndAward(a0, b0);
    	points.compareAndAward(a1, b1);
    	points.compareAndAward(a2, b2);
    	System.out.println(points.getPointsForAlice() + " " + points.getPointsForBob());
    }
}

class Points {
	
	int alice = 0;
	int bob = 0;
	
	public Points() {
	}
	
	public void addPointToAlice() {
		alice++;
	}
	
	public void addPointToBob() {
		bob++;
	}
	
	public int getPointsForAlice() {
		return alice;
	}
	
	public int getPointsForBob() {
		return bob;
	}
	
	public void compareAndAward(int ai, int bi) {
		if (ai > bi) {
			addPointToAlice();
		} else if (ai < bi) {
			addPointToBob();
		}
	}
	
}