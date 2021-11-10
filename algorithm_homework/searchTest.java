package algorithm_homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class searchTest {
	Tree[] trees;
	ArrayList<Integer> testCase;
	ArrayList<searchTimeRecord> records;
	long[] timeAverage;

	searchTest() {
		trees = new Tree[5];

		trees[0] = new binary_tree();
		trees[1] = new Treaps();
		trees[2] = new AVL_tree();
		trees[3] = new Splay_tree();
		trees[4] = new RedBlack_tree();

		testCase = null;

		records = new ArrayList<searchTimeRecord>();

		timeAverage = new long[5];
		Arrays.fill(timeAverage, 0);
		
	}

	public void setTestCase(ArrayList<Integer> testCase) {
		this.testCase = (ArrayList<Integer>) testCase.clone();
	}

	public void adaptTestCase() {
		if (this.testCase == null)
			return;
		for (Tree t : trees) {
			t.removeAll();
			t.insertArray(testCase);
		}
	}

	public void makeWorstCase() {
		Collections.sort(testCase);
	}

	public searchTimeRecord test() {
		if (testCase == null)
			return null;

		searchTimeRecord records = new searchTimeRecord(trees, testCase.size());

		for (int i = 0; i < trees.length; i++) {
			Tree t = trees[i];
			long time = t.searchAll(testCase);
			records.setRecord(time, i);
			addRecord(records);

			System.out.println(String.format("%15s, size(%d) = %3dms", t.getName(), t.getSize(), time));
		}

		return records;
	}

	public void addRecord(searchTimeRecord record) {
		records.add(record);
	}
	
	public void calAV() {
		Arrays.fill(timeAverage, 0);
		for(searchTimeRecord testResult : records) {
			for(int i = 0; i< testResult.treeNames.length; i++) {
				timeAverage[i] += testResult.record[i];
			}
		}
		for(int i = 0; i < timeAverage.length; i++) {
			timeAverage[i] /= records.size();
		}
	}
	
	public void printAV() {
		calAV();
		for(int i = 0; i< trees.length; i++) {
			System.out.println(trees[i].getName() + " average : " + timeAverage[i]);
		}
	}

}