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
public class SolutionMaxNonContiguousArray {
	
	private static Map<String, Integer> precomputedMap = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numTestCases = in.nextInt();
		
		for (int i = 0; i < numTestCases; i++) {
			int n = in.nextInt();
			int[] array = new int[n];
			for (int j = 0; j < n; j++) {
				array[j] = in.nextInt();
			}
			maxSubArrayOf(array);
		}
		
		in.close();
	}

	private static void maxSubArrayOf(int[] array) {
		int maxSum = Integer.MIN_VALUE;
		
		for (int elem : array) {
			int[] minus = getArrayWithout(array, elem);
			// System.out.println(printArray(minus));
			int thisSum = subProblem(elem, minus);
			if (thisSum > maxSum) {
				maxSum = thisSum;
			}
		}
		
		System.out.println(maxSum);
	}
	
	private static int subProblem(Integer item, int[] subArray) {
		Problem subProblem = Problem.newProblem(item, subArray);
		String problemKey = subProblem.toString();
		
		if (precomputedMap.containsKey(problemKey)) {
			return precomputedMap.get(problemKey);
		}
		
		if (subArray.length == 1) {
			int sum = (item + subArray[0]) > item ? (item + subArray[0]) : item;
			precomputedMap.put(problemKey, sum);
			return sum;
		}

		int maxSum = Integer.MIN_VALUE;
		for (int elem : subArray) {
			int[] minus = getArrayWithout(subArray, elem);
			// System.out.println(printArray(minus));
			int thisSum = item + subProblem(elem, minus);
			if (thisSum > maxSum) {
				maxSum = thisSum;
			}
		}
		
		if (maxSum != Integer.MIN_VALUE) {
			precomputedMap.put(problemKey, maxSum);
		}
		
		return maxSum;
	}
	
	
	private static int[] getArrayWithout(int[] array, int x) {
		int[] result = new int[array.length - 1];
		
		int index = 0;
		for (int item : array) {
			if (item != x) {
				result[index++] = item;
			}
		}
		
		return result;
	}

	
	static final String printArray(int[] array) {
		StringBuilder sb = new StringBuilder();

		sb.append("[");
		boolean firstItemPassed = false;
		for (int item : array) {
			if (firstItemPassed) {
				sb.append(", ");
			}
			sb.append(item);
			firstItemPassed = true;
		}
		sb.append("]");

		return sb.toString();
	}
	
	public static int hashCodeOf(int[] array) {
		int result = 17;
		
		for (int item : array) {
			result = 31*result + item;
		}
		
		return result; 
	}
}

class Problem {
	int item;
	int[] array;
	
	public Problem(int item, int[] array) {
		this.item = item;
		this.array = array;
	}

	public static Problem newProblem(int item, int[] array) {
		return new Problem(item, array);
	}
	
	@Override
	public String toString() {
		Arrays.sort(array);
		return item + SolutionMaxNonContiguousArray.printArray(array);
	}
	
	@Override
	public boolean equals(Object other) {
		Problem that = (Problem) other;
		return that.item == item && Arrays.equals(array, that.array);
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		
		result += item * 31;
		result += SolutionMaxNonContiguousArray.hashCodeOf(array);
		
		return result;
	}
}
