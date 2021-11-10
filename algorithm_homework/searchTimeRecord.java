package algorithm_homework;

public class searchTimeRecord {
	String[] treeNames;
	int testSize;
	long[] record;

	searchTimeRecord(Tree[] trees, int testSize) {
		treeNames = new String[trees.length];
		record = new long[trees.length];
		this.testSize = testSize;
		for (int i = 0; i < trees.length; i++) {
			treeNames[i] = trees[i].getName();
		}
	}

	public void setRecord(long time, int index) {
		if (index < 0 || index >= record.length)
			return;
		record[index] = time;
	}

	public void print() {
		for (int i = 0; i < treeNames.length; i++) {
			System.out.println("tree name is " + treeNames[i]);
			System.out.println("search time = " + record[i] + "ms\n");
		}
	}

}
