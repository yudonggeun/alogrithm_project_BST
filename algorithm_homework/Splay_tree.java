package algorithm_homework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/** Class SplayTree **/
class Splay_tree implements Tree{
	/** Class Node **/
	private class Node {
		Node left, right, parent;
		int element;

		/** Constructor **/
		public Node() {
			this(0, null, null, null);
		}

		/** Constructor **/
		public Node(int ele) {
			this(ele, null, null, null);
		}

		/** Constructor **/
		public Node(int ele, Node left, Node right, Node parent) {
			this.left = left;
			this.right = right;
			this.parent = parent;
			this.element = ele;
		}
	}

	private Node root;
	private int count = 0;

	/** Constructor **/
	public Splay_tree() {
		root = null;
	}

	/** Function to check if tree is empty **/
	public boolean isEmpty() {
		return root == null;
	}

	public void insertArray(ArrayList<Integer> array) {
		for(int value : array) {
			insert(value);
		}
	}
	
	/** function to insert element */
	public void insert(int ele) {
		Node z = root;
		Node p = null;
		while (z != null) {
			p = z;
			if (ele > p.element)
				z = z.right;
			else
				z = z.left;
		}
		z = new Node();
		z.element = ele;
		z.parent = p;
		if (p == null)
			root = z;
		else if (ele > p.element)
			p.right = z;
		else
			p.left = z;
		Splay(z);
		count++;
	}

	/** rotate **/
	public void makeLeftChildParent(Node c, Node p) {
		if ((c == null) || (p == null) || (p.left != c) || (c.parent != p))
			throw new RuntimeException("WRONG");

		if (p.parent != null) {
			if (p == p.parent.left)
				p.parent.left = c;
			else
				p.parent.right = c;
		}
		if (c.right != null)
			c.right.parent = p;

		c.parent = p.parent;
		p.parent = c;
		p.left = c.right;
		c.right = p;
	}

	/** rotate **/
	public void makeRightChildParent(Node c, Node p) {
		if ((c == null) || (p == null) || (p.right != c) || (c.parent != p))
			throw new RuntimeException("WRONG");
		if (p.parent != null) {
			if (p == p.parent.left)
				p.parent.left = c;
			else
				p.parent.right = c;
		}
		if (c.left != null)
			c.left.parent = p;
		c.parent = p.parent;
		p.parent = c;
		p.right = c.left;
		c.left = p;
	}

	/** function splay **/
	private void Splay(Node x) {
		while (x.parent != null) {
			Node Parent = x.parent;
			Node GrandParent = Parent.parent;
			if (GrandParent == null) {
				if (x == Parent.left)
					makeLeftChildParent(x, Parent);
				else
					makeRightChildParent(x, Parent);
			} else {
				if (x == Parent.left) {
					if (Parent == GrandParent.left) {
						makeLeftChildParent(Parent, GrandParent);
						makeLeftChildParent(x, Parent);
					} else {
						makeLeftChildParent(x, x.parent);
						makeRightChildParent(x, x.parent);
					}
				} else {
					if (Parent == GrandParent.left) {
						makeRightChildParent(x, x.parent);
						makeLeftChildParent(x, x.parent);
					} else {
						makeRightChildParent(Parent, GrandParent);
						makeRightChildParent(x, Parent);
					}
				}
			}
		}
		root = x;
	}

	/** function to remove element **/
	public void remove(int ele) {
		Node node = findNode(ele);
		remove(node);
	}

	/** function to remove node **/
	private void remove(Node node) {
		if (node == null)
			return;

		Splay(node);
		if ((node.left != null) && (node.right != null)) {
			Node min = node.left;
			while (min.right != null)
				min = min.right;

			min.right = node.right;
			node.right.parent = min;
			node.left.parent = null;
			root = node.left;
		} else if (node.right != null) {
			node.right.parent = null;
			root = node.right;
		} else if (node.left != null) {
			node.left.parent = null;
			root = node.left;
		} else {
			root = null;
		}
		node.parent = null;
		node.left = null;
		node.right = null;
		node = null;
		count--;
	}

	/** Functions to count number of nodes **/
	public int countNodes() {
		return count;
	}

	/** Functions to searchNode for an element **/
	public long search(Integer value) {
		long time = System.currentTimeMillis();
		if(findNode(value) == null) {
			return -1;
		}
		return System.currentTimeMillis()-time;
	}

	private Node findNode(int ele) {
		Node PrevNode = null;
		Node z = root;
		while (z != null) {
			PrevNode = z;
			if (ele > z.element)
				z = z.right;
			else if (ele < z.element)
				z = z.left;
			else if (ele == z.element) {
				Splay(z);
				return z;
			}

		}
		if (PrevNode != null) {
			Splay(PrevNode);
			return null;
		}
		return null;
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

	/** Function for inorder traversal **/
	public void inorder() {
		inorder(root);
	}

	private void inorder(Node r) {
		if (r != null) {
			inorder(r.left);
			System.out.print(r.element + " ");
			inorder(r.right);
		}
	}

	/** Function for preorder traversal **/
	public void preorder() {
		preorder(root);
	}

	private void preorder(Node r) {
		if (r != null) {
			System.out.print(r.element + " ");
			preorder(r.left);
			preorder(r.right);
		}
	}

	/** Function for postorder traversal **/
	public void postorder() {
		postorder(root);
	}

	private void postorder(Node r) {
		if (r != null) {
			postorder(r.left);
			postorder(r.right);
			System.out.print(r.element + " ");
		}
	}

	/** Class SplayTreeTest **/
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/** Creating object of SplayTree **/
		Splay_tree spt = new Splay_tree();
		System.out.println("Splay Tree Test\n");
		char ch;
		/** Perform tree operations **/
		do {
			System.out.println("\nSplay Tree Operations\n");
			System.out.println("1. insert ");
			System.out.println("2. remove ");
			System.out.println("3. searchNode");
			System.out.println("4. count nodes");
			System.out.println("5. check empty");

			int choice = scan.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter integer element to insert");
				spt.insert(scan.nextInt());
				break;
			case 2:
				System.out.println("Enter integer element to remove");
				spt.remove(scan.nextInt());
				break;
			case 3:
				System.out.println("Enter integer element to searchNode");
				System.out.println("searchNode result : " + spt.search(scan.nextInt()));
				break;
			case 4:
				System.out.println("Nodes = " + spt.countNodes());
				break;
			case 5:
				System.out.println("Empty status = " + spt.isEmpty());
				break;
			default:
				System.out.println("Wrong Entry \n ");
				break;
			}
			/** Display tree **/
			System.out.print("\nPost order : ");
			spt.postorder();
			System.out.print("\nPre order : ");
			spt.preorder();
			System.out.print("\nIn order : ");
			spt.inorder();

			System.out.println("\nDo you want to continue (Type y or n) \n");
			ch = scan.next().charAt(0);
		} while (ch == 'Y' || ch == 'y');
	}
}