package com.github.smc.hackerrank.domains.algorithms.search.hackerlandradiotransmitters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/hackerland-radio-transmitters
 *
 * @author seshu
 */
public class Solution2 {
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] x = new int[n];
        for(int x_i=0; x_i < n; x_i++){
            x[x_i] = in.nextInt();
        }
        
        getTransLocations(x, k);
    }

	private static void getTransLocations(int[] x, int k) {
		Arrays.sort(x);
		List<Integer> xList = toList(x);
		int maxOfX = x[x.length - 1];
		int capacity = maxOfX + 1;

		boolean[] house = new boolean[capacity];
		boolean[] covered = new boolean[capacity];
		boolean[] tower = new boolean[capacity];
		
		for (int i = 1; i <= maxOfX; i++) {
			house[i] = xList.contains(i);
			covered[i] = false;
			tower[i] = false;
		}
		
		for (int i = 1; i <= maxOfX; i++) {
			if (house[i] == false || covered[i] == true) {
				continue;
			}
			
			int next = i+1;
			while (next <= maxOfX && house[next] == false && covered[next] == false) next++;
			
			if (next - i > k || next >= maxOfX) {
				tower[i] = true;
				cover(i, k, house, covered);
			} else {
				tower[next] = true;
				cover(next, k, house, covered);
				i = next + k;	
			}
		}
		
		int numTowers = 0;
		String s = "";
		for(int i = 1; i <= maxOfX; i++) {
			if (house[i] && tower[i]) {
				numTowers++;
				s = s + i + " ";
			}
		}
		
//		System.out.println("NumTowers: " + numTowers + "\nTowers are at: " + s);
		System.out.println(numTowers);
	}
	
	private static void cover(int position, int k, boolean[] house, boolean[] covered) {
		for (int i = position - k; i <= position + k && i < house.length; i++) {
			if (house[i] && !covered[i]) {
				covered[i] = true;
			}
		}
	}
	
	private static List<Integer> toList(int[] array) {
		List<Integer> list = new ArrayList<>();
		
		for (int i : array) {
			list.add(i);
		}
		
		return list;
	}

}
