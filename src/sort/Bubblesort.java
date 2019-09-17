package sort;

public class Bubblesort {
	public static void main(String[] args) {
		System.out.println("Case I");
		int[] numbers = new int[] {3, 7, 8, 5, 2, 1, 9, 5, 4};
		printArray(0, numbers.length - 1, numbers, "pre_sort");
		bubblesort(numbers);
		printArray(0, numbers.length - 1, numbers, "post_sort");
		
		System.out.println("Case II");
		numbers = new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1};
		printArray(0, numbers.length - 1, numbers, "pre_sort");
		bubblesort(numbers);
		printArray(0, numbers.length - 1, numbers, "post_sort");

		System.out.println("Case III");
		numbers = new int[] {5, 5, 5, 5, 5, 5, 5, 5, 5};
		printArray(0, numbers.length - 1, numbers, "pre_sort");
		bubblesort(numbers);
		printArray(0, numbers.length - 1, numbers, "post_sort");

		System.out.println("Case IV");
		numbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		printArray(0, numbers.length - 1, numbers, "pre_sort");
		bubblesort(numbers);
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
	
	// O(n^2)
	private static void bubblesort(int[] numbers) {
		int n = numbers.length; // First optimization is that nth largest number is put in place on nth iteration, so no need to check last n after n iterations
		boolean swapped = false; // Second optimization is that if no swap occurs, the array is already sorted, so can exit
		
		// O(n)
		for(int i=0; i < numbers.length; i++) {
			swapped = lookForSwaps(numbers, n);
			
			if(!swapped) {
				return;
			}
			n = n - 1;
		}
	}

	private static boolean lookForSwaps(int[] numbers, int n) {
		int first = numbers[0]; 
		boolean swapped = false;
		
		// O(n) on inner loop - Though n decreases
		for(int j=1; j < n; j++) {
			int second = numbers[j];
			if(first > second) {
				swap(numbers, j, first, second);
				swapped = true;
			} else {
				first = second;
			}
		}
		
		return swapped;
	}

	private static void swap(int[] numbers, int j, int first,
			int second) {
		numbers[j-1] = second;
		numbers[j] = first;
		printArray(0, numbers.length - 1, numbers, "swapped");
	}
}
