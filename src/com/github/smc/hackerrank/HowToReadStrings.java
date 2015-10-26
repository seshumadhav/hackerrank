/**
 *
 */
package com.github.smc.hackerrank;

import java.util.Scanner;

/**
 * Sample Input 07:05:45PM
 *
 * @author seshu
 */
public class HowToReadStrings {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String time = in.next();
    boolean isPM = time.contains("PM");

    if (time.contains("AM") || time.contains("PM")) {
      time = time.substring(0, time.length() - 2);
    }
    String[] tokens = time.split(":");
    String rest = ":" + tokens[1] + ":" + tokens[2];

    String hour = tokens[0];
    Integer hourI = Integer.valueOf(hour);

    hourI = hourI != 12 && isPM ? hourI + 12 : hourI == 12 && !isPM ? 0 : hourI;
    String hourModified = Integer.toString(hourI);
    hourModified = hourModified.length() == 1 ? "0" + hourModified
        : hourModified;

    System.out.println(hourModified + rest);
  }

}
