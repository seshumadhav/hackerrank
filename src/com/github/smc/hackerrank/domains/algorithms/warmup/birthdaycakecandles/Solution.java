package com.github.smc.hackerrank.domains.algorithms.warmup.birthdaycakecandles;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * Time Taken: x minutes
 * 
 * @author seshumadhav@gmail.com
 *
 */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int height[] = new int[n];
        for(int height_i=0; height_i < n; height_i++){
            height[height_i] = in.nextInt();
        }
        
        int maxHeight = Integer.MIN_VALUE;
        for (int hi : height) {
        	if (hi > maxHeight) {
        		maxHeight = hi;
        	}
        }
        
        int numCandlesToBlow = 0;
        for (int hi : height) {
        	if (hi == maxHeight) {
        		numCandlesToBlow++;
        	}
        }
        
        System.out.println(numCandlesToBlow);
    }
}
