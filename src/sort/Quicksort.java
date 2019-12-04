package sort;

public class Quicksort {
	public static void main(String[] args) {
		System.out.println("Case I");
		int[] numbers = new int[] {3, 7, 8, 5, 2, 1, 9, 5, 4};
		printArray(0, numbers.length - 1, numbers, "pre_sort");
		quicksort(numbers);
		printArray(0, numbers.length - 1, numbers, "post_sort");
		
		System.out.println("Case II");
		numbers = new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1};
		printArray(0, numbers.length - 1, numbers, "pre_sort");
		quicksort(numbers);
		printArray(0, numbers.length - 1, numbers, "post_sort");

		System.out.println("Case III");
		numbers = new int[] {5, 5, 5, 5, 5, 5, 5, 5, 5};
		printArray(0, numbers.length - 1, numbers, "pre_sort");
		quicksort(numbers);
		printArray(0, numbers.length - 1, numbers, "post_sort");

		System.out.println("Case IV");
		numbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		printArray(0, numbers.length - 1, numbers, "pre_sort");
		quicksort(numbers);
		printArray(0, numbers.length - 1, numbers, "post_sort");
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
	
	// O(n log n)
	private static int[] quicksort(int[] numbers) {
		int start = 0;
		int end = numbers.length - 1;
		
		// O(log n) on outer loop - recursion - O(log n) number of partitions
		partitionpivot(start, end, numbers);
		return numbers;
	}

	private static void partitionpivot(int start, int end, int[] numbers) {
		int walker = start;
		int pivot = end;
		if(start >= end) {
			return;
		}
		
		int pivotnumber = numbers[pivot];

		// O(n) on inner loop
		while(walker < pivot) {
			int walkernumber = numbers[walker];
			if(walkernumber > pivotnumber) {
				swapWalkerPivotAndNotProcessedNumber(numbers, walker, walkernumber, pivot, pivotnumber);
				printArray(start, end, numbers, "pivot_move");
				pivot--;
			} else {
				walker++;
			}
		}

		// O(log n) on outer loop - recursion - O(log n) number of partitions
		partitionpivot(0, pivot - 1, numbers);
		partitionpivot(pivot + 1, end, numbers);
	}

	private static void swapWalkerPivotAndNotProcessedNumber(int[] numbers,
			int walker, int walkernumber, int pivot, int pivotnumber) {
		numbers[pivot] = walkernumber;
		numbers[walker] = numbers[pivot - 1]; // when walker == pivot - 1, this does nothing
		numbers[pivot - 1] = pivotnumber;
	}
}
