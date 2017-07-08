package com.github.smc.hackerrank.domains.algorithms.search.pairs;

import java.io.*;
import java.util.*;

/**
 * https://www.hackerrank.com/challenges/pairs/problem
 * 
 *
 * @author seshu
 */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int k = in.nextInt();
        
        int[] array = readNIntegers(in, n);
        
        int numPairs = getNumPairs(array, k);
    }
    
    private static int getNumPairs(int[] array, int k) {
		Arrays.sort(array);
		
		for (int index = 0; index < array.length; index++) {
			
		}
    	
		return 0;
	}
    
    private int getPairsWhoseSumIsK(int begin, int end, int[] array, int k) {
    	int i = begin;
    	int j = begin + 1;
    	
    	int numPairs = 0;
    	
    	while (i <= end && j <= end) {
    		int mid = begin + end / 2;
    		if (array[i] + array[mid] == k) {
    			numPairs++;
    		}
    	}
    	
    }

	private static int[] readNIntegers(Scanner in, int n) {
  	  int[] array = new int[n];
  	  
  	  for (int i = 0; i < n; i++) {
  		  array[i] = in.nextInt();
  	  }
  	  
  	  return array;
    }
    
    
}
