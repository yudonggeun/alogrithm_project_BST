package algorithm_homework;

import java.util.Random;

class Treaps
{
	public class Node
	{
		int data;
		int priority;
		Node left, right;

		// constructor
		Node(int data)
		{
			this.data = data;
			this.priority = new Random().nextInt(100);
			this.left = this.right = null;
		}
	}
	/* Function to left-rotate a given treap

		  r                         R
		 / \      Left Rotate      / \
		L   R        ———>         r   Y
		   / \                   / \
		  X   Y                 L   X
	*/
	public Node rotateLeft(Node root)
	{
		Node R = root.right;
		Node X = root.right.left;

		// rotate
		R.left = root;
		root.right = X;

		// set a new root
		return R;
	}

	/* Function to right-rotate a given treap

			r                        L
		   / \     Right Rotate     / \
		  L   R        ———>        X   r
		 / \                          / \
		X   Y                        Y   R
	*/
	public Node rotateRight(Node root)
	{
		Node L = root.left;
		Node Y = root.left.right;

		// rotate
		L.right = root;
		root.left = Y;

		// set a new root
		return L;
	}

	// Recursive function to insert a given key with a priority into treap
	public Node insertNode(Node root, int data)
	{
		// base case
		if (root == null) {
			return new Node(data);
		}

		// if data is less than the root node, insert in the left subtree;
		// otherwise, insert in the right subtree
		if (data < root.data)
		{
			root.left = insertNode(root.left, data);

			// rotate right if heap property is violated
			if (root.left != null && root.left.priority > root.priority) {
				root = rotateRight(root);
			}
		}
		else {
			root.right = insertNode(root.right, data);

			// rotate left if heap property is violated
			if (root.right != null && root.right.priority > root.priority) {
				root = rotateLeft(root);
			}
		}

		return root;
	}

	// Recursive function to search for a key in a given treap
	public boolean searchNode(Node root, int key)
	{
		// if the key is not present in the tree
		if (root == null) {
			return false;
		}

		// if the key is found
		if (root.data == key) {
			return true;
		}

		// if the key is less than the root node, search in the left subtree
		if (key < root.data) {
			return searchNode(root.left, key);
		}

		// otherwise, search in the right subtree
		return searchNode(root.right, key);
	}

	// Recursive function to delete a key from a given treap
	public Node deleteNode(Node root, int key)
	{
		// base case: the key is not found in the tree
		if (root == null) {
			return null;
		}

		// if the key is less than the root node, recur for the left subtree
		if (key < root.data) {
			root.left = deleteNode(root.left, key);
		}

		// if the key is more than the root node, recur for the right subtree
		else if (key > root.data) {
			root.right = deleteNode(root.right, key);
		}

		// if the key is found
		else {
			// Case 1: node to be deleted has no children (it is a leaf node)
			if (root.left == null && root.right == null)
			{
				// deallocate the memory and update root to null
				root = null;
			}

			// Case 2: node to be deleted has two children
			else if (root.left != null && root.right != null)
			{
				// if the left child has less priority than the right child
				if (root.left.priority < root.right.priority)
				{
					// call `rotateLeft()` on the root
					root = rotateLeft(root);

					// recursively delete the left child
					root.left = deleteNode(root.left, key);
				}
				else {
					// call `rotateRight()` on the root
					root = rotateRight(root);

					// recursively delete the right child
					root.right = deleteNode(root.right, key);
				}
			}

			// Case 3: node to be deleted has only one child
			else {
				// choose a child node
				Node child = (root.left != null)? root.left: root.right;
				root = child;
			}
		}

		return root;
	}

	// Utility function to print two-dimensional view of a treap using
	// reverse inorder traversal
	public void printTreap(Node root, int space)
	{
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

	public static void main(String[] args)
	{
		// Treap keys
		Treaps treap = new Treaps();
		int[] keys = { 5, 2, 1, 4, 9, 8, 10 };
		
		// construct a treap
		Node root = null;
		for (int key: keys) {
			root = treap.insertNode(root, key);
		}

		System.out.println("Constructed treap:\n\n");
		treap.printTreap(root, 0);

		System.out.println("\nDeleting node 1:\n\n");
		root = treap.deleteNode(root, 1);
		treap.printTreap(root, 0);

		System.out.println("\nDeleting node 5:\n\n");
		root = treap.deleteNode(root, 5);
		treap.printTreap(root, 0);

		System.out.println("\nDeleting node 9:\n\n");
		root = treap.deleteNode(root, 9);
		treap.	printTreap(root, 0);
	}
}
