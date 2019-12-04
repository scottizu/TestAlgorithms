package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * CASE A - Build a BST Tree from original and compare
 * CASE B - Verify greater than and less than constraints on each node
 * CASE C - Verify sort order
 * 
 * This is the starting point, next steps include:
 *   Looking at additional test cases
 *   Optimization for Space/Time
 *   Implement Test Bed - This could have been done prior to implementation using TDD
 * @author sizu
 *
 */
public class VerfiyBinarySearchTree {
	public static void main(String[] args) {
		Node root1 = buildTree1();
		printTree("Tree #1", root1);
		validateBinarySearchTreeUsingBuildBinarySearchTreeAndCompareCanContinueChecker("Case I A:", root1);
		validateBinarySearchTreeUsingGreaterThanLessThanConstraintChecker("Case I B:", root1);
		validateBinarySearchTreeUsingGreaterThanCanContinueChecker("Case I C:", root1);
		
		Node root2 = buildTree2();
		printTree("Tree #2", root2);
		validateBinarySearchTreeUsingBuildBinarySearchTreeAndCompareCanContinueChecker("Case II A:", root2);
		validateBinarySearchTreeUsingGreaterThanLessThanConstraintChecker("Case II B:", root2);
		validateBinarySearchTreeUsingGreaterThanCanContinueChecker("Case II C:", root2);
		
		Node root3 = buildTree3();
		printTree("Tree #3", root3);
		validateBinarySearchTreeUsingBuildBinarySearchTreeAndCompareCanContinueChecker("Case III A:", root3);
		validateBinarySearchTreeUsingGreaterThanLessThanConstraintChecker("Case III B:", root3);
		validateBinarySearchTreeUsingGreaterThanCanContinueChecker("Case III C:", root3);
	}

	/**
	 * BFS Pre Order Traversal to print the tree
	 * @param textToPrint
	 * @param root
	 */
	private static void printTree(String textToPrint, Node root) {
		CanContinueChecker printCanContinueChecker = new PrintCanContinueChecker(root, 0);

		// Print Tree using BFS
		// TIME is O(N), SPACE is O(N)
		breadthFirstSearchPreOrderTraversal(printCanContinueChecker);
		PrintCanContinueChecker.print(textToPrint);
	}

	// Variations on Validation Methodologies
	
	/**
	 * BST Property: Using nodes from a tree with BFS Pre Order Traversal to create a BST, will create an exact replica if and only if it is a BST
	 * Creates a BST from all the nodes using BFS Pre Order Traversal
	 * Compares the original tree to the created BST
	 * @param textToPrint
	 * @param root
	 */
	private static void validateBinarySearchTreeUsingBuildBinarySearchTreeAndCompareCanContinueChecker(
			String textToPrint, Node root) {
		BuildBinarySearchTreeCanContinueChecker.reset(); // Always reset before use, clear history
		CanContinueChecker buildBinarySearchTreeCanContinueChecker = new BuildBinarySearchTreeCanContinueChecker(root);
		
		// Create BST - Use BFS to reconstruct the tree
		// TIME is O(N log N), SPACE is O(N)
		breadthFirstSearchPreOrderTraversal(buildBinarySearchTreeCanContinueChecker);
		Node builtRoot = BuildBinarySearchTreeCanContinueChecker.createdBST;
		printTree("Created BST", builtRoot);

		CanContinueChecker isEqualTreeCanContinueChecker = new CheckEqualCanContinueChecker(root, builtRoot);
		
		// Validate Tree - OPTION A is using BFS Pre Order Traversal
		// TIME is O(N), SPACE is O(N)
		boolean isValid_BFS = breadthFirstSearchPreOrderTraversal(isEqualTreeCanContinueChecker);
		System.out.println(textToPrint+":BFS_PreOrderTraversal:"+isValid_BFS); // Print Result

		// Validate Tree - OPTION B is using DFS Pre Order Traversal 
		// TIME is O(N), SPACE is O(log N)
		boolean isValid_DFS = depthFirstSearchPreOrderTraversal(isEqualTreeCanContinueChecker);
		System.out.println(textToPrint+":DFS_PreOrderTraversal:"+isValid_DFS); // Print Result
	}

