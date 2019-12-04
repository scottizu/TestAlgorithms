package string;

import java.util.HashMap;
import java.util.Map;

public class StringIsPermutationOfOther {
	public static void main(String[] args) {
		System.out.println(isPermutation("test1", "1test"));
		System.out.println(isPermutation("test1", "1tes"));
		System.out.println(isPermutation("tes", "1tes"));
		System.out.println(isPermutation("aaaaabbb", "ab"));
	}

	private static boolean isPermutation(String string1, String string2) {
		Map<Character, Integer> map1 = buildCountMap(string1);
		Map<Character, Integer> map2 = buildCountMap(string2);
		for(Character key: map1.keySet()) {
			if(map2.get(key) != map1.get(key)) {
				return false;
			} else {
				map2.remove(key);
			}
		}
		if(!map2.isEmpty()) {
			return false;
		}
		return true;
	}

	private static Map<Character, Integer> buildCountMap(String string) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i=0; i<string.length(); i++) {
			char character = string.charAt(i);
			incrementCount(map, character);
		}
		return map;
	}

	private static void incrementCount(Map<Character, Integer> map,
			char character) {
		Integer currentCount = map.get(character);
		if(currentCount == null) {
			currentCount = 0;
		}
		map.put(character, currentCount + 1);
	}
}
