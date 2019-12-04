package list;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * Next steps
 * - Add test cases
 * @author sizu
 *
 */
public class LinkedListHeadTailMerge {
	public static void main(String[] args) {
		LinkedListNode root1_1 = createLinkedList1();
		printLinkedList("Example 1:", root1_1);
		headTailMergeWithDoubleLinkedList(root1_1);
		printLinkedList("Example 1, Solution 1:", root1_1);
		
		LinkedListNode root1_2 = createLinkedList1();
		printLinkedList("Example 1:", root1_2);
		headTailMergeWithArrayDeque(root1_2);
		printLinkedList("Example 1, Solution 2:", root1_2);
		
		LinkedListNode root1_3 = createLinkedList1();
		printLinkedList("Example 1:", root1_3);
		headTailMergeWithMap(root1_3);
		printLinkedList("Example 1, Solution 3:", root1_3);
		
		LinkedListNode root1_4 = createLinkedList1();
		printLinkedList("Example 1:", root1_4);
		headTailMergeWithMapRefactored(root1_4);
		printLinkedList("Example 1, Solution R:", root1_4);
		
		LinkedListNode root1_5 = createLinkedList1();
		printLinkedList("Example 1:", root1_5);
		headTailMergeInPlace(root1_5);
		printLinkedList("Example 1, Solution 4:", root1_5);
		
		LinkedListNode root2_1 = createLinkedList2();
		printLinkedList("Example 2:", root2_1);
		headTailMergeWithDoubleLinkedList(root2_1);
		printLinkedList("Example 2, Solution 1:", root2_1);
		
		LinkedListNode root2_2 = createLinkedList2();
		printLinkedList("Example 2:", root2_2);
		headTailMergeWithArrayDeque(root2_2);
		printLinkedList("Example 2, Solution 2:", root2_2);
		
		LinkedListNode root2_3 = createLinkedList2();
		printLinkedList("Example 2:", root2_3);
		headTailMergeWithMap(root2_3);
		printLinkedList("Example 2, Solution 3:", root2_3);
		
		LinkedListNode root2_4 = createLinkedList2();
		printLinkedList("Example 2:", root2_4);
		headTailMergeWithMapRefactored(root2_4);
		printLinkedList("Example 2, Solution R:", root2_4);
		
		LinkedListNode root2_5 = createLinkedList2();
		printLinkedList("Example 2:", root2_5);
		headTailMergeInPlace(root2_5);
		printLinkedList("Example 2, Solution 4:", root2_5);
	}

	private static void headTailMergeWithDoubleLinkedList(LinkedListNode walkerL) {
		// Step 1: Capability to walk backwards
		// Rather than create a new data structure, add missing info to a new data structure
		Map<LinkedListNode, LinkedListNode> map = new HashMap<LinkedListNode, LinkedListNode>();
		LinkedListNode walkerR = walkerL;
		while(walkerR.next != null) {
			map.put(walkerR.next, walkerR);
			walkerR = walkerR.next;
		}
		
		// Step 2: Merge
		while(!walkerL.value.equals(walkerR.value)) {
			LinkedListNode temp = walkerL.next;
			if(temp == walkerR) { // For Even Case
				walkerL.next = temp;
				walkerL = temp;
				break;
			} else {
				walkerL.next = walkerR;
				walkerR.next = temp;
				walkerL = temp;
				walkerR = map.get(walkerR);
			}
		}
		walkerL.next = null;
	}

	private static void headTailMergeWithArrayDeque(LinkedListNode walkerL) {
		// Step 1: Capability to walk backwards
		if(walkerL == null) {
			return;
		}
		ArrayDeque<LinkedListNode> queue = new ArrayDeque<LinkedListNode>();
		queue.offer(walkerL);
		while(walkerL.next != null) {
			walkerL = walkerL.next;
			queue.offer(walkerL); // putLast
		}

		// Step 2: Merge
		LinkedListNode last = queue.pollFirst(); // getFirst
		while(!queue.isEmpty()) {
			last.next = queue.pollLast(); // getLast
			last = last.next;
			
			if(queue.isEmpty()) {
				break;
			} else {
				last.next = queue.pollFirst(); // getFirst
				last = last.next;
			}
		}
		last.next = null;
	}