	/**
	 * BST Property: A node's value will be greater than Ancestor A's value if it is or has an ancestor which is the right child of Ancestor A. 
	 * BST Property: A node's value will be less than Ancestor A's value if it is or has an ancestor which is the left child of Ancestor A.
	 * Creates a Requirements Checker to verify each node is within the upper and lower bounds defined by its ancestors
	 * 
	 * @param string
	 * @param root1
	 */
	private static void validateBinarySearchTreeUsingGreaterThanLessThanConstraintChecker(
			String textToPrint, Node root) {
		GreaterThanLessThanConstraintChecker greaterThanLessThanConstraintChecker = new GreaterThanLessThanConstraintChecker(root, null, null);
		
		// Validate Tree - OPTION A is using BFS Pre Order Traversal
		// TIME is O(N), SPACE is O(N)
		boolean isValid_BFS = breadthFirstSearchPreOrderTraversal(greaterThanLessThanConstraintChecker);
		System.out.println(textToPrint+":BFS_PreOrderTraversal:"+isValid_BFS); // Print Result
		
		// Validate Tree - OPTION B is using DFS Pre Order Traversal
		// TIME is O(N), SPACE is O(log N)
		boolean isValid_DFS = depthFirstSearchPreOrderTraversal(greaterThanLessThanConstraintChecker);
		System.out.println(textToPrint+":DFS_PreOrderTraversal:"+isValid_DFS); // Print Result
		
		// Validate Tree - OPTION A is using BFS Pre Order Traversal
		// TIME is O(N), SPACE is O(log N)
		boolean isValid_DFS_IOT = depthFirstSearchInOrderTraversal(greaterThanLessThanConstraintChecker);
		System.out.println(textToPrint+":DFS_InOrderTraversal:"+isValid_DFS_IOT); // Print Result
	}
	
	/**
	 * BST Property: DFS In Order Traversal will to walk through BST in sorted order
	 * If the items are not sorted, it is not a BST
	 * 
	 * @param textToPrint
	 * @param root
	 */
	private static void validateBinarySearchTreeUsingGreaterThanCanContinueChecker(String textToPrint,
			Node root) {
		GreaterThanCanContinueChecker.reset(); // Always reset before use, clear history
		CanContinueChecker greaterThanCanContinueChecker = new GreaterThanCanContinueChecker(root);
		
		// Validate Tree - Using DFS In Order Traversal
		// TIME is O(N), SPACE is O(log N)
		boolean isValid = depthFirstSearchInOrderTraversal(greaterThanCanContinueChecker);
		System.out.println(textToPrint+":DFS_InOrderTraversal:"+isValid); // Print Result
	}

	// BFS AND DFS GENERIC ALGORITHMS
	// TIME is O(N), SPACE is O(N)
	// returns false if cannot continue
	private static boolean breadthFirstSearchPreOrderTraversal(CanContinueChecker rootCanContinueChecker) {
		Queue<CanContinueChecker> canContinueCheckerQueue = new LinkedList<CanContinueChecker>();
		if(!rootCanContinueChecker.canContinue()) {
			return false;
		}
		canContinueCheckerQueue.offer(rootCanContinueChecker);
		while(!canContinueCheckerQueue.isEmpty()) {
			CanContinueChecker curr = canContinueCheckerQueue.poll();
			for(CanContinueChecker childCanContinueChecker: curr.getChildrenCanContinueCheckers()) {
				if(!childCanContinueChecker.canContinue()) {
					return false;
				}
				canContinueCheckerQueue.offer(childCanContinueChecker);
			}
		}
		return true;
	}

	// TIME is O(N), SPACE is O(log N)
	// returns false if cannot continue
	private static boolean depthFirstSearchPreOrderTraversal(CanContinueChecker rootCanContinueChecker) {
		Stack<CanContinueChecker> canContinueCheckerStack = new Stack<CanContinueChecker>();
		canContinueCheckerStack.add(rootCanContinueChecker);
		while(!canContinueCheckerStack.isEmpty()) {
			CanContinueChecker curr = canContinueCheckerStack.pop();
			if(!curr.canContinue()) {
				return false;
			}
			for(CanContinueChecker childCanContinueChecker: curr.getChildrenCanContinueCheckers()) {
				canContinueCheckerStack.push(childCanContinueChecker);
			}
		}
		return true;
	}

