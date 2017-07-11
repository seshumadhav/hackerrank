package com.github.smc.hackerrank.domains.algorithms.search.maxsubarraysum;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;

import javafx.collections.transformation.SortedList;

/**
 * 
 * @author seshumadhav@gmail.com
 */
public class SolutionZero {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int numQueries = Integer.valueOf(in.nextLine());
		int n = in.nextInt();
		int m = in.nextInt();

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}

		// for(int i = 0; i < n; i++) {
		// System.out.println(arr[i]);
		// }
		List<List<Integer>> sets = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			generateSets(arr[i], subset(i+1, arr), sets);
		}
		
//		for (LinkedList<Integer> list : sets) {
//			
//		}
		
	}
	
	static int[] subset(int fromIndex, int[] arr) {
		int[] subset = new int[arr.length];
		int index = 0;
		
		for (int i = 0; i < arr.length; i++) {
			if (i >= fromIndex) {
				subset[index++] = arr[i];
			}
		}
		
		return subset;
	}
	
	static void generateSets(int number, int[] problem, List<List<Integer>> sets) {
		if (problem.length == 1) {
			sets.add(toList(problem[0]));
		}
		
		for (List<Integer> set : sets) {
			sets.add(add(number, set));
		}
		
		sets.add(toList(number));
	}
	
	static void addSetToSets(List<Integer> list, List<List<Integer>> sets) {
		if (!isPresent(toArray(list), toArrays(sets))) {
			sets.add(list);
		}
	}
	
	private static LinkedList<Integer> add(int number, List<Integer> givenList) {
		LinkedList<Integer> newList = new LinkedList<>();
		
		newList.add(number);
		newList.addAll(givenList);
		
		return newList;
	}
	
	private static List<Integer> toList(int number) {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(number);
		return list;
	}
	
	private static boolean isPresent(int number, int[] array) {
		for (int arrayitem : array) {
			if (arrayitem == number) {
				return true;
			}
		}
		
		return false;
	}
	
	private static boolean isPresent(int[] array, int[][] arrays) {
		for (int[] onearray : arrays) {
			if (areArraysSame(array, onearray)) {
				return true;
			}
		}
		
		return false;
	}
	
	private static boolean areArraysSame(int[] arr1, int [] arr2) {
		List<Integer> list1 = toList(arr1);Collections.sort(list1);
		List<Integer> list2 = toList(arr2);Collections.sort(list2);
		return list1.equals(list2);
	}
	
	private static List<Integer> toList(int[] array) {
		List<Integer> list = new ArrayList<>();
		
		for(int item : array) {
			list.add(item);
		}
		
		return list;
	}
	
	private static int[] toArray(List<Integer> list) {
		int[] arr = new int[list.size()];
		
		int index = 0;
		for (int number : list) {
			arr[index++] = number;
		}
		
		return arr;
	}

	private static int[][] toArrays(List<List<Integer>> lists) {
		int[][] arrays = new int[lists.size()][];
		int index = 0;
		
		for (List<Integer> list : lists) {
			arrays[index++] = toArray(list);
		}
		
		return arrays;
	}

}
