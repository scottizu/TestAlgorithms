package string;

public class StringOneEditAway {
	public static void main(String[] args) {
		System.out.println("case1:"+areOneOrZeroEditsAway("pale", "ple"));
		System.out.println("case1:"+areOneOrZeroEditsAway("pales", "pale"));
		System.out.println("case1:"+areOneOrZeroEditsAway("pale", "bale"));
		System.out.println("case1:"+areOneOrZeroEditsAway("pale", "bake"));
		System.out.println("case1:"+areOneOrZeroEditsAway("pafe", "ple"));
	}

	private static boolean areOneOrZeroEditsAway(String string1, String string2) {
		if(Math.abs(string1.length() - string2.length()) > 1) {
			System.out.println("C0: string1:"+string1+" string2:"+string2);
			return false;
		}
		
		int walker1 = 0;
		int walker2 = 0;
		while(walker1 < string1.length() && walker2 < string2.length() ) {
			if(string1.charAt(walker1) == string2.charAt(walker2)) {
				walker1++;
				walker2++;
			} else {
				break;
			}
		}
		if(walker1 == string1.length()) {
			System.out.println("C1: string1:"+string1+" string2:"+string2);
			return true;
		}
		if(walker2 == string2.length()) {
			System.out.println("C2: string1:"+string1+" string2:"+string2);
			return true;
		}
		

		int backwalker1 = string1.length() - 1;
		int backwalker2 = string2.length() - 1;
		while(backwalker1 >= walker1 && backwalker2 >= walker2) {
			if(string1.charAt(backwalker1) == string2.charAt(backwalker2)) {
				backwalker1--;
				backwalker2--;
			} else {
				break;
			}
		}
		if(backwalker1 > walker1 || backwalker2 > walker2) {
			return false;
		} else {
			return true;
		}
	}
}