	// TIME is O(N), SPACE is O(log N)
	// returns false if cannot continue
	private static boolean depthFirstSearchInOrderTraversal(CanContinueChecker currChecker) {
		for(CanContinueChecker preCanContinueCheckers: currChecker.getPreCanContinueCheckers()) {
			boolean preChecks = depthFirstSearchInOrderTraversal(preCanContinueCheckers);
			if(!preChecks) {
				return false;
			}
		}
		if(!currChecker.canContinue()) {
			return false;
		}
		for(CanContinueChecker postCanContinueCheckers: currChecker.getPostCanContinueCheckers()) {
			boolean postChecks = depthFirstSearchInOrderTraversal(postCanContinueCheckers);
			if(!postChecks) {
				return false;
			}
		}
		return true;
	}

	// TIME is O(log N), SPACE is O(1)
	public static Node bstInsertNode(Node node, Node rootNode) {
		Node copyNode = new Node();
		copyNode.value = node.value;
		
		if(rootNode == null) {
			return copyNode;
		}
		Node parentNode = rootNode;
		while(true) {
			//System.out.println("inserting node... parentNode.value:"+parentNode.value+" copyNode.value:"+copyNode.value);
			if(copyNode.value < parentNode.value) {
				if(parentNode.left == null) {
					parentNode.left = copyNode;
					return rootNode;
				}
				parentNode = parentNode.left;
			} else {
				if(parentNode.right == null) {
					parentNode.right = copyNode;
					return rootNode;
				}
				parentNode = parentNode.right;
			}
		}
	}

	// Node Definitions
	public static class Node {
		int value;
		Node left;
		Node right;
	}

	// Sample Trees
	private static Node buildTree1() {
		Node root = new Node();
		root.value = 15;
		
		Node rootLeft = new Node();
		rootLeft.value = 10;
		root.left = rootLeft;
		
		Node rootRight = new Node();
		rootRight.value = 20;
		root.right = rootRight;
		
		Node rootLeftLeft = new Node();
		rootLeftLeft.value = 7;
		rootLeft.left = rootLeftLeft;
		
		Node rootLeftRight = new Node();
		rootLeftRight.value = 12;
		rootLeft.right = rootLeftRight;
		
		Node rootRightLeft = new Node();
		rootRightLeft.value = 17;
		rootRight.left = rootRightLeft;
		
		Node rootRightRight = new Node();
		rootRightRight.value = 23;
		rootRight.right = rootRightRight;
		
		return root;
	}

	private static Node buildTree2() {
		Node root = new Node();
		root.value = 15;
		
		Node rootRight = new Node();
		rootRight.value = 20;
		root.right = rootRight;
		
		Node rootRightLeft = new Node();
		rootRightLeft.value = 17;
		rootRight.left = rootRightLeft;
		
		Node rootRightRight = new Node();
		rootRightRight.value = 23;
		rootRight.right = rootRightRight;
		
		return root;
	}

	private static Node buildTree3() {
		Node root = new Node();
		root.value = 15;
		
		Node rootLeft = new Node();
		rootLeft.value = 10;
		root.left = rootLeft;
		
		Node rootRight = new Node();
		rootRight.value = 20;
		root.right = rootRight;
		
		Node rootLeftLeft = new Node();
		rootLeftLeft.value = 7;
		rootLeft.left = rootLeftLeft;
		
		Node rootLeftRight = new Node();
		rootLeftRight.value = 16;
		rootLeft.right = rootLeftRight;
		
		Node rootRightLeft = new Node();
		rootRightLeft.value = 17;
		rootRight.left = rootRightLeft;
		
		Node rootRightRight = new Node();
		rootRightRight.value = 23;
		rootRight.right = rootRightRight;
		
		return root;
	}
	
	// CanContinueChecker base class
	
	public static abstract class CanContinueChecker {
		public boolean canContinue() {
			return false;
		}
		public List<CanContinueChecker>  getPostCanContinueCheckers() { // Needed for In Order Traversal
			return null;
		}
		public List<CanContinueChecker>  getPreCanContinueCheckers() { // Needed for In Order Traversal
			return null;
		}
		public List<CanContinueChecker> getChildrenCanContinueCheckers() { // Needed for Pre/Post Order Traversal
			return null;
		}
	}
	
