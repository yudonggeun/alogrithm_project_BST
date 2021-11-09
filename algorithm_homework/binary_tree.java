package algorithm_homework;

import java.util.ArrayList;

public class binary_tree implements Tree{
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
	private int nodeCount;

	public binary_tree() {
		this.root = null;
		this.height = 0;
		this.nodeCount = 0;
	}
	
	private Node mkNode(int value, int parentDepth) {
		Node node = new Node(value);
		this.nodeCount++;
		node.depth = parentDepth+1;
		this.height = Math.max(this.height, node.depth);
		return node;
	}
	
	public void insert(int data) {
		if (root == null) {
			root = mkNode(data, -1);
		} else {
			insert(root, data);
		}
	}
	private void insert(Node node, int value) {
		if (value < node.value) {
			if (node.left != null) {
				insert(node.left, value);
			} else {
				System.out.println(" Inserted " + value + " to left of " + node.value);
				node.left = mkNode(value, node.depth);
			}
		} else if (value > node.value) {
			if (node.right != null) {
				insert(node.right, value);
			} else {
				System.out.println("  Inserted " + value + " to right of " + node.value);
				node.right = mkNode(value, node.depth);
			}
		}
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
	
	public boolean search(Integer value) {
		if(root == null) return false;
		Node p = root;
		while(p != null) {
			if(p.value == value) {
				return true;
			} else if(p.value < value) {
				p = p.right;
			} else {
				p = p.left;
			}
		}
		return false;
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
