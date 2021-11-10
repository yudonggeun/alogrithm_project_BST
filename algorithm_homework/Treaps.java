package algorithm_homework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Treaps implements Tree{

	private class Node {
		int data;
		int priority;
		Node left, right;

		// constructor
		Node(int data) {
			this.data = data;
			this.priority = new Random().nextInt(100);
			this.left = this.right = null;
		}
	}

	private Node root = null;

	private Node rotateLeft(Node root) {
		Node R = root.right;
		Node X = root.right.left;

		// rotate
		R.left = root;
		root.right = X;

		// set a new root
		return R;
	}

	private Node rotateRight(Node root) {
		Node L = root.left;
		Node Y = root.left.right;

		// rotate
		L.right = root;
		root.left = Y;

		// set a new root
		return L;
	}

	// Recursive function to insert a given key with a priority into treap
	public void insert(int data) {
		root = insert(root, data);
	}

	public Node insert(Node root, int data) {
		// base case
		if (root == null) {
			return new Node(data);
		}

		// if data is less than the root node, insert in the left subtree;
		// otherwise, insert in the right subtree
		if (data < root.data) {
			root.left = insert(root.left, data);

			// rotate right if heap property is violated
			if (root.left != null && root.left.priority > root.priority) {
				root = rotateRight(root);
			}
		} else {
			root.right = insert(root.right, data);

			// rotate left if heap property is violated
			if (root.right != null && root.right.priority > root.priority) {
				root = rotateLeft(root);
			}
		}

		return root;
	}
	
	public void insertArray(ArrayList<Integer> array) {
		for(int value : array) {
			insert(value);
		}
	}
	
	public long search(Integer value) {
		long time = System.currentTimeMillis();
		if(!searchNode(value)) {
			return -1;
		}
		return System.currentTimeMillis()-time;
	}
	
	public boolean searchNode(Integer key) {
		return searchNode(root, key);
	}
	
	// Recursive function to searchNode for a key in a given treap
	public boolean searchNode(Node root, int key) {
		// if the key is not present in the tree
		if (root == null) {
			return false;
		}

		// if the key is found
		if (root.data == key) {
			return true;
		}

		// if the key is less than the root node, searchNode in the left subtree
		if (key < root.data) {
			return searchNode(root.left, key);
		}

		// otherwise, searchNode in the right subtree
		return searchNode(root.right, key);
	}

	public void delete(int key) {
		root = delete(root, key);
	}

	// Recursive function to delete a key from a given treap
	public Node delete(Node root, int key) {
		// base case: the key is not found in the tree
		if (root == null) {
			return null;
		}

		// if the key is less than the root node, recur for the left subtree
		if (key < root.data) {
			root.left = delete(root.left, key);
		}

		// if the key is more than the root node, recur for the right subtree
		else if (key > root.data) {
			root.right = delete(root.right, key);
		}

		// if the key is found
		else {
			// Case 1: node to be deleted has no children (it is a leaf node)
			if (root.left == null && root.right == null) {
				// deallocate the memory and update root to null
				root = null;
			}

			// Case 2: node to be deleted has two children
			else if (root.left != null && root.right != null) {
				// if the left child has less priority than the right child
				if (root.left.priority < root.right.priority) {
					// call `rotateLeft()` on the root
					root = rotateLeft(root);

					// recursively delete the left child
					root.left = delete(root.left, key);
				} else {
					// call `rotateRight()` on the root
					root = rotateRight(root);

					// recursively delete the right child
					root.right = delete(root.right, key);
				}
			}

			// Case 3: node to be deleted has only one child
			else {
				// choose a child node
				Node child = (root.left != null) ? root.left : root.right;
				root = child;
			}
		}

		return root;
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

	public void printTreap(int space) {
		printTreap(root, space);
	}

	// Utility function to print two-dimensional view of a treap using
	// reverse inorder traversal
	public void printTreap(Node root, int space) {
		final int height = 10;

		// Base case
		if (root == null) {
			return;
		}

		// increase distance between levels
		space += height;

		// print the right child first
		printTreap(root.right, space);
		System.lineSeparator();

		// print the current node after padding with spaces
		for (int i = height; i < space; i++) {
			System.out.print(' ');
		}

		System.out.println(root.data + "(" + root.priority + ")");

		// print the left child
		System.lineSeparator();
		printTreap(root.left, space);
	}

	public static void main(String[] args) {
		// Treap keys
		Treaps treap = new Treaps();
		int[] keys = { 5, 2, 1, 4, 9, 8, 10 };

		// construct a treap
		Node root = null;
		for (int key : keys) {
			treap.insert(key);
		}

		System.out.println("Constructed treap:\n\n");
		treap.printTreap(0);

		System.out.println("\nDeleting node 1:\n\n");
		treap.delete(1);
		treap.printTreap(0);

		System.out.println("\nDeleting node 5:\n\n");
		treap.delete(5);
		treap.printTreap(0);

		System.out.println("\nDeleting node 9:\n\n");
		treap.delete(9);
		treap.printTreap(0);
	}
}
