package algorithm_homework;

import java.util.ArrayList;

interface Tree {
	
	public void insert(int data);

	public long search(Integer target);
	
	public long searchAll(ArrayList<Integer> testcase);

	public void removeAll();

	public void insertArray(ArrayList<Integer> array);
	
	public int getSize();
	
	public String getName();
	
}