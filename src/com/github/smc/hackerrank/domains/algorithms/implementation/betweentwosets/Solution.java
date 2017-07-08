package com.github.smc.hackerrank.domains.algorithms.implementation.betweentwosets;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * https://www.hackerrank.com/challenges/between-two-sets
 * 
 * @author seshu
 */
public class Solution {

	static int getTotalX(int[] a, int[] b) {
		int aMax = getMax(a);
		int bMax = getMax(b);
		int max = aMax > bMax ? aMax : bMax;
		
		int totalSuch = 0;
		for (int number = 1; number <= max; number++) {
			totalSuch += worksWithA(number, a) && worksWithB(number, b) ? 1 : 0;
		}
		
		return totalSuch;
	}
	
	static boolean worksWithA(int x, int[] a) {
		for (int i = 0; i < a.length; i++) {
			if (x % a[i] != 0) {
				return false;
			}
		}
		
		return true;
	}
	
	static boolean worksWithB(int x, int[] b) {
		for (int i = 0; i < b.length; i++) {
			if (b[i] % x != 0) {
				return false;
			}
		}
		
		return true;
	}
	
	static int getMax(int[] array) {
		int index = 0;
		int max = array[index++];
		
		while (index < array.length) {
			if (max > array[index]) {
				max = array[index];
			}
			
			index++;
		}
		
		return max;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[] a = new int[n];
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
		}
		int[] b = new int[m];
		for (int b_i = 0; b_i < m; b_i++) {
			b[b_i] = in.nextInt();
		}
		int total = getTotalX(a, b);
		System.out.println(total);
	}
}
