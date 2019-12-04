package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindSmallestStringContainingSet {
	public static void main(String[] args) {
		Map<Character, Integer> chars1 = new HashMap<Character, Integer>();
		chars1.put('a', 1);
		chars1.put('b', 1);
		chars1.put('c', 1);
		
		Map<Character, Integer> chars2 = new HashMap<Character, Integer>();
		chars2.put('a', 3);
		chars2.put('b', 2);
		chars2.put('c', 2);
		
		String string1 = "xayaxliebacdefaaaadblckkabbakllc";
		String string2 = "xayaxliebhhacdefaaaadblckkabbakllc";
		String string3 = "haxxbllllcllallbllllclla";
		
		String found = findSmallestStringContainingGivenSet(string1, chars1);
		System.out.println("found1:"+found);
		
		String found2 = findSmallestStringContainingGivenSet(string2, chars1);
		System.out.println("found2:"+found2);
		
		String found3 = findSmallestStringContainingGivenSet(string3, chars1);
		System.out.println("found3:"+found3);
		
		String found4 = findSmallestStringContainingGivenSet(string1, chars2);
		System.out.println("found4:"+found4);
		
		String found5 = findSmallestStringContainingGivenSet(string2, chars2);
		System.out.println("found5:"+found5);
		
		String found6 = findSmallestStringContainingGivenSet(string3, chars2);
		System.out.println("found6:"+found6);
	}

	private static String findSmallestStringContainingGivenSet(String string,
			Map<Character, Integer> chars) {
		List<SearchObject> sos = new ArrayList<SearchObject>();
		List<SearchObject> sosToRemove = new ArrayList<SearchObject>();
		String minLengthString = null;

		// Possible Optimization, once found min possible length, just return
		Integer minPossibleLength = 0;
		for(Integer lengths: chars.values()) {
			minPossibleLength = minPossibleLength + lengths;
		}
		
		// O(n)
		for(int i=0; i<string.length(); i++) {
			Character currentCharacter = string.charAt(i);

			if(chars.get(currentCharacter) != null) {
				SearchObject so = new SearchObject();
				for(Character key: chars.keySet()) {
					so.chars.put(key, chars.get(key));
				}
				sos.add(so);
			}
			
			sosToRemove.clear();
			for(SearchObject so: sos) {
				so.currentString = so.currentString + currentCharacter;
				Integer countLeft = so.chars.get(currentCharacter);
				if(countLeft != null) {
					if(countLeft == 1) {
						so.chars.remove(currentCharacter);

						if(so.chars.isEmpty()) {
							// Possible Optimization.  All sos with index less than this one can be removed from so list
							sosToRemove.add(so);
							
							System.out.println("-"+so.currentString);
							if(minLengthString == null || so.currentString.length() < minLengthString.length()) {
								minLengthString = so.currentString;
								
								// Possible Optimization, once found min possible length, just return
								if(minLengthString.length() == minPossibleLength) {
									return minLengthString;
								}
							} 
						}
					} else {
						so.chars.put(currentCharacter, countLeft - 1);
					}
				}
			}
			for(SearchObject so: sosToRemove) {
				sos.remove(so);
			}
		}
		
		return minLengthString;
	}

	public static class SearchObject {
		String currentString = "";
		Map<Character, Integer> chars = new HashMap<Character, Integer>();
	}
}
