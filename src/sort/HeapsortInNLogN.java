package sort;

public class HeapsortInNLogN {
	public static void main(String[] args) {
		System.out.println("Case I");
		int[] numbers = new int[] {3, 7, 8, 5, 2, 1, 9, 5, 4};
		printArrayAndHeap(0, numbers.length - 1, numbers, "pre_sort");
		heapsortInNLogN(numbers);
		printArrayAndHeap(0, numbers.length - 1, numbers, "post_sort");

		System.out.println("Case II");
		numbers = new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1};
		printArrayAndHeap(0, numbers.length - 1, numbers, "pre_sort");
		heapsortInNLogN(numbers);
		printArrayAndHeap(0, numbers.length - 1, numbers, "post_sort");

		System.out.println("Case III");
		numbers = new int[] {5, 5, 5, 5, 5, 5, 5, 5, 5};
		printArrayAndHeap(0, numbers.length - 1, numbers, "pre_sort");
		heapsortInNLogN(numbers);
		printArrayAndHeap(0, numbers.length - 1, numbers, "post_sort");

		System.out.println("Case IV");
		numbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		printArrayAndHeap(0, numbers.length - 1, numbers, "pre_sort");
		heapsortInNLogN(numbers);
		printArrayAndHeap(0, numbers.length - 1, numbers, "post_sort");
	}

	private static void printArrayAndHeap(int i, int j, int[] numbers, String text) {
		printArray(i,j,numbers, text);
		printHeap(i,j,numbers);
	}

	private static void printArray(int start, int end, int[] numbers, String text) {
		StringBuilder out = new StringBuilder();
		out.append("{");
		String del = "";
		for(int i=start; i < end + 1; i++) {
			out.append(del);
			out.append(numbers[i]);
			del = ",";
		}
		out.append("}");
		System.out.println(text+":"+out);
	}

	private static void printHeap(int start, int end, int[] numbers) {
		int depth = (int) Math.ceil((Math.log(end+2)/Math.log(2)));
		int currentLevel = 0;

		while(currentLevel < depth) {
			int firstAtCurrentLevel = (int) Math.pow(2, currentLevel) - 1;
			int firstAtNextLevel = (int) Math.pow(2, currentLevel + 1) - 1;
			
			StringBuilder heapout = new StringBuilder();
			for(int index = firstAtCurrentLevel; index < firstAtNextLevel; index++) {
				if(index <= end) {
					heapout.append(" ");
					heapout.append(numbers[index]);
				}
			}
			System.out.println("level:"+currentLevel+" heap:"+heapout);
			currentLevel++;
		}
	}

	// O(n log n)
	private static void heapsortInNLogN(int[] numbers) {
		// O(n) on outer loop
		for(int i=1; i<numbers.length; i++) {
			bubbleUpIndex(i, numbers[i], numbers);
			printArrayAndHeap(0, i, numbers, "bubble_up");
		}
		
		// O(n) on outer loop
		for(int i=numbers.length - 1; i > 0; i--) {
			int parentNum = numbers[i];
			numbers[i] = numbers[0]; // Move max, which is root of heap, into proper sort place
			numbers[0] = parentNum; // In case 0 through i-1 is already max heap, need to set it here
			bubbleDownIndex(0, parentNum, numbers, i-1);
			printArrayAndHeap(0, i-1, numbers, "bubble_down");
		}
	}

	// Assuming 0 through index - 1 satisfies a max heap, make 0 through index satisfy a max heap
	// 0 -> 1, 2
	// 1 -> 3, 4
	// 2 -> 5, 6
	// 3 -> 7, 8
	// max will be in index 0
	private static void bubbleUpIndex(int childIndex, int childNum, int[] numbers) {
		// O(log n) on inner loop
		while(true) {
			int parentIndex = (int) Math.floor((childIndex-1)/2);
			Integer parentNum = numbers[parentIndex];
			
			boolean parentIsBigger = (parentNum > childNum);
			if(parentIsBigger) {
				return;
			}
			numbers[parentIndex] = childNum;
			numbers[childIndex] = parentNum;
			
			childIndex = parentIndex;
			// childNum = childNum;
			if(parentIndex == 0) {
				return;
			}
		}
	}

	private static void bubbleDownIndex(int parentIndex, int parentNum, int[] numbers, int finalIndex) {
		// O(log n) on inner loop
		while(true) {
			int leftChildIndex = parentIndex*2 + 1;
			int rightChildIndex = parentIndex*2 + 2;
			Integer leftChildNum = null;
			if(leftChildIndex <= finalIndex) {
				leftChildNum = numbers[leftChildIndex];
			}
			Integer rightChildNum = null;
			if(rightChildIndex <= finalIndex) {
				rightChildNum = numbers[rightChildIndex];
			}
			boolean leftChildIsSmaller = (leftChildNum == null || parentNum > leftChildNum);
			boolean rightChildIsSmaller = (rightChildNum == null || parentNum > rightChildNum);
			Boolean bubbleDownThroughRightChild = null;
			if(leftChildIsSmaller && rightChildIsSmaller) {
				return;
			} else if(leftChildIsSmaller && !rightChildIsSmaller) {
				bubbleDownThroughRightChild = true;
			} else if(!leftChildIsSmaller && rightChildIsSmaller) {
				bubbleDownThroughRightChild = false;
			} else if(rightChildNum > leftChildNum) {
				bubbleDownThroughRightChild = true;
			} else {
				bubbleDownThroughRightChild = false;
			}
			
			if(bubbleDownThroughRightChild) {
				numbers[parentIndex] = rightChildNum;
				numbers[rightChildIndex] = parentNum;
				parentIndex = rightChildIndex;
				//parentNum = parentNum;
			} else {
				numbers[parentIndex] = leftChildNum;
				numbers[leftChildIndex] = parentNum;
				parentIndex = leftChildIndex;
				//parentNum = parentNum;
			}
		}
	}

}
