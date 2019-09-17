package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MergeOfKSorted {

	public static void main(String[] args) {
		List<int[]> listOfSortedA = new ArrayList<int[]>();
		listOfSortedA.add(new int[] {1, 2});
		listOfSortedA.add(new int[] {3, 4});
		int[] outA = findMergeKSortedArrays(listOfSortedA);
		printArray(0, outA.length - 1, outA, "A.Merged:");

		List<int[]> listOfSortedB = new ArrayList<int[]>();
		listOfSortedB.add(new int[] {3, 4});
		listOfSortedB.add(new int[] {1, 2});
		int[] outB = findMergeKSortedArrays(listOfSortedB);
		printArray(0, outB.length - 1, outB, "B.Merged:");

		List<int[]> listOfSortedC = new ArrayList<int[]>();
		listOfSortedC.add(new int[] {1, 51, 75, 80, 91, 100});
		listOfSortedC.add(new int[] {1, 2, 6, 36, 40, 96});
		int[] outC = findMergeKSortedArrays(listOfSortedC);
		printArray(0, outC.length - 1, outC, "C.Merged:");

		List<int[]> listOfSortedD = new ArrayList<int[]>();
		listOfSortedD.add(new int[] {1, 2, 3, 4, 5, 10});
		listOfSortedD.add(new int[] {});
		int[] outD = findMergeKSortedArrays(listOfSortedD);
		printArray(0, outD.length - 1, outD, "D.Merged:");
		
		List<int[]> listOfSortedE = new ArrayList<int[]>();
		listOfSortedE.add(new int[] {});
		listOfSortedE.add(new int[] {3, 5, 6});
		int[] outE = findMergeKSortedArrays(listOfSortedE);
		printArray(0, outE.length - 1, outE, "E.Merged:");

		List<int[]> listOfSortedF = new ArrayList<int[]>();
		listOfSortedF.add(new int[] {1, 2, 3, 4, 5, 100});
		listOfSortedF.add(new int[] {1, 2, 6, 36, 40, 96});
		int[] outF = findMergeKSortedArrays(listOfSortedF);
		printArray(0, outF.length - 1, outF, "F.Merged:");

		List<int[]> listOfSortedG = new ArrayList<int[]>();
		listOfSortedG.add(new int[] {1, 2, 3, 4, 5, 100});
		listOfSortedG.add(new int[] {1, 2, 6, 36, 40, 96});
		listOfSortedG.add(new int[] {2, 6, 8, 41, 92, 96, 98});
		int[] outG = findMergeKSortedArrays(listOfSortedG);
		printArray(0, outG.length - 1, outG, "G.Merged:");

	}

	private static int[] findMergeKSortedArrays(List<int[]> listOfSorted) {
		// Set up Combined
		int totalLength = 0;
		for(int i=0; i<listOfSorted.size(); i++) {
			totalLength = totalLength + listOfSorted.get(i).length;
		}
		
		int[] combined = new int[totalLength];
		
		// Convert listOfSorted to LinkedList so the Arrays are Ordered based on smallest element.
		LinkedList<LinkedListNode> linkedListOfArrayNodes = new LinkedList<LinkedListNode>();
		for(int i=0; i<listOfSorted.size(); i++) {
			int[] numbersI = listOfSorted.get(i);

			if(numbersI.length > 0) {
				insertIntoList(listOfSorted.get(i), 0, linkedListOfArrayNodes);
			}
		}
		
		findMergeKSortedArrays(linkedListOfArrayNodes, 0, combined);
		return combined;
	}

	private static void findMergeKSortedArrays(
			LinkedList<LinkedListNode> linkedListOfArrayNodes, int combinedIndex,
			int[] combined) {
		while(true) {
			if(linkedListOfArrayNodes.isEmpty()) {
				return;
			}
			LinkedListNode lowest = linkedListOfArrayNodes.remove(0); // Will be reinserted if more numbers are present
			combined[combinedIndex] = lowest.value;
			combinedIndex++;
			
			if(lowest.indexInOriginatingArray + 1 > lowest.originatingArray.length - 1) {
				// No more, just remove this from the linked list
				
				if(linkedListOfArrayNodes.size() == 1) { // Last Array
					LinkedListNode last = linkedListOfArrayNodes.remove(0);
					copyRemaining(last.indexInOriginatingArray, last.originatingArray, combinedIndex, combined);
				}
			} else {
				insertIntoList(lowest.originatingArray, lowest.indexInOriginatingArray + 1, linkedListOfArrayNodes);
			}
		}
	}

	private static void insertIntoList(int[] originatingArray, int indexInOriginatingArray,
			LinkedList<LinkedListNode> linkedListOfArrayNodes) {

		LinkedListNode newNode = new LinkedListNode();
		newNode.originatingArray = originatingArray;
		newNode.indexInOriginatingArray = indexInOriginatingArray;
		newNode.value = originatingArray[indexInOriginatingArray];
		
		// set low to insertion point
		int low = 0;
		int high = linkedListOfArrayNodes.size();
		while(high != low) {
			int middle = (high + low)/2; 
			if(newNode.value < linkedListOfArrayNodes.get(middle).value) {
				high = middle; // middle is never equal to high
			} else {
				low = middle + 1; // when middle is equal to low, low still changes
			}
		}
		
		// insert
		linkedListOfArrayNodes.add(low, newNode);
	}

	private static void copyRemaining(int copyFromIndex, int[] copyFrom,
			int copyToIndex, int[] copyTo) {
		for(int i=copyFromIndex; i < copyFrom.length; i++) {
			copyTo[copyToIndex] = copyFrom[i];
			copyToIndex++;
		}
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
	
	public static class LinkedListNode {
		int[] originatingArray = null;
		Integer indexInOriginatingArray = null;
		Integer value = null;
	}
}
