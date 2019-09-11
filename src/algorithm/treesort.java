package algorithm;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

public class treesort {
	public static void main(String[] args) {
		System.out.println("Case I");
		int[] numbers = new int[] {3, 7, 8, 5, 2, 1, 9, 5, 4};
		printArray(0, numbers.length - 1, numbers, "pre_sort");
		treesort(numbers);
		printArray(0, numbers.length - 1, numbers, "post_sort");
		
		System.out.println("Case II");
		numbers = new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1};
		printArray(0, numbers.length - 1, numbers, "pre_sort");
		treesort(numbers);
		printArray(0, numbers.length - 1, numbers, "post_sort");

		System.out.println("Case III");
		numbers = new int[] {5, 5, 5, 5, 5, 5, 5, 5, 5};
		printArray(0, numbers.length - 1, numbers, "pre_sort");
		treesort(numbers);
		printArray(0, numbers.length - 1, numbers, "post_sort");

		System.out.println("Case IV");
		numbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		printArray(0, numbers.length - 1, numbers, "pre_sort");
		treesort(numbers);
		printArray(0, numbers.length - 1, numbers, "post_sort");
	}

	private static void printArray(int start, int end, int[] numbers, String text) {
		String out = "{";
		String del = "";
		for(int i=start; i < end + 1; i++) {
			out = out + del + numbers[i] + "";
			del = ",";
		}
		out = out + "}";
		System.out.println(text+":"+out);
	}

	private static void printHeap(TreeSortNode rootNode, String text) {
		Set<TreeSortNode> currentNodes = new LinkedHashSet<TreeSortNode>();
		Set<TreeSortNode> nextNodes = new LinkedHashSet<TreeSortNode>();
		
		currentNodes.add(rootNode);
		boolean foundChildNode = true;
		while(foundChildNode) {
			foundChildNode = false;
			
			String out = "";
			String del = "";
			for(TreeSortNode currentNode: currentNodes) {
				String printedValue = "" + currentNode.value;
				if(currentNode.value == null) {
					printedValue = "X";
				}
				out = out + del + printedValue;
				del = " ";
				if(currentNode.left != null) {
					nextNodes.add(currentNode.left);
					foundChildNode = true;
				} else {
					TreeSortNode placeHolder = new TreeSortNode();
					nextNodes.add(placeHolder);
				}
				if(currentNode.right != null) {
					nextNodes.add(currentNode.right);
					foundChildNode = true;
				} else {
					TreeSortNode placeHolder = new TreeSortNode();
					nextNodes.add(placeHolder);
				}
			}
			System.out.println(text+":"+out);
			currentNodes = nextNodes;
			nextNodes = new LinkedHashSet<TreeSortNode>();
		}
	}
	
	// O(n log n)
	private static void treesort(int[] numbers) {
		if(numbers.length < 1) {
			return;
		}
		TreeSortNode root = new TreeSortNode();
		root.value = numbers[0];
		printHeap(root, "root");
		
		// O(n) on outer loop
		for(int i=1; i<numbers.length; i++) {
			TreeSortNode newNode = new TreeSortNode();
			newNode.value = numbers[i];
			
			insertNode(newNode, root);
		}
		
		// O(n) convert tree to array
		printTreeToArray(root, numbers);
	}

	private static void insertNode(TreeSortNode newNode, TreeSortNode root) {
		TreeSortNode parentNode = root;

		// O(log n) on inner loop to insert into tree
		while(true) {
			if(newNode.value < parentNode.value) {
				if(parentNode.left == null) {
					parentNode.left = newNode;
					printHeap(root, "left_insert");
					return;
				} else {
					parentNode = parentNode.left;
				} 
			} else { // newNode.value >= parentNode.value
				if(parentNode.right == null) {
					parentNode.right = newNode;
					printHeap(root, "right_insert");
					return;
				} else {
					parentNode = parentNode.right;
				}
			}
		}
	}

	
	private static void printTreeToArray(TreeSortNode root, int[] numbers) {
		int index = 0;
		Stack<TreeSortNode> stack = new Stack<TreeSortNode>(); // In stack if haven't printed
		TreeSortNode analyzeNode = root;
		System.out.println("root.value:"+root.value);
		
		// O(n) to traverse the tree
		while(true) {
			if(analyzeNode != null) {
				stack.push(analyzeNode);
				
				analyzeNode = analyzeNode.left;
			} else {
				if(stack.isEmpty()) {
					break;
				} else {
					analyzeNode = stack.pop();
				}
				
				numbers[index] = analyzeNode.value;
				printArray(0, index, numbers, "tree_to_array");
				index++;
				
				analyzeNode = analyzeNode.right;
			}
		}
	}

	public static class TreeSortNode {
		Integer value;
		TreeSortNode left;
		TreeSortNode right;
	}
}