	/**
	 * Use BFS Pre Order Traversal and this to print a tree
	 * Prints a balanced tree for readability by filling out empty spots and placing X's 
	 * Also, prints one last row which includes null leaf nodes, represented with X's
	 * @author sizu
	 *
	 */
	public static class PrintCanContinueChecker extends CanContinueChecker {
		public static StringBuilder output = new StringBuilder(); // SPACE is roughly O(N) after all nodes are added, Worst case is very unbalanced tree taking O(2^N)
		public static Integer outputLevel = -1;
		public static String outputNewLine = "";
		public static Integer maxLevel = -1;
		
		public static void print(String textToPrint) {
			System.out.println(textToPrint);
			System.out.println(output.toString());
			output = new StringBuilder();
			outputLevel = -1;
			outputNewLine = "";
			maxLevel = -1;
		}

		Node node;
		Integer level;

		public PrintCanContinueChecker(Node node, Integer level) {
			this.node = node;
			this.level = level;
			if(node != null && level > maxLevel) {
				maxLevel = level;
				// System.out.println("increment max level... node.value:"+node.value+" level:"+level+" maxLevel:"+maxLevel);
			}
		}

		@Override
		public boolean canContinue() {
			if(level <= outputLevel) {
				output.append(" ");
				output.append(getNodeValue(node));
			} else {
//				System.out.println("jump to next level level:"+level+" maxLevel:"+maxLevel+" node.value:"+getNodeValue(node));
				if(level <= maxLevel + 1)  { // Print one row past maxLevel
					output.append(outputNewLine);;
					output.append(getNodeValue(node));
					outputNewLine = "\n";
					outputLevel++;
				}
			}
			return true;
		}

		private String getNodeValue(Node node) {
			if(node == null) {
				return "X";
			} else {
				return ""+node.value;
			}
		}

		@Override
		public List<CanContinueChecker> getChildrenCanContinueCheckers() {
			List<CanContinueChecker> canContinueCheckers = new ArrayList<CanContinueChecker>();
			int newLevel = this.level + 1;
			if(node != null && node.left != null) {
				canContinueCheckers.add(new PrintCanContinueChecker(node.left, newLevel));
			} else {
				if(newLevel <= maxLevel + 1) { // Print a balanced tree, even if nodes are missing
					canContinueCheckers.add(new PrintCanContinueChecker(null, newLevel));
				}
			}
			if(node != null && node.right != null) {
				canContinueCheckers.add(new PrintCanContinueChecker(node.right, newLevel));
			} else {
				if(newLevel <= maxLevel + 1) { // Print a balanced tree, even if nodes are missing
					canContinueCheckers.add(new PrintCanContinueChecker(null, newLevel));
				}
			}
			return canContinueCheckers;
		}
	}
	
	/**
	 * CASE A - Use BFS Pre Order Traversal and this to build a Binary Search Tree
	 * @author sizu
	 *
	 */
	public static class BuildBinarySearchTreeCanContinueChecker extends CanContinueChecker {
		public static Node createdBST = null;
		public static void reset() {
			createdBST = null;
		}
		
		Node node;
		public BuildBinarySearchTreeCanContinueChecker(Node node) {
			this.node = node;
		}

		@Override
		public boolean canContinue() {
			createdBST = bstInsertNode(node, createdBST); // Need to assign for 1st insertion
//			 printTree("BST Insert Node", createdBST);
			return true;
		}

		@Override
		public List<CanContinueChecker> getChildrenCanContinueCheckers() {
			List<CanContinueChecker> canContinueCheckers = new ArrayList<CanContinueChecker>();
			if(node.left != null) {
				canContinueCheckers.add(new BuildBinarySearchTreeCanContinueChecker(node.left));
			}
			if(node.right != null) {
				canContinueCheckers.add(new BuildBinarySearchTreeCanContinueChecker(node.right));
			}
			return canContinueCheckers;
		}
	}

	/**
	 * CASE A - Use BFS/DFS Pre Order Traversal to compare two trees
	 * @author sizu
	 *
	 */
	public static class CheckEqualCanContinueChecker extends CanContinueChecker {
		Node node1;
		Node node2;
		public CheckEqualCanContinueChecker(Node node1, Node node2) {
			this.node1 = node1;
			this.node2 = node2;
		}
		
		@Override
		public boolean canContinue() {
			if(node1.value != node2.value) {
				return false;
			}
			// Verify same number of children, in general case
			if(node1.left == null && node2.left != null) {
				return false;
			}
			if(node1.right == null && node2.right != null) {
				return false;
			}
			if(node2.left == null && node1.left != null) {
				return false;
			}
			if(node2.right == null && node1.right != null) {
				return false;
			}
			return true;
		}

