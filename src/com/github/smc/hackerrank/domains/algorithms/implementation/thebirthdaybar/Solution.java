package com.github.smc.hackerrank.domains.algorithms.implementation.thebirthdaybar;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * https://www.hackerrank.com/challenges/the-birthday-bar?h_r=next-challenge&h_v=zen
 *
 * @author seshu
 */
public class Solution {

    static int solve(int n, int[] s, int d, int m) {
    	int begin = 0;
    	int posibilities = 0;
    	
        while (begin < s.length) {
        	int end = begin + m -1;
        	
        	if (end > s.length) {
        		break;
        	}
        	
        	if (getSum(s, begin, end) == d) {
        		posibilities++;
        	}
        		
        	begin++;
        }
    	
    	return posibilities;
    }
    
    static int getSum(int[] s, int beginIndex, int endIndex) {
    	int sum = 0;
    	
    	for (int i = beginIndex; i <= endIndex && i < s.length; i++) {
    		sum += s[i];
    	}
    	
    	return sum;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] s = new int[n];
        for(int s_i=0; s_i < n; s_i++){
            s[s_i] = in.nextInt();
        }
        int d = in.nextInt();
        int m = in.nextInt();
        int result = solve(n, s, d, m);
        System.out.println(result);
    }
}
