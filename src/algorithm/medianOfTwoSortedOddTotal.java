package algorithm;

public class medianOfTwoSortedOddTotal {
	// https://leetcode.com/problems/median-of-two-sorted-arrays/
	public static void main(String[] args) {
		System.out.println("The median is "+findMedianSortedArrays(new int[] {1, 3}, new int[] {2}));

		System.out.println("The median is "+findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4}));
	}
	
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        int[] sortedArray = mergeSortedArrays(nums1, nums2, size);
        
        if(size % 2 == 1) {
            return (double) sortedArray[size/2];
        } else {
            return ((double) sortedArray[size/2] + (double) sortedArray[size/2 - 1])/2;
        }
    }

	private static int[] mergeSortedArrays(int[] nums1, int[] nums2, int size) {

        int[] sortedArray = new int[size];
        int sortedIndex = 0;
        
        int index1 = 1;
        int index2 = 1;
        int num1 = nums1[0];
        int num2 = nums2[0];
        
        while(true) {
            if(num1 < num2) {
                sortedArray[sortedIndex] = num1;
                sortedIndex++;
                if(index1 < nums1.length) {
                    num1 = nums1[index1];
                    index1++;
                } else {
                    sortedArray[sortedIndex] = num2;
                    sortedIndex++;

                	addRemainingValuesToSortedArray(sortedIndex, sortedArray, index2, nums2);
                		
                    return sortedArray;
                }
            } else {
                sortedArray[sortedIndex] = num2;
                sortedIndex++;
                if(index2 < nums2.length) {
                    num2 = nums2[index2];
                    index2++;
                } else {
                    sortedArray[sortedIndex] = num1;
                    sortedIndex++;

                	addRemainingValuesToSortedArray(sortedIndex, sortedArray, index1, nums1);
                	
                    return sortedArray;
                }
            }
        }
	}
	
	public static void addRemainingValuesToSortedArray(int sortedIndex, int[] sortedArray, int index, int[] copyFrom) {
        for(int i=index; i<copyFrom.length; i++) {
            sortedArray[sortedIndex] = copyFrom[i];
            sortedIndex++;
        }
	}
}
