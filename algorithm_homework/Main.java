package algorithm_homework;

import java.util.*;

public class Main {
	static final int testCaseCount = 1;
	static ArrayList<Integer> testCase;
	static FactoryTestCase caseMaker;
	static Tree[] treeSet;

	private static void createTree() {
		treeSet = new Tree[5];

		treeSet[0] = new binary_tree();
		treeSet[1] = new Treaps();
		treeSet[2] = new AVL_tree();
		treeSet[3] = new Splay_tree();
		treeSet[4] = new RedBlack_tree();
	}

	private static void initTree() {
		for (Tree t : treeSet) {
			t.removeAll();
		}
	}

	private static void CreateAndInsertTestCase() {
		// create test case
		testCase = caseMaker.mkTestCase(100000);// 인자는 테스트 캐이스 데이터 값들의 수
		// insert test case
		for (Tree t : treeSet) {
			t.insertArray(testCase);
		}
	}

	public static void main(String args[]) {

		caseMaker = new FactoryTestCase();
		createTree();

		for (int i = 0; i < testCaseCount; i++) {
			CreateAndInsertTestCase();
			System.out.println(testCase.get(0));
			for (Tree t : treeSet) {
				System.out.println("size is "+ t.getSize());
				System.out.println("ms = " + t.search(testCase.get(0)));
			}
		}

		/*
		 * binary_tree tree = new binary_tree();
		 * System.out.println("Binary Tree Example");
		 * System.out.println("Building tree with root value " + 5); tree.insert(5);
		 * tree.insert(2); tree.insert(4); tree.insert(8); tree.insert(6);
		 * tree.insert(7); tree.insert(3); tree.insert(9);
		 * System.out.println("Traversing tree in order"); tree.traverseInOrder();
		 * System.out.println("\nheight : " + tree.getHeight());
		 */
		// 테스트 케이스 생성

	}

}
