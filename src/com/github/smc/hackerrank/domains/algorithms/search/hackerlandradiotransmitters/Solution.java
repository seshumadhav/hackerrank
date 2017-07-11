package com.github.smc.hackerrank.domains.algorithms.search.hackerlandradiotransmitters;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/hackerland-radio-transmitters
 *
 * @author seshu
 */
public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int[] x = new int[n];
		for (int x_i = 0; x_i < n; x_i++) {
			x[x_i] = in.nextInt();
		}
		
		setupTowers(x, k);

	}

	private static void setupTowers(int[] x, int k) {
		int numTowers = 0;
		int tower = -1;
		int i = 0;

		Arrays.sort(x);
		while (i < x.length) {
			int current = i;
			int next = current + 1;
			
			if (outOfTower(x, tower, i, k)) {
				while (next < x.length && x[current] + k >= x[next]) next++;
				tower = next - 1; System.out.print(x[tower] + " ");
				i = next;
				
				numTowers++;
			} else {
				while (next < x.length && x[tower] + k >= x[next]) next++;
				i = next;
			}
		}
		
		System.out.println("\n" + numTowers);
	}
	
	private static boolean outOfTower(int[] x, int tower, int i, int k) {
		return tower == -1 ? true : x[tower] + k < x[i]; 
	}
}
