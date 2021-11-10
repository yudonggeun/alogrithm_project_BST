package algorithm_homework;

import java.util.*;

public class Main {
	static ArrayList<Integer> testCase;
	static FactoryTestCase caseMaker;
	static int testCount[] = {20000,20000, 20000, 20000, 20000};//�׽�Ʈ ���̽� ���� ����

	public static void main(String args[]) {

		caseMaker = new FactoryTestCase();
		searchTest random = new searchTest();
		searchTest worst = new searchTest();

		for (int i = 0; i < testCount.length; i++) {
			// create test case
			testCase = caseMaker.mkTestCase(testCount[i]);// ���ڴ� �׽�Ʈ ĳ�̽� ������ ������ ��

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
