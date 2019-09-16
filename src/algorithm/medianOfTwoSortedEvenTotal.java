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
		
		while(true) {
			int guess1 = (int) Math.ceil( ((double) high + (double) low) /2);
			System.out.println("low:"+low+" high:"+high+" guess1:"+guess1);
			
			//int xSize1 = guess1;
			//int xSize2 = length1 - guess1;

			// guess1 + guess2 = (length1 + length2)/2
			int guess2 = (length1 + length2)/2 - guess1;
			
			//int ySize1 = guess2; // length2 - guess1
			//int ySize2 = length2 - guess2;
			
			Integer xL = null;
			if(-1 < guess1 - 1) {
				xL = nums1[guess1 - 1];
			}
			Integer xH = null;
			if(guess1 < nums1.length) {
				xH = nums1[guess1];
			}
			Integer yL = null;
			if(-1 < guess2 - 1) {
				yL = nums2[guess2 - 1];
			}
			Integer yH = null;
			if(guess2 < nums2.length) {
				yH = nums2[guess2];
			}
//			System.out.println("xL:"+xL+" xH:"+xH);
//			System.out.println("yL:"+yL+" yH:"+yH);

			if(high == low) {
				return AverageMinXHYHAndMaxXLYL(xH, yH, xL, yL);
			}
			
			if(yH == null || xL == null || xL <= yH) {
				// Move right
				if(xH == null || yL == null || yL <= xH) {
					return AverageMinXHYHAndMaxXLYL(xH, yH, xL, yL);
				} else {
					if(low == guess1) {
						low = high;
					} else {
						low = guess1;
					}
				}
			} else {
				// Move left
				if(high == guess1) {
					high = low;
				} else {
					high = guess1;
				}
			}
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
		if(xH == null) {
			if(yL == null) {
				return ((double) yH + (double) xL)/2;
			} else {
				return ((double) yH + (double) Math.max(xL, yL))/2;
			}
		} else if (yH == null) {
			if(xL == null) {
				return ( (double) xH + (double) yL)/2;
			} else {
				return ( (double) xH + (double) Math.max(xL, yL))/2;
			}
		} else {
			if(xL == null) {
				return ((double) Math.min(xH, yH) + (double) yL)/2;
			} else if (yL == null) {
				return ((double) Math.min(xH, yH) + (double) xL)/2;
			} else {
				return ((double) Math.min(xH, yH) + (double) Math.max(xL, yL))/2;
			}
		}
	}
}
