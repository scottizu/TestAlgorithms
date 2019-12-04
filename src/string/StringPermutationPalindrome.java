package string;

import java.util.HashSet;
import java.util.Set;

public class StringPermutationPalindrome {
	public static void main(String[] args) {
		System.out.println("case1:"+isPermutationPalindrome("Tact Coa"));
		System.out.println("case2:"+isPermutationPalindrome("azAZ"));
		System.out.println("case3:"+isPermutationPalindrome("bac"));
		System.out.println("case4:"+isPermutationPalindrome("bba ac"));
	}

	private static boolean isPermutationPalindrome(String string) {
		Set<Integer> odds = new HashSet<Integer>();
		for(int i=0; i<string.length(); i++) {
			//System.out.println("char:"+(int) string.charAt(i));
			int integer = (int) string.charAt(i);
			if(97 <= integer && integer <= 122) { // map a-z to A-Z
				integer = integer - 32;
			}
			if((65 <= integer && integer <= 90)) { // A-Z
				if(odds.contains(integer)) {
					odds.remove(integer);
				} else {
					odds.add(integer);
				}
			} 
		}
		
		if(odds.size() > 1) {
			return false;
		} else {
			return true;
		}
	}
}
 