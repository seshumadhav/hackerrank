package com.github.smc.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utils {
	
	private static final <T> String print(List<T> list) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		boolean firstItemPassed = false;
		for (T t : list) {
			if (firstItemPassed) {
				sb.append(", ");
			}
			sb.append(t.toString());
			firstItemPassed = true;
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	private static final String printArray(int[] array) {
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
	
	private static final String printArray(char[] array) {
		StringBuilder sb = new StringBuilder();

		sb.append("["); 
		boolean firstItemPassed = false;
		for (char item : array) {
			if (firstItemPassed) {
				sb.append(", ");
			}
			sb.append(item);
			firstItemPassed = true;
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	private static final String printArray(long[] array) {
		StringBuilder sb = new StringBuilder();

		sb.append("["); 
		boolean firstItemPassed = false;
		for (long item : array) {
			if (firstItemPassed) {
				sb.append(", ");
			}
			
			sb.append(item);
			firstItemPassed = true; 
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	private static boolean areArraysSame(int[] array1, int[] array2) {
		return Arrays.equals(array1, array2);
	}
	
	private static boolean isSubsetOf(int[] child, int[] parent) {
		List<Integer> sublist = new ArrayList<Integer>() {{ for (int i : child) add(i); }};
		List<Integer> list = new ArrayList<Integer>() {{ for (int i : parent) add(i); }};
		return Collections.indexOfSubList(list, sublist) != -1;
	}
}
