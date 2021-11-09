package algorithm_homework;

import java.util.*;

public class FactoryTestCase {

	Random rd;

	FactoryTestCase() {
		rd = new Random();
	}

	public ArrayList<Integer> mkTestCase(int testCaseSize) {
		HashSet<Integer> hash = new HashSet<Integer>(testCaseSize);
		ArrayList<Integer> arraylist = new ArrayList<Integer>(testCaseSize);
		for (int i = 0; i < testCaseSize; i++) {
			Integer number = rd.nextInt();

			if (hash.contains(number)) {
				i--;
				continue;
			}
			arraylist.add(number);
			hash.add(number);
		}
		return arraylist;
	}

	public static void main(String[] args) {
		FactoryTestCase ft = new FactoryTestCase();
		for(int x : ft.mkTestCase(5)) {
			System.out.println(x);
		}
	}

}
