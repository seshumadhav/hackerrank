package com.github.smc.hackerrank.domains.algorithms.search.knightlonchessboard;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.smc.hackerrank.domains.algorithms.search.knightlonchessboard.Solution.N;

public class Solution {

	public static int N;
	public static Pos dest;

	public static Map<String, Integer> distances = new HashMap<>();
	public static int minDistance = Integer.MAX_VALUE;
	public static LinkedList<Pos> path = new LinkedList<>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		dest = Pos.of(N - 1, N - 1);

		List<Pair> pairs = pairs();
		System.out.println(print(pairs));

		for (int i = 1; i < N; i++) {
			distances = new HashMap<>();
			minDistance = Integer.MAX_VALUE;
			path = new LinkedList<>();

			List<Pair> jumpRanges = pairsPerRow(i);
			System.out.println(print(jumpRanges));

			Pos src = Pos.of(0, 0);

			List<Integer> answers = new ArrayList<>();
			for (Pair jumpRange : jumpRanges) {
				LinkedList<Pos> journey = new LinkedList<>();
				answers.add(getNumHops(journey, src, jumpRange));
			}
			System.out.println(print(answers));
		}
	}

	private static Integer getNumHops(LinkedList<Pos> journey, Pos src, Pair jumpRange) {
		if (src.equals(dest)) {
			updateMap(src, dest, 0);
			return 0;
		}

		Integer distance = preComputedMap(src, dest);
		if (distance != -1) {
			return distance;
		}

		int a = jumpRange.a;
		int b = jumpRange.b;

		List<Pos> allHops = hop(src, a, b);
		List<Pos> hops = allHops.stream().filter(hop -> !journey.contains(hop)).collect(Collectors.toList());
		hops = removeDupes(hops);

		if (hops.isEmpty()) {
			return -1;
		}

		minDistance = Integer.MAX_VALUE;
		journey.add(src);
		for (Pos nextPos : hops) {
			int distanceFromNextPos = preComputedMap(nextPos, dest);
			if (distanceFromNextPos == -1) {
				distanceFromNextPos = getNumHops(journey, nextPos, jumpRange) + 1;
			}

			if (distanceFromNextPos < minDistance) {
				minDistance = distanceFromNextPos;
			}
		}

		updateMap(src, dest, minDistance);
		return minDistance;
	}

	private static Integer preComputedMap(Pos src, Pos dest) {
		String key = key(src, dest);
		return distances.containsKey(key) ? distances.get(key) : -1;
	}

	public static void updateMap(Pos src, Pos dest, int hops) {
		distances.put(key(src, dest), hops);
	}

	private static String key(Pos src, Pos dest) {
		return src.toString() + "-" + dest.toString();
	}

	private static List<Pair> pairs() {
		List<Pair> jumpRanges = new ArrayList<>();
		for (int i = 1; i < N; i++) {
			jumpRanges.addAll(pairsPerRow(i));
		}

		return jumpRanges;
	}

	private static List<Pair> pairsPerRow(int i) {
		List<Pair> jumpRanges = new ArrayList<>();

		for (int j = 1; j < N; j++) {
			jumpRanges.add(Pair.of(i, j));
		}

		return jumpRanges;
	}

	private static List<Pos> hop(Pos current, int a, int b) {
		List<Pos> poss = new ArrayList<>();

		Pos p1 = Pos.of(current.x + a, current.y + b);
		Pos p2 = Pos.of(current.x + a, current.y - b);
		Pos p3 = Pos.of(current.x - a, current.y + b);
		Pos p4 = Pos.of(current.x - a, current.y - b);
		Pos p5 = Pos.of(current.x + b, current.y + a);
		Pos p6 = Pos.of(current.x + b, current.y - a);
		Pos p7 = Pos.of(current.x - b, current.y + a);
		Pos p8 = Pos.of(current.x - b, current.y - a);

		poss.add(p1);
		poss.add(p2);
		poss.add(p3);
		poss.add(p4);
		poss.add(p5);
		poss.add(p6);
		poss.add(p7);
		poss.add(p8);

		List<Pos> fPoss = poss.stream().filter(pos -> pos.isValid()).collect(Collectors.toList());
		return fPoss;
	}

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

	private static <T> List<T> removeDupes(List<T> list) {
		Set<T> set = new LinkedHashSet<T>();// A Linked hash set
		// prevents the adding order of the elements
		for (T item : list) {
			set.add(item);
		}
		return new ArrayList<T>(set);
	}

}

class Pair {
	int a, b;

	public Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}

	public static Pair of(int a, int b) {
		return new Pair(a, b);
	}

	public String toString() {
		return "(" + a + "," + b + ")";
	}

	@Override
	public boolean equals(Object other) {
		Pair otherPair = (Pair) other;
		return a == otherPair.a && b == otherPair.b;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + a;
		result = 31 * result + b;
		return result;
	}
}

class Pos {
	int x;
	int y;

	public boolean isValid() {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

	public boolean outsideTheBoard() {
		return x >= N || y >= N;
	}

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static Pos of(int x, int y) {
		return new Pos(x, y);
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}

	@Override
	public boolean equals(Object other) {
		Pos otherPos = (Pos) other;
		return x == otherPos.x && y == otherPos.y;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + x;
		result = 31 * result + y;
		return result;
	}
}