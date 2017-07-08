package com.github.smc.hackerrank.domains.algorithms.warmup.maxmin;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * @author seshumadhav@gmail.com
 */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int d = in.nextInt();
        int e = in.nextInt();
        
        List<Integer> input = new ArrayList<>();
        input.add(a);
        input.add(b);
        input.add(c);
        input.add(d);
        input.add(e);
        
        Collections.sort(input);
        
        int i = 0;
        long min = 0, max = 0;
        for (Integer l : input) {
			if (i == 0) {
				min += l;
			} else if (i == input.size() - 1) {
				max += l;
			} else {
				min += l;
				max += l;
			}
			
			i++;
		}
        
        System.out.println(min + " " + max);
    }    
}