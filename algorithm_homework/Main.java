package algorithm_homework;

import java.util.*;

public class Main {
	static final int testCaseCount = 1;
	static ArrayList<Integer> testCase;
	static FactoryTestCase caseMaker;

	public static void main(String args[]) {

		caseMaker = new FactoryTestCase();
		searchTest mt = new searchTest();

		// create test case
		testCase = caseMaker.mkTestCase(10000000);// ���ڴ� �׽�Ʈ ĳ�̽� ������ ������ ��
		
		for (int i = 0; i < testCaseCount; i++) {
			mt.setTestCase(testCase);
			mt.test();
			
			System.out.println("----------------------------------------");
			
			mt.makeWorstCase();
			mt.initTree();
			mt.test();
		}

	}

}
