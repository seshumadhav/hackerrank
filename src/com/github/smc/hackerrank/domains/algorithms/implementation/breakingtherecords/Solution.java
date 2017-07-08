package com.github.smc.hackerrank.domains.algorithms.implementation.breakingtherecords;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * https://www.hackerrank.com/challenges/breaking-best-and-worst-records
 * 
 * @author seshu
 */
public class Solution {

    static int[] getRecord(int[] s){
    	if (s.length == 1) {
    		return new int[] {0, 0};
    	}
    	
        int lowest = s[0];
        int numLowestRecordBreaks = 0;
        
        int highest = s[0];
        int numHighestRecordBreaks = 0;
        
        for (int i = 1; i < s.length; i++) {
        	if (s[i] < lowest) {
        		lowest = s[i];
        		numLowestRecordBreaks++;
        	}
        	
        	if (s[i] > highest) {
        		highest = s[i];
        		numHighestRecordBreaks++;
        	}
        }
    	
    	return new int[] {numHighestRecordBreaks, numLowestRecordBreaks};
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] s = new int[n];
        for(int s_i=0; s_i < n; s_i++){
            s[s_i] = in.nextInt();
        }
        int[] result = getRecord(s);
        String separator = "", delimiter = " ";
        for (Integer val : result) {
            System.out.print(separator + val);
            separator = delimiter;
        }
        System.out.println("");
    }
}