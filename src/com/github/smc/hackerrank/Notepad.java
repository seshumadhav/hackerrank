package com.github.smc.hackerrank;

import java.util.HashSet;

public class Notepad {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public String firstDuplicate(final String str) {
		if (str == null || str.isEmpty()) {
			return null;
		}
		
		int i = 0;
		StringBuilder sb = new StringBuilder();
		HashSet<String> wordsReadSoFar = new HashSet<>();
		
		while (i < str.length()) {
			char ch = str.charAt(i);
			if (ch == ' ') {
				String word = sb.toString();
				sb.setLength(0); // Clear buffer
	
				if (wordsReadSoFar.contains(word)) {
					return word;
				} else {
					if (!word.isEmpty()) {
						wordsReadSoFar.add(word);
					}
				}
			} else {
				sb.append(ch);
			}
		}
		
		return null;
	}

}
