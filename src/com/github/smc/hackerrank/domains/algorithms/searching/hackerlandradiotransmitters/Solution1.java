package com.github.smc.hackerrank.domains.algorithms.searching.hackerlandradiotransmitters;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * https://www.hackerrank.com/challenges/hackerland-radio-transmitters
 *
 * @author seshu
 */
public class Solution1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] x = new int[n];
        for(int x_i=0; x_i < n; x_i++){
            x[x_i] = in.nextInt();
        }
        
        
        List<Integer> transLocations = getTransLocations(x, k);
		// System.out.println("Locations: " + print(transLocations));
        // System.out.println("Total Transformers: " + transLocations.size());
        System.out.println(transLocations.size());
    }

	private static List<Integer> getTransLocations(int[] x, int k) {
		List<Integer> transLocations = new ArrayList<>();
		
		Arrays.sort(x);
		// System.out.println("Array after sorting: " + printArray(x));
		
		Map<Integer, Item> coverage = getCoverage(x, k);
		Map<Integer, Integer> indexOf = getIndex(coverage);

		int i = 0;
		while (i < x.length) {
			boolean canSkipThisHouse = true;
			
			int thisHouseIndex = i;
			int nextHouseIndex = i + 1;
			
			if (thisHouseIndex == x.length - 1) {
				transLocations.add(coverage.get(thisHouseIndex).data);
				break;
			}
			
			while (nextHouseIndex < x.length && canSkipThisHouse == true) {
				List<Integer> thisHouseSet = coverage.get(thisHouseIndex).set;
				List<Integer> nextHouseSet = coverage.get(nextHouseIndex).set;
				
				canSkipThisHouse = isSubsetOf(thisHouseSet, nextHouseSet);
				if (canSkipThisHouse) {
					// System.out.println("Skipped house (index: #" + thisHouseIndex + ", xi: " + coverage.get(thisHouseIndex).data + ")");
					thisHouseIndex = nextHouseIndex;
					nextHouseIndex = thisHouseIndex + 1;
				}
			}
			
			transLocations.add(coverage.get(thisHouseIndex).data);
			i = getNextHouseToStartSearch(coverage, thisHouseIndex, nextHouseIndex, indexOf);
		}
		
		return transLocations;
	}

	private static Map<Integer, Integer> getIndex(Map<Integer, Item> coverage) {
		Map<Integer, Integer> map = new HashMap<>();
		
		for (Entry<Integer, Item> entry : coverage.entrySet()) {
			map.put(entry.getValue().data, entry.getKey());
		}
		
		return map;
	}

	private static int getNextHouseToStartSearch(Map<Integer, Item> coverage, int thisHouseIndex, int nextHouseIndex, Map<Integer, Integer> index) {
		List<Integer> thisSet = coverage.get(thisHouseIndex).set;		
		int maxOfThisSet = thisSet.get(thisSet.size() - 1);
		int indexOfSetThatDoesNotContainMax = getIndexOfSetThatDoesNotContainMax(maxOfThisSet, thisHouseIndex + 1, coverage);
		return indexOfSetThatDoesNotContainMax;
	}

	private static int getIndexOfSetThatDoesNotContainMax(int max, int startIndex, Map<Integer, Item> coverage) {
		int index = 0;
		for (Entry<Integer, Item> entry : coverage.entrySet()) {
			if (entry.getKey() < startIndex) {
				continue;
			}
			
			List<Integer> set = entry.getValue().set;
			if (!set.contains(max)) {
				return entry.getKey();
			}
		}
		
		return coverage.size() - 1;
	}

	private static Map<Integer, Item> getCoverage(int[] x, int k) {
		Map<Integer, Item> houseCoverageMap = new HashMap<>();
		
		for (int index = 0; index < x.length; index++) {
			Item item = new Item();
			item.index = index;
			item.data = x[index];
			
			item.set.add(x[index]);
			
			int left = index -1;
			while (left >= 0 && distanceBetween(x[left], x[item.index]) <= k) {
				item.set.add(x[left]);
				left--;
			}
			
			int right = index + 1;
			while (right < x.length && distanceBetween(x[item.index], x[right]) <= k) {
				item.set.add(x[right]);
				right++;
			}
			
			Collections.sort(item.set);
			// System.out.println("Covered ones [" + index + ": [" + item.index + ", " + item.data + ", " + print(item.set) + "]]");
			
			houseCoverageMap.put(index, item);
		}
		
		return houseCoverageMap;
	}
	
	// Utils
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
	
	private static boolean isSubsetOf(int[] child, int[] parent) {
		List<Integer> sublist = new ArrayList<Integer>() {{ for (int i : child) add(i); }};
		List<Integer> list = new ArrayList<Integer>() {{ for (int i : parent) add(i); }};
		return Collections.indexOfSubList(list, sublist) != -1;
	}
	
	private static <T> boolean isSubsetOf(List<T> sublist, List<T> list) {
		return Collections.indexOfSubList(list, sublist) != -1;
	}
	
	private static int distanceBetween(int left, int right) {
		return right - left;
	}
}

class Item {
	int index;
	int data;
	List<Integer> set = new ArrayList<>();
}
