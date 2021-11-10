package algorithm_homework;

import java.util.*;

public class Main {
	static ArrayList<Integer> testCase;
	static FactoryTestCase caseMaker;
	static int testCount[] = {20000,20000, 20000, 20000, 20000};//테스트 케이스 숫자 결정

	public static void main(String args[]) {

		caseMaker = new FactoryTestCase();
		searchTest random = new searchTest();
		searchTest worst = new searchTest();

		for (int i = 0; i < testCount.length; i++) {
			// create test case
			testCase = caseMaker.mkTestCase(testCount[i]);// 인자는 테스트 캐이스 데이터 값들의 수

			System.out.println(
					"\n========================================\n" + "case " + (i + 1) + "** size : " + testCount[i]);

			random.setTestCase(testCase);
			random.adaptTestCase();
			random.test();

			System.out.println("\n----------------------------------------");

			worst.setTestCase(testCase);
			worst.makeWorstCase();
			worst.adaptTestCase();
			worst.test();
		}
		random.printAV();
		worst.printAV();

	}

}
