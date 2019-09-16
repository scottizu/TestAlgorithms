package algorithm;

/**
 * Assume stock prices are given in an array and you can pick one day to buy and a different day to sell
 * The sell day must be after the buy day
 * What is the profit from the best buy and sell?
 * @author sizu
 *
 */
public class BestBuyAndSell {
	
	public static void main(String[] args) throws Exception {
		int maxProfit1 = findMaxProfit(new int[] {9, 8, 12, 5, 9, 10, 1, 2});
		System.out.println("maxProfit1:"+maxProfit1); // 5
		
		int maxProfit2 = findMaxProfit(new int[] {15, 9, 8, 6, 3});
		System.out.println("maxProfit2:"+maxProfit2); // -1
		
		int maxProfit3 = findMaxProfit(new int[] {9, 8, 12, 5, 9, 10, 1, 15});
		System.out.println("maxProfit3:"+maxProfit3); //14
	}

	private static int findMaxProfit(int[] priceArray) throws Exception {
		if(priceArray.length < 2) {
			throw new Exception ("invalid argument");
		}
		int currentMax = priceArray[1] - priceArray[0]; // Arbitrarily to buy on day 1, sell on day 2
		int min = priceArray[0];
		Integer max = null;
		
		// O(n)
		for(int i=1; i<priceArray.length; i++) {
			int currentVal = priceArray[i];
			if(currentVal < min) {
				if(max != null) {
					int profit = max - min;
					if(profit > currentMax) {
						currentMax = profit;
					}
				} else {
					int profit = currentVal - min;
					if(profit > currentMax) {
						currentMax = profit;
					}
				}
				min = currentVal;
				max = null;
			} else if (max == null) {
				max = currentVal;
			} else if (currentVal > max) {
				max = currentVal;
			}
		}
		if(max != null) {
			int profit = max - min;
			if(profit > currentMax) {
				currentMax = profit;
			}
		}
		
		return currentMax;
	}
}
