package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * swap takes in a string as input and replaces all occurences of one letter to another letter and returns the final string
 * 
 * See if it is possible to do 0 to N swaps to get from string1 to string2
 * @author sizu
 *
 * We will stick with characters in set ABCDE
 *
 */
public class FindIfSwapPathExistsAndGivePath {
	
	public static void main(String[] args) {
		// This is false because input and output of swap is same length
		System.out.println("Map does not exist false=="+doesSwapPathExist("ABCA", "ABC"));
		
		// This is false because A in original string must map to A and D in final string
		System.out.println("Map does not exist false=="+doesSwapPathExist("ABCA", "ABCD"));
		
		// This is false because A in original string must map to A and D in final string
		// When the original set contains all letters, the first non trivial swap will then be a set of 4 letters
		// from then on, there will only be 4 or less letters after each additional swap
		System.out.println("Cycle with no extra characters false=="+doesSwapPathExist("ABCDE", "EBCDA"));
		
		System.out.println("Map does not exist false=="+doesSwapPathExist("AAAAE", "EAAAA"));
		
		System.out.println("0 swaps true=="+doesSwapPathExist("ABCDE", "ABCDE"));
		
		System.out.println("All swap to A true=="+doesSwapPathExist("ABCDE", "AAAAA"));
		
		// First find any character missing in the input that is in the output
		// swap it to the output
		// A->E
		// EBCD
		// B->A
		// EACD
		// C->B
		// EABD
		// D->C
		// EABC
		System.out.println("Must use missing character as temp true=="+doesSwapPathExist("ABCD", "EABC"));

		// A->E
		// EBCD
		// B->A, C->A, D->A
		// EAAA
		System.out.println("Must free up character before mapping to it true=="+doesSwapPathExist("ABCD", "EAAA"));
		
		// Find anything that maps to itself
		// swap anything to that will map to it in the output
		// A maps to itself
		// B->A, C->A, D->A
		System.out.println("All map to A true=="+doesSwapPathExist("ABCD", "AAAA"));
		
		System.out.println("0 swaps true=="+doesSwapPathExist("ABCDE", "ABCDE"));
		
		System.out.println("1 swap needed true=="+doesSwapPathExist("ABCDE", "ABCDD"));
		
		System.out.println("1 swap needed true=="+doesSwapPathExist("ABCDE", "BBCDE"));
		
		System.out.println("free up extra character for use in cycle, true=="+doesSwapPathExist("ABCDE", "AADEC"));
		
		System.out.println("Cannot free up extra character false=="+doesSwapPathExist("ABCDE", "ACDEC"));
		
		System.out.println("Non trivial swap true=="+doesSwapPathExist("ABCDE", "BBCDE"));
	}

	static int MAX_SIZE = 5;
	
	private static boolean doesSwapPathExist(String string1, String string2) {
		System.out.println("doesSwapPathExist string1:"+string1+" string2:"+string2);
		Map<Character, Character> charToCharMap = findMap(string1, string2);
		if(charToCharMap == null) {
			System.out.println("Map does not exist - false");
			return false;
		}
		
		String tempString = string1;
		Set<Character> charactersInTempString = new HashSet<Character>();
		for(int i=0; i<tempString.length(); i++) {
			Character tempChar = tempString.charAt(i);
			charactersInTempString.add(tempChar);
		}
		
		boolean didSomething = true;
		while(didSomething) {
			didSomething = false;
			
			// Resolve items that map to themselves
			for(Object inputObject: charToCharMap.keySet().toArray()) {
				Character input = (Character) inputObject;
				
				List<Character> flow = new ArrayList<Character>();
				while(charToCharMap.get(input) != null && !flow.contains(input)) {
					flow.add(input);
					input = charToCharMap.get(input);
				}
				
				// At this point input either is resolved (charToCharMap.get(input) == null)
				// Or input is in the flow
				
				// Character does not map to anything, already resolved - flow will be size 0
				if(flow.size() == 0) { // Character doesn't map to anything
					continue;
				}
				
				// A maps to A, flow will be size 1
				// A maps to B maps to null, flow will be size 1, flow will not contain B
				
				// Trivial swap, just ignore
				if(flow.contains(input) && flow.size() == 1) { // Cycle of length 1
//					System.out.println("Trivial swap flow "+flow.toString());
					charToCharMap.remove(input); 
					didSomething = true;
					continue; // Move to next character
				}
				
				if(flow.contains(input)) { // Cycle
					Character availableCharacter = findAvailableCharacter(tempString, charactersInTempString); 
					if(availableCharacter != null) {
						System.out.println("Found Available Character return true HERE");
						Character needsToMapTo = charToCharMap.get(input);
						charToCharMap.put(input, availableCharacter);
						charToCharMap.put(availableCharacter, needsToMapTo);
						flow.add(availableCharacter);
					} else {
						continue; // No extra character to resolve cycle, move to next character
					}
				}
				
				System.out.println("Creating an extra character return true HERE flow.size():"+flow.size()+" flow:"+flow.toString());
				// Resolve a ton
				for(int i=flow.size() - 1; i> -1; i--) {
					Character flowChar = flow.get(i);
					tempString = makeSwap(tempString, charactersInTempString, flowChar, charToCharMap);
					didSomething = true;
				}
			}
		}
		
		if(charToCharMap.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	private static String makeSwap(String tempString, Set<Character> charactersInTempString, Character input, Map<Character, Character> charToCharMap
			) {
		Character output = charToCharMap.get(input);
		tempString = tempString.replaceAll(""+input, ""+output);
		System.out.println(input+"->"+output+" tempString:"+tempString);
		charToCharMap.remove(input);
		charactersInTempString.remove(input);
		charactersInTempString.add(output);
		return tempString;
	}

	private static Character findAvailableCharacter(String tempString, Set<Character> charactersInTempString) {
		String characters = "ABCDE";
		for(int i=0; i<5; i++) {
			Character character = characters.charAt(i);
			if(!charactersInTempString.contains(character)) {
				return character;
			}
		}
		return null;
	}

	private static Map findMap(String string1, String string2) {
		Map<Character, Character> charToCharMap = new HashMap<Character, Character>();
		if(string1 == null && string2 == null) {
			return charToCharMap; // Any characters not in the map are mapped to themselves
		} else if (string1 == null) {
			return null;
		} else if (string2 == null) {
			return null;
		} else if (string1.length() != string2.length()) {
			return null;
		}
		for(int i=0; i<string1.length(); i++) {
			Character character1 = string1.charAt(i);
			Character character2 = string2.charAt(i);
			Character currCharacter2 = charToCharMap.get(character1);
			if(currCharacter2 == null) {
				charToCharMap.put(character1, character2);
			} else if (!character2.equals(currCharacter2)) {
				return null;
			}
		}
		return charToCharMap;
	}
}
