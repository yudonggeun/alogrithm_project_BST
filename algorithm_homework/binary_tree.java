package algorithm_homework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class binary_tree implements Tree{
	
	private final String name = "BST";
	
	static class Node {
		int value;
		Node left, right;
		int depth;

		Node(int value) {
			this.value = value;
			left = null;
			right = null;
		}
	}

	private Node root;
	private int height;

	public binary_tree() {
		this.root = null;
		this.height = 0;
	}
	
	private Node mkNode(int value, int parentDepth) {
		Node node = new Node(value);
		node.depth = parentDepth+1;
		this.height = Math.max(this.height, node.depth);
		return node;
	}
	
	public void insert(int data) {
		root = insert(root, data);
	}
	private Node insert(Node node, int value) {
		if(node == null) {
			node = mkNode(value, -1);
			return node;
		}
		
		if (value < node.value) {
			node.left = insert(node.left, value);
		} 
		else if (value > node.value) {
			node.right = insert(node.right, value);
		}
		return node;
	}
	
	public void traverseInOrder() {
		if(root == null) {
			return;
		} 
		traverseInOrder(root);
	}
	private void traverseInOrder(Node node) {
		if (node != null) {
			traverseInOrder(node.left);
			System.out.print(" " + node.value);
			traverseInOrder(node.right);
		}
	}
	
	public long search(Integer value) {
		if (root == null)
			return -1;
		
		long time = System.currentTimeMillis();
		
		Node p = root;
		while (p != null) {
			if (p.value == value) {
				time = System.currentTimeMillis() - time;
				return time;
			} else if (p.value < value) {
				p = p.right;
			} else {
				p = p.left;
			}
		}
		return -1;
	}
	
	@Override
	public long searchAll(ArrayList<Integer> testcase) {
		long starttime = System.currentTimeMillis();
		long endtime = 0;
		for(Integer target : testcase) {
			search(target);
		}
		endtime = System.currentTimeMillis();
		return endtime - starttime;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getSize() {
		Queue<Node> queue = new LinkedList<Node>();
		int count = 0;
		if(root == null) 
			return 0;
	
		queue.add(root);
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			count++;
			if(node.left != null)
				queue.add(node.left);
			if(node.right != null)
				queue.add(node.right);
		}
		return count;
	}
	
	public void removeAll() {
		root = null;
	}
	
	public void insertArray(ArrayList<Integer> array) {
		for(int value : array) {
			insert(value);
		}
	}
	
	public int getHeight() {
		return height;
	}
}
