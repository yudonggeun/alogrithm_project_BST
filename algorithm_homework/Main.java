package algorithm_homework;
import java.util.*;

public class Main {
	static final int testCaseCount = 1;
	static ArrayList<Integer> testCase;
	static FactoryTestCase caseMaker;
	static Tree[] treeSet;
	
	public static void main(String args[]) {
		
		caseMaker = new FactoryTestCase();
		treeSet = new Tree[5];
		
		for(int i = 0; i< testCaseCount; i++) {
			testCase = caseMaker.mkTestCase(1000);//인자는 테스트 캐이스 데이터 값들의 수
			
		}
		
		/*
		binary_tree tree = new binary_tree();
		System.out.println("Binary Tree Example");
		System.out.println("Building tree with root value " + 5);
		tree.insert(5);
		tree.insert(2);
		tree.insert(4);
		tree.insert(8);
		tree.insert(6);
		tree.insert(7);
		tree.insert(3);
		tree.insert(9);
		System.out.println("Traversing tree in order");
		tree.traverseInOrder();
		System.out.println("\nheight : " + tree.getHeight());
		 */
		//테스트 케이스 생성
		
		
		
	}

}
