package string;

import java.util.HashSet;
import java.util.Set;

public class StringHasUniqueCharacters {
	public static void main(String[] args) {
		System.out.println("hasUnique1:"+hasUnique("abad"));
		System.out.println("hasUnique1:"+hasUnique("abkdoes"));
		System.out.println("hasUnique1:"+hasUnique(""));
//		System.out.println("hasUnique1:"+hasUnique(null)); // error check or throw exception
	}
	
	public static boolean hasUnique(String string){
		Set<Character> charSet = new HashSet<Character>();
		for(int i=0; i<string.length(); i++) {
			char character = string.charAt(i);
			if(charSet.contains(character)){
				return false;
			} else {
				charSet.add(character);
			}
		}
		return true;
	}
}
