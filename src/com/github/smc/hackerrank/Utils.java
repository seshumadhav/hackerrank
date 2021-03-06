package com.github.smc.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 *
 * @author seshu
 */
public class Utils {

	private static final <T> String printList(List<T> list) {
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
		List<Integer> sublist = new ArrayList<Integer>() {
			{
				for (int i : child)
					add(i);
			}
		};
		List<Integer> list = new ArrayList<Integer>() {
			{
				for (int i : parent)
					add(i);
			}
		};
		return Collections.indexOfSubList(list, sublist) != -1;
	}

	private static List<Integer> toList(int[] array) {
		List<Integer> list = new ArrayList<>();

		for (int i : array) {
			list.add(i);
		}

		return list;
	}

	private static <T> List<T> removeDupes(List<T> list) {
		Set<T> set = new LinkedHashSet<T>();// A Linked hash set
		// prevents the adding order of the elements
		for (T item : list) {
			set.add(item);
		}
		return new ArrayList<T>(set);
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

	private static int hashCodeImpl() {
		// @Override
		// public int hashCode() {
		// int result = 17;
		// result = 31 * result + name.hashCode();
		// result = 31 * result + age;
		// result = 31 * result + passport.hashCode();
		// return result;
		// }
		return -1;
	}

	private static int hashCodeOf(int[] array) {
		int result = 17;
		
		for (int item : array) {
			result = 31*result + item;
		}
		
		return result; 
	}
}
