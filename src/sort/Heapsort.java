package sort;

public class Heapsort {
	public static void main(String[] args) {
		System.out.println("Case I");
		int[] numbers = new int[] {3, 7, 8, 5, 2, 1, 9, 5, 4};
		printArrayAndHeap(0, numbers.length - 1, numbers, "pre_sort");
		heapsort(numbers);
		printArrayAndHeap(0, numbers.length - 1, numbers, "post_sort");

		System.out.println("Case II");
		numbers = new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1};
		printArrayAndHeap(0, numbers.length - 1, numbers, "pre_sort");
		heapsort(numbers);
		printArrayAndHeap(0, numbers.length - 1, numbers, "post_sort");

		System.out.println("Case III");
		numbers = new int[] {5, 5, 5, 5, 5, 5, 5, 5, 5};
		printArrayAndHeap(0, numbers.length - 1, numbers, "pre_sort");
		heapsort(numbers);
		printArrayAndHeap(0, numbers.length - 1, numbers, "post_sort");

		System.out.println("Case IV");
		numbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		printArrayAndHeap(0, numbers.length - 1, numbers, "pre_sort");
		heapsort(numbers);
		printArrayAndHeap(0, numbers.length - 1, numbers, "post_sort");
	}

	private static void printArrayAndHeap(int i, int j, int[] numbers, String text) {
		printArray(i,j,numbers, text);
		printHeap(i,j,numbers);
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

	private static void printHeap(int start, int end, int[] numbers) {
		int depth = (int) Math.ceil((Math.log(end+2)/Math.log(2)));
		int currentLevel = 0;

		while(currentLevel < depth) {
			int firstAtCurrentLevel = (int) Math.pow(2, currentLevel) - 1;
			int firstAtNextLevel = (int) Math.pow(2, currentLevel + 1) - 1;
			
			String heapout = "";
			for(int index = firstAtCurrentLevel; index < firstAtNextLevel; index++) {
				if(index <= end) {
					heapout = heapout + " " + numbers[index];
				}
			}
			System.out.println("level:"+currentLevel+" heap:"+heapout);
			currentLevel++;
		}
	}

	// O(n^2)
	private static void heapsort(int[] numbers) {
		// O(n) on outer loop
		for(int i=0; i<numbers.length; i++) {
			bubbleUpLargestAndPlaceAtEnd(0, numbers.length - 1 - i, numbers);
		}
	}

	private static void bubbleUpLargestAndPlaceAtEnd(int start, int end, int[] numbers) {
		if(end == 0) {
			return;
		}
		int startingIndex = (int) Math.floor(end+1/2) - 1;
		
		// O(n) on inner loop - Though n decreases - Fixing broken heap is O(n)
		for(int i=startingIndex; i > -1; i--) {
			swapParentWithLargestChild(start, end, numbers, i);
		}
		
		// Move largest from 0th place to end of array
		int largest = numbers[start];
		int last = numbers[end];
		numbers[end] = largest;
		numbers[start] = last; 
		printArrayAndHeap(start, end, numbers, "put_largest_at_end");
	}

	private static void swapParentWithLargestChild(int start, int end,
		int[] numbers, int i) {
		int parent = numbers[i];
		if(i*2 + 2 <= end) {
			int rightChild = numbers[i*2 + 2];
			if(rightChild > parent) {
				numbers[i] = rightChild;
				numbers[i*2 + 2] = parent;
				parent = rightChild;
				printArrayAndHeap(start, end, numbers, "swap_right_child");
			}
		}
		if(i*2 + 1 <= end) {
			int leftChild = numbers[i*2 + 1];
			if(leftChild > parent) {
				numbers[i] = leftChild;
				numbers[i*2 + 1] = parent;
				printArrayAndHeap(start, end, numbers, "swap_left_child");
			}
		}
	}
}
