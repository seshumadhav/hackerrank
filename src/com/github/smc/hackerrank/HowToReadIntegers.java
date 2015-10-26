/**
 *
 */
package com.github.smc.hackerrank;

import java.util.Scanner;

/**
 * Sample Input 9 6 2015 6 6 2015
 *
 * @author seshu
 */
public class HowToReadIntegers {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    int aDay = in.nextInt();
    int aMonth = in.nextInt();
    int aYear = in.nextInt();

    int eDay = in.nextInt();
    int eMonth = in.nextInt();
    int eYear = in.nextInt();

    int fine = 0;

    if (aYear > eYear) {
      fine = 10000;
    } else if (aYear == eYear && aMonth > eMonth) {
      fine = 500 * (aMonth - eMonth);
    } else if (aYear == eYear && aMonth == eMonth && aDay > eDay) {
      fine = 15 * (aDay - eDay);
    }

    System.out.println(fine);
  }

}
