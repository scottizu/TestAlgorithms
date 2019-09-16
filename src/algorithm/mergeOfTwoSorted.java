package algorithm;

public class mergeOfTwoSorted {

	public static void main(String[] args) {
		int[] outA = findMergeSortedArrays(new int[] {1, 2}, new int[] {3, 4});
		printArray(0, outA.length - 1, outA, "A.Merged:");

		int[] outB = findMergeSortedArrays(new int[] {3, 4}, new int[] {1, 2});
		printArray(0, outB.length - 1, outB, "B.Merged:");

		int[] outC = findMergeSortedArrays(new int[] {1, 51, 75, 80, 91, 100}, new int[] {1, 2, 6, 36, 40, 96});
		printArray(0, outC.length - 1, outC, "C.Merged:");

		int[] outD = findMergeSortedArrays(new int[] {1, 2, 3, 4, 5, 10}, new int[] {});
		printArray(0, outD.length - 1, outD, "D.Merged:");
		
		int[] outE = findMergeSortedArrays(new int[] {}, new int[] {3, 5, 6});
		printArray(0, outE.length - 1, outE, "E.Merged:");

		int[] outF = findMergeSortedArrays(new int[] {1, 2, 3, 4, 5, 100}, new int[] {1, 2, 6, 36, 40, 96});
		printArray(0, outF.length - 1, outF, "F.Merged:");

	}

	private static int[] findMergeSortedArrays(int[] nums1, int[] nums2) {
		
		int index1 = 0;
		int index2 = 0;
		
		int[] combined = new int[nums1.length+nums2.length];
		int combinedIndex = 0;
		
		if(index1 > nums1.length - 1) {
			copyRemaining(index2, nums2, combinedIndex, combined);
			return combined;
		}
		if(index2 > nums2.length - 1) {
			copyRemaining(index1, nums1, combinedIndex, combined);	
			return combined;
		}
		
		int num1 = nums1[index1];
		index1++;
		
		int num2 = nums2[index2];
		index2++;
		
		while(true) {
			if(num1 < num2) {
				combined[combinedIndex] = num1;
				combinedIndex++;
				if(index1 > nums1.length - 1) {
					combined[combinedIndex] = num2;
					combinedIndex++;
					
					copyRemaining(index2, nums2, combinedIndex, combined);
					return combined;
				} else {
					num1 = nums1[index1];
					index1++;
				}
			} else {
				combined[combinedIndex] = num2;
				combinedIndex++;
				if(index2 > nums2.length - 1) {
					combined[combinedIndex] = num1;
					combinedIndex++;
					
					copyRemaining(index1, nums1, combinedIndex, combined);
					return combined;
				} else {
					num2 = nums2[index2];
					index2++;
				}
			}
		}
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
}
