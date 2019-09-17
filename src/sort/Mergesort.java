package sort;

public class Mergesort {
	public static void main(String[] args) {
		System.out.println("Case I");
		int[] numbers = new int[] {3, 7, 8, 5, 2, 1, 9, 5, 4};
		printArray(0, numbers.length - 1, numbers, "pre_sort");
		mergesort(numbers);
		printArray(0, numbers.length - 1, numbers, "post_sort");
		
		System.out.println("Case II");
		numbers = new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1};
		printArray(0, numbers.length - 1, numbers, "pre_sort");
		mergesort(numbers);
		printArray(0, numbers.length - 1, numbers, "post_sort");

		System.out.println("Case III");
		numbers = new int[] {5, 5, 5, 5, 5, 5, 5, 5, 5};
		printArray(0, numbers.length - 1, numbers, "pre_sort");
		mergesort(numbers);
		printArray(0, numbers.length - 1, numbers, "post_sort");

		System.out.println("Case IV");
		numbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		printArray(0, numbers.length - 1, numbers, "pre_sort");
		mergesort(numbers);
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
	
	// O(n log n)
	private static void mergesort(int[] numbers) {
		int[] temparray = new int[numbers.length];
		
		// Outer loop is O(log n) - Divide and Conquer
		mergesortAlgorithm(0, numbers.length - 1, numbers, temparray);
	}

	private static void mergesortAlgorithm(int start, int end, int[] numbers, int[] temparray) {
		if(end == start) {
			return;
		} else {
			int numbersInFirstHalf = (end - start + 1)/2; // This is Math.floor
			int firstHalfStart = start;
			int firstHalfEnd = start + numbersInFirstHalf - 1;
			int secondHalfStart = start + numbersInFirstHalf;
			int secondHalfEnd = end;
			
			// Outer loop is O(log n) - Divide and Conquer
			mergesortAlgorithm(firstHalfStart, firstHalfEnd, numbers, temparray);
			mergesortAlgorithm(secondHalfStart, secondHalfEnd, numbers, temparray);
			
			mergeSorted(start, end, numbers, temparray, firstHalfStart, firstHalfEnd, secondHalfStart, secondHalfEnd);

			// Copy sorted back into original
			copyArrayElements(start, end, temparray, start, numbers);
			printArray(start, end, numbers, "sorted_subarray");
		}
	}

	private static void mergeSorted(int start, int end, int[] numbers,
			int[] temparray, int firstHalfStart, int firstHalfEnd,
			int secondHalfStart, int secondHalfEnd) {

		int tempindex = start;
		
		int firstNum = numbers[firstHalfStart];
		int secondNum = numbers[secondHalfStart];
		
		// Inner loop is O(n) 
		while(true) {
			if(firstNum < secondNum) {
				temparray[tempindex] = firstNum;
				tempindex++;
				firstHalfStart++;
				if(firstHalfStart == firstHalfEnd + 1) {
					copyArrayElements(secondHalfStart, secondHalfEnd, numbers, tempindex, temparray);
					return;
				} else {
					firstNum = numbers[firstHalfStart];
				}
			} else {
				temparray[tempindex] = secondNum;
				tempindex++;
				secondHalfStart++;
				if(secondHalfStart == secondHalfEnd + 1) {
					copyArrayElements(firstHalfStart, firstHalfEnd, numbers, tempindex, temparray);
					return;
				} else {
					secondNum = numbers[secondHalfStart];
				}
			}
		}
	}

	private static void copyArrayElements(int fromStart, int fromEnd, int[] from,
			int toStart, int[] to) {
		for(int i=fromStart; i<=fromEnd; i++){
			to[toStart] = from[i];
			toStart++;
		}
	}
}
