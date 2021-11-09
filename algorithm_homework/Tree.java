package algorithm_homework;

import java.util.ArrayList;

interface Tree {
	public void insert(int data);

	public boolean search(Integer target);

	public void removeAll();

	public void insertArray(ArrayList<Integer> array);
}