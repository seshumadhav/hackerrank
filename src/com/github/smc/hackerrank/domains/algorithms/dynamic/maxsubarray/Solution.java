package com.github.smc.hackerrank.domains.algorithms.dynamic.maxsubarray;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * https://www.hackerrank.com/challenges/maxsubarray/problem
 *
 * @author seshu
 */
public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numTestCases = in.nextInt();

		for (int i = 0; i < numTestCases; i++) {
			int n = in.nextInt();
			int[] array = new int[n];
			for (int j = 0; j < n; j++) {
				array[j] = in.nextInt();
			}

			int contig = maxContiguousSubArrayOf(array);
			int noncontig = maxNonContiguousSubArrayOf(array);

			System.out.println(contig + " " + noncontig);
		}

		in.close();
	}

	private static int maxContiguousSubArrayOf(int[] array) {
		int record = array[0];
		int curSet = array[0];

		for (int i = 1; i < array.length; i++) {
			int elem = array[i];

			curSet = curSet + elem;
			if (curSet < elem) {
				curSet = elem;
			}

			if (curSet > record) {
				record = curSet;
			}

		}

		return record;
	}

	private static int maxNonContiguousSubArrayOf(int[] array) {
		int sum = Integer.MIN_VALUE;

		int maxItem = Integer.MIN_VALUE;
		for (int item : array) {
			if (item >= 0) {
				if (sum == Integer.MIN_VALUE) {
					sum = 0;
				}
				
				sum += item;
			} else if (item > maxItem) {
				maxItem = item;
			}
		}

		if (sum == Integer.MIN_VALUE) {
			sum = maxItem;
		}

		return sum;
	}

}