	private static void headTailMergeWithMap(LinkedListNode walkerL) {
		// Step 1: Capability to walk backwards
		Map<Integer, LinkedListNode> map = new HashMap<Integer, LinkedListNode>();
		Integer count = 1;
		while(walkerL != null) {
			map.put(count, walkerL);
			count++;
			walkerL = walkerL.next;
		}

		// Step 2: Merge
		Integer leftIndex = 1;
		Integer rightIndex = count - 1;
		
		// Use Do While
		if(rightIndex < 2) {
			return;
		}
		
		LinkedListNode current = map.get(leftIndex);
		leftIndex++;
		
		current.next = map.get(rightIndex);
		current = current.next;
		rightIndex--;
		
		while(rightIndex >= leftIndex) {
			current.next = map.get(leftIndex);
			current = current.next;
			leftIndex++;

			if(rightIndex >= leftIndex) {
				current.next = map.get(rightIndex);
				current = current.next;
				rightIndex--;
			} else {
				break;
			}
		}
		
		current.next = null;
	}

	private static void headTailMergeWithMapRefactored(LinkedListNode walkerL) {
		// Step 1: Capability to walk backwards
		Map<Integer, LinkedListNode> map = new HashMap<Integer, LinkedListNode>();
		Integer count = 1;
		while(walkerL != null) {
			map.put(count, walkerL);
			count++;
			walkerL = walkerL.next;
		}

		// Step 2: Merge
		Integer leftIndex = 1;
		Integer rightIndex = count - 1;

		// Remove Do While
		while(rightIndex >= leftIndex) {
			map.get(leftIndex).next = map.get(rightIndex);
			leftIndex++;

			if(rightIndex >= leftIndex) {
				map.get(rightIndex).next = map.get(leftIndex);
				rightIndex--;
			} else { // Odd case
				leftIndex--; // reset to terminate
				break;
			}
		}
		map.get(leftIndex).next = null;
	}

	private static void headTailMergeInPlace(LinkedListNode root) {
		// Step 0: Get Length
		LinkedListNode walker1 = root;
		Integer length = 0;
		while(walker1 != null) {
			length++;
			walker1 = walker1.next;
		}
		
		if(length < 3) {
			return;
		}

		// Step 1: Capability to walk backwards
		LinkedListNode walker2 = root;
		Integer counter2 = 1;
		Integer secondLess = length/2;
		while(counter2 <= secondLess) { // 6 nodes, 4 > 3 : 5 nodes, 3 > 2.5
			counter2++;
			walker2 = walker2.next;
		}
		LinkedListNode walker2Next = walker2.next;
		LinkedListNode lastNode = walker2; // This will be the last node
		while(walker2Next != null) {
			LinkedListNode temp = walker2Next.next;
			walker2Next.next = walker2;
			walker2 = walker2Next;
			walker2Next = temp;
		}
		
		// Step 2: Merge
		LinkedListNode walker3 = root;
		LinkedListNode walkerR = walker2;
		for(int i=0; i<secondLess; i++) {
			LinkedListNode temp = walker3.next;
			walker3.next = walkerR;
			walker3 = temp;
			temp = walkerR.next;
			walkerR.next = walker3;
			walkerR = temp;
		}
		lastNode.next = null;

	}

	private static void printLinkedList(String textToPrint, LinkedListNode walkerL) {
		StringBuilder out = new StringBuilder();
		out.append(textToPrint);
		out.append(walkerL.value);
		while(walkerL.next != null) {
			walkerL = walkerL.next;
			out.append("->"+walkerL.value);
		}
		System.out.println(out.toString());
	}

	private static LinkedListNode createLinkedList1() {
		LinkedListNode A = new LinkedListNode("A");
		LinkedListNode B = new LinkedListNode("B");
		LinkedListNode C = new LinkedListNode("C");
		LinkedListNode D = new LinkedListNode("D");
		LinkedListNode E = new LinkedListNode("E");
		LinkedListNode F = new LinkedListNode("F");
		A.next = B;
		B.next = C;
		C.next = D;
		D.next = E;
		E.next = F;
		return A;
	}

	private static LinkedListNode createLinkedList2() {
		LinkedListNode A = new LinkedListNode("A");
		LinkedListNode B = new LinkedListNode("B");
		LinkedListNode C = new LinkedListNode("C");
		LinkedListNode D = new LinkedListNode("D");
		LinkedListNode E = new LinkedListNode("E");
		A.next = B;
		B.next = C;
		C.next = D;
		D.next = E;
		return A;
	}
	
	public static class LinkedListNode {
		String value;
		LinkedListNode next;
		
		public LinkedListNode(String value) {
			this.value = value;
		}
	}
}

