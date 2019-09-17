package algorithm;

/**
 * In Progress
 * @author sizu
 *
 */
public class medianOfTwoSortedEvenTotal {
	// https://leetcode.com/problems/median-of-two-sorted-arrays/
	public static void main(String[] args) {
		System.out.println("A. The median is "+findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4}));

		System.out.println("B. The median is "+findMedianSortedArrays(new int[] {3, 4}, new int[] {1, 2}));

		System.out.println("C. The median is "+findMedianSortedArrays(new int[] {1, 51, 75, 80, 91, 100}, new int[] {1, 2, 6, 36, 40, 96}));

		System.out.println("D. The median is "+findMedianSortedArrays(new int[] {1, 2, 3, 4, 5, 10}, new int[] {}));
		
		System.out.println("E. The median is "+findMedianSortedArrays(new int[] {}, new int[] {3, 5, 6}));

		System.out.println("F. The median is "+findMedianSortedArrays(new int[] {1, 2, 3, 4, 5, 100}, new int[] {1, 2, 6, 36, 40, 96}));

		System.out.println("G. The median is "+findMedianSortedArrays(new int[] {1, 2, 3, 4, 5, 100}, new int[] {1, 6, 36, 40, 96}));

		System.out.println("H. The median is "+findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4, 5}));

		System.out.println("I. The median is "+findMedianSortedArrays(new int[] {0, 1, 1}, new int[] {3, 4, 5}));

	}

	// Assume both arrays are non empty
	//   xL and xH cannot be null, yL and yH cannot be null
	//
	private static double findMedianSortedArrays(int[] A, int[] B) {
		
		int length1 = A.length;
		int length2 = B.length;
		
		if(length1 == 0) {
			return findMedian(B);
		} else if (length2 == 0) {
			return findMedian(A);
		}
		
		int[] nums1 = A;
		int[] nums2 = B;
		if(length2 < length1) {
			length1 = B.length;
			length2 = A.length;
			nums1 = B;
			nums2 = A;
		}
		
		int low = 0;
		int high = length1;
		int totalLengths = length1 + length2;
		
		while(high != low) {
			// Items to left of guess1/guess2 is less than Items to right of guess1/guess2 to if sum of lengths is odd 
			// guess1 + guess2 = (length1 + length2)/2
			int guess1 = (high + low)/2;
			int guess2 = (totalLengths)/2 - guess1; 
			System.out.println("low:"+low+" high:"+high+" guess1:"+guess1+" guess2:"+guess2);
			
			Integer xL = getLow(guess1, nums1);
			Integer yL = getLow(guess2, nums2);
			Integer xH = getHigh(guess1, nums1);
			Integer yH = getHigh(guess2, nums2);
			
			if(yH == null || xL == null || xL <= yH) {
				if(xH == null || yL == null || yL <= xH) {
					// Found
					low = guess1;
					high = guess1;
				} else {
					// Move right
					low = guess1 + 1;
				}
			} else {
				// Move left
				high = guess1 - 1;
			}
		}

		int guess1 = (high + low)/2;
		int guess2 = (totalLengths)/2 - guess1;
		System.out.println("low:"+low+" high:"+high+" guess1:"+guess1+" guess2:"+guess2);
		
		Integer xL = getLow(guess1, nums1);
		Integer yL = getLow(guess2, nums2);
		Integer xH = getHigh(guess1, nums1);
		Integer yH = getHigh(guess2, nums2);
		if(totalLengths % 2 == 0) {
			return AverageMinXHYHAndMaxXLYL(xH, yH, xL, yL);
		} else {
			return MinXHYH(xH, yH);
		}
	}

	private static Integer getHigh(int guess, int[] nums) {
		if(guess < nums.length) {
			return nums[guess];
		} else {
			return null;
		}
	}

	private static Integer getLow(int guess, int[] nums) {
		if(-1 < guess - 1) {
			return nums[guess - 1];
		} else {
			return null;
		}
	}

	private static double findMedian(int[] a) {
		int length = a.length;
		if(length % 2 == 0) {
			return ((double) a[length/2] + (double) a[length/2 - 1])/2;
		} else {
			return (double) a[length/2];
		}
	}

	private static double AverageMinXHYHAndMaxXLYL(Integer xH, Integer yH,
			Integer xL, Integer yL) {
		return ((double) MinXHYH(xH, yH) + (double) MaxXLYL(xL, yL))/2;
	}

	private static double MinXHYH(Integer xH, Integer yH) {
		if(xH == null) {
			return yH;
		} else if (yH == null) {
			return xH;
		} else {
			return Math.min(xH, yH);
		}
	}

	private static double MaxXLYL(Integer xL, Integer yL) {
		if(xL == null) {
			return yL;
		} else if (yL == null) {
			return xL;
		} else {
			return Math.max(xL, yL);
		}
	}
}