		@Override
		public List<CanContinueChecker> getChildrenCanContinueCheckers() {
			List<CanContinueChecker> canContinueCheckers = new ArrayList<CanContinueChecker>();
			if(node1.left != null) { // Pre Order Traversal guarantees node1.left != null => node2.left != null
				canContinueCheckers.add(new CheckEqualCanContinueChecker(node1.left, node2.left));
			}
			if(node1.right != null) {
				canContinueCheckers.add(new CheckEqualCanContinueChecker(node1.right, node2.right));
			}
			return canContinueCheckers;
		}
	}

	/**
	 * CASE B - Use BFS/DFS Pre Order Traversal or DFS In Order Traversal to check constraints
	 * @author sizu
	 *
	 */
	public static class GreaterThanLessThanConstraintChecker extends CanContinueChecker {
		Node node;
		Integer mustBeLessThan = null;
		Integer mustBeGreaterThan = null;
		public GreaterThanLessThanConstraintChecker(Node node, Integer mustBeLessThan, Integer mustBeGreaterThan) {
			this.node = node;
			this.mustBeLessThan = mustBeLessThan;
			this.mustBeGreaterThan = mustBeGreaterThan;
		}
		
		@Override
		public boolean canContinue() {
			if(mustBeLessThan != null && node.value > mustBeLessThan) { // Allow equal
				return false;
			}
			if(mustBeGreaterThan != null && node.value < mustBeGreaterThan) { // Allow equal
				return false;
			}
			return true;
		}

		@Override
		public List<CanContinueChecker> getPreCanContinueCheckers() {
			List<CanContinueChecker> canContinueCheckers = new ArrayList<CanContinueChecker>();
			if(node.left != null) {
				canContinueCheckers.add(new GreaterThanLessThanConstraintChecker(node.left, node.value, mustBeGreaterThan));
			}
			return canContinueCheckers;
		}

		@Override
		public List<CanContinueChecker> getPostCanContinueCheckers() {
			List<CanContinueChecker> canContinueCheckers = new ArrayList<CanContinueChecker>();
			if(node.right != null) {
				canContinueCheckers.add(new GreaterThanLessThanConstraintChecker(node.right, mustBeLessThan, node.value));
			}
			return canContinueCheckers;
		}

		@Override
		public List<CanContinueChecker> getChildrenCanContinueCheckers() {
			List<CanContinueChecker> canContinueCheckers = new ArrayList<CanContinueChecker>();
			if(node.left != null) {
				canContinueCheckers.add(new GreaterThanLessThanConstraintChecker(node.left, node.value, mustBeGreaterThan));
			}
			if(node.right != null) {
				canContinueCheckers.add(new GreaterThanLessThanConstraintChecker(node.right, mustBeLessThan, node.value));
			}
			return canContinueCheckers;
		}
	}
	
	/**
	 * CASE C - Use DFS In Order Traversal and this to validate a Binary Search Tree
	 * @author sizu
	 *
	 */
	public static class GreaterThanCanContinueChecker extends CanContinueChecker {
		public static Integer lastNumber; // SPACE is O(1)

		public static void reset() {
			lastNumber = null;
		}

		Node node;
		public GreaterThanCanContinueChecker(Node node) {
			this.node = node;
		}

		@Override
		public boolean canContinue() {
//			System.out.println("lastNumber:"+lastNumber+" node.value:"+node.value);
			boolean valid = true;
			if(lastNumber != null) {
				valid = node.value > lastNumber;
			}
			lastNumber = node.value;
			return valid;
		}

		@Override
		public List<CanContinueChecker> getPreCanContinueCheckers() {
			List<CanContinueChecker> canContinueCheckers = new ArrayList<CanContinueChecker>();
			if(node.left != null) {
				canContinueCheckers.add(new GreaterThanCanContinueChecker(node.left));
			}
			return canContinueCheckers;
		}

		@Override
		public List<CanContinueChecker> getPostCanContinueCheckers() {
			List<CanContinueChecker> canContinueCheckers = new ArrayList<CanContinueChecker>();
			if(node.right != null) {
				canContinueCheckers.add(new GreaterThanCanContinueChecker(node.right));
			}
			return canContinueCheckers;
		}
	}
}
