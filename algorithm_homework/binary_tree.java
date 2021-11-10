package algorithm_homework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
				node.left = mkNode(value, node.depth);
			}
		} else if (value > node.value) {
			if (node.right != null) {
				insert(node.right, value);
			} else {
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
