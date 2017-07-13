package com.github.smc.hackerrank.domains.algorithms.search.knightlonchessboard;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

	public static int N;
	public static Pos dest;

	public static Map<String, Integer> distances = new HashMap<>();
	public static int minDistance = Integer.MAX_VALUE;
	public static LinkedList<Pos> path = new LinkedList<>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		in.close();

		dest = Pos.of(N - 1, N - 1);

		Pos src = Pos.of(0, 0);
		for (int i = 1; i < N; i++) {
			List<Pair> jumpRanges = pairsPerRow(i);
			// System.out.println("Jump Ranges: " + print(jumpRanges));
			List<Integer> answers = new ArrayList<>();

			for (Pair jumpRange : jumpRanges) {
				distances = new HashMap<>();
				minDistance = Integer.MAX_VALUE;
				LinkedList<Pos> journey = new LinkedList<>();

				String pad = "\t";

				// System.out.println("getNumHops(Journey: " + print(journey) + ", jumpRange: " + jumpRange + ")");
				Integer numHops = getNumHops(pad, journey, src, jumpRange);
				answers.add(numHops == Integer.MAX_VALUE ? -1 : numHops);
				pruneMap();
				// System.out.println("Map computed so far: \n" + distances);
			}

			// System.out.println("\n\nNum Hops: " + print(answers));
			System.out.println(print(answers));
		}
	}

	private static void pruneMap() {
		Set<Entry<String, Integer>> prunedSet = distances.entrySet().stream()
				.filter(entry -> entry.getValue() != Integer.MAX_VALUE).collect(Collectors.toSet());
		distances = new HashMap<>();
		for (Entry<String, Integer> e : prunedSet) {
			distances.put(e.getKey(), e.getValue());
		}
	}

	private static Integer getNumHops(String pad, LinkedList<Pos> journey, Pos src, Pair jumpRange) {
		// System.out.println("\n" + pad + "Attempting to execute getNumHops between " + src + " & " + dest + "[JumpRange: " + jumpRange + "]");
		if (src.equals(dest)) {
			// System.out.println(pad + "src and destination are same. Updating map with " + key(src, dest) + " as 0.");
			updateMap(src, dest, 0);
			return 0;
		}

		Integer distance = preComputedMap(src, dest);
		if (distance != Integer.MAX_VALUE) {
			// System.out.println(pad + "Precomputed distance b/w " + src + ", " + dest + " found: " + distance);
			return distance;
		}

		int a = jumpRange.a;
		int b = jumpRange.b;

		List<Pos> allHops = hop(src, a, b);
		List<Pos> hops = allHops.stream().filter(hop -> !journey.contains(hop)).collect(Collectors.toList());
		hops = removeDupes(hops);

		if (hops.isEmpty()) {
			// System.out.println(pad + "No new positions to jump. Returning INTEGER.MAX_VALUE");
			return Integer.MAX_VALUE; // no route
		}

		Integer minDistance = Integer.MAX_VALUE;
		Pos via = null;
		journey.add(src);
		// System.out.println(pad + "I will find shortest paths via each of " + print(hops));
		for (Pos nextPos : hops) {
			int distanceFromNextPos = preComputedMap(nextPos, dest);
			if (distanceFromNextPos == Integer.MAX_VALUE) {
				Integer numHopsFromNextPos = getNumHops(pad + "\t", journey, nextPos, jumpRange);
				distanceFromNextPos = numHopsFromNextPos == Integer.MAX_VALUE ? Integer.MAX_VALUE : numHopsFromNextPos;
			}

			// System.out.println(pad + "Distance from " + src + " via " + nextPos + " to " + dest + ": " + distanceFromNextPos);
			if (distanceFromNextPos < minDistance) {
				// System.out.println(pad + "Noted minDistance (" + src + " > " + nextPos + " > " + dest + ": " + distanceFromNextPos);
				via = nextPos;
				minDistance = distanceFromNextPos + 1;
			} else {
				// System.out.println(pad + "minDistance(" + src + " > " + nextPos + " > " + dest + ": " + distanceFromNextPos + ". Not smaller than best seen so far." + minDistance + "(Via " + via + "). Ignoring");
			}
		}

		// System.out.println(pad + "Updated in map that shortest distance between " + src + " & " + dest + " was: " + minDistance + " | Via: " + via + "\n---\n");
		updateMap(src, dest, minDistance == Integer.MAX_VALUE ? Integer.MAX_VALUE : minDistance);
		return minDistance;
	}

	private static Integer preComputedMap(Pos src, Pos dest) {
		String key = key(src, dest);
		return distances.containsKey(key) ? distances.get(key) : Integer.MAX_VALUE;
	}

	public static void updateMap(Pos src, Pos dest, int hops) {
		distances.put(key(src, dest), hops);
	}

	private static String key(Pos src, Pos dest) {
		return src.toString() + "-TO-" + dest.toString();
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

		// sb.append("[");
		boolean firstItemPassed = false;
		for (T t : list) {
			if (firstItemPassed) {
				sb.append(" ");
			}
			sb.append(t.toString());
			firstItemPassed = true;
		}
		// sb.append("]");

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
		return x >= 0 && y >= 0 && x < Solution.N && y < Solution.N;
	}

	public boolean outsideTheBoard() {
		return x >= Solution.N || y >= Solution.N;
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