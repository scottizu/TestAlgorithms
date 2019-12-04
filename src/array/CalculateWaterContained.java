package array;

/**
 * Next steps
 * - Refactor code to reuse
 * - More testing, check arguments
 * @author sizu
 *
 */
public class CalculateWaterContained {
	public static void main(String[] args) {
		int water1 = calculateWaterContained(new int[]{5, 4, 3, 2, 5}); // 0 + 1 + 2 + 3 + 0 = 6
		System.out.println("water1:"+water1);
		
		int water2= calculateWaterContained(new int[]{1, 2, 5, 4, 3, 2, 5}); // 0 + 0 + 0 + 1 + 2 + 3 + 0 = 6
		System.out.println("water2:"+water2);
		
		int water3= calculateWaterContained(new int[]{1, 2, 5, 4, 3, 2, 4, 2, 3}); // 0 + 0 + 0 + 0 + 1 + 2 + 0 + 1 + 0 = 4 
		System.out.println("water3:"+water3);
		
		int water4 = generalizeCaculateWaterContained(new int[]{5, 4, 3, 2, 5, 0, 1, 2, 5, 4, 3, 2, 5, 0, 1, 2, 5, 4, 3, 2, 4, 2, 3}); // 6 + 6 + 4 = 16
		System.out.println("water4:"+water4);
	}

	private static int calculateWaterContained(int[] nums) {
		int leftMax = 0;
		int total = 0;
		int subtotal = 0;
		int start = 0;
		for(int i=0; i<nums.length; i++) {
			int num = nums[i];
			if(num > leftMax) {
				total = total + subtotal;
				leftMax = num;
				subtotal = 0;
				start = i;
			} else {
				subtotal = subtotal + leftMax - num;
			}
		}
		int remaining = calculateWaterContainedAfterStartAssumingStartIsHighest(nums, start, nums.length - 1);
		total = total + remaining;
		return total;
	}

	private static int calculateWaterContainedAfterStartAssumingStartIsHighest(
			int[] nums, int start, int end) {
		int total = 0;
		int rightMax = 0;
		for(int i=end; i>start; i--) { // Assume nums[start] is highest
			int num = nums[i];
			if(num > rightMax) {
				rightMax = num;
			} else {
				total = total + rightMax - num;
			}
		}
		return total;
	}

	private static int generalizeCaculateWaterContained(int[] nums) {
		int leftMax = 0;
		int total = 0;
		int subtotal = 0;
		int start = 0;
		for(int i=0; i<nums.length; i++) {
			int num = nums[i];
			if(num == 0) {
				int remaining = calculateWaterContainedAfterStartAssumingStartIsHighest(nums, start, i-1);
				total = total + remaining;
				leftMax = 0;
				subtotal = 0;
				start = i;
			} else if(num > leftMax) {
				total = total + subtotal;
				leftMax = num;
				subtotal = 0;
				start = i;
			} else {
				subtotal = subtotal + leftMax - num;
			}
		}
		int remaining = calculateWaterContainedAfterStartAssumingStartIsHighest(nums, start, nums.length - 1);
		total = total + remaining;
		return total;
	}
}
