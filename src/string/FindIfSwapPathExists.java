package string;

import java.util.HashMap;
import java.util.Map;

/**
 * swap takes in a string as input and replaces all occurrences of one letter to another letter and returns the final string
 * 
 * See if it is possible to do 0 to N swaps to get from string1 to string2
 * @author sizu
 *
 * We will stick with characters in set ABCDE
 *
 */
public class FindIfSwapPathExists {
	
	public static void main(String[] args) {

		System.out.println("Both null true=="+doesSwapPathExist(null, null)+"\n");
		
		System.out.println("One string is null and the other is not false=="+doesSwapPathExist("ABCA", null)+"\n");
		
		// This is false because input and output of swap is same length
		System.out.println("Strings separate lengths false=="+doesSwapPathExist("ABCA", "ABC")+"\n");
		
		System.out.println("0 swaps true=="+doesSwapPathExist("ABCDE", "ABCDE")+"\n");
		
		// This is false because A in original string must map to A and D in final string
		System.out.println("Map does not exist false=="+doesSwapPathExist("ABCA", "ABCD")+"\n");
		
		System.out.println("Map does not exist false=="+doesSwapPathExist("AAAAE", "EAAAA")+"\n");
		
		// This is false because A in original string must map to A and D in final string
		// When the original set contains all letters, the first non trivial swap will then be a set of 4 letters
		// from then on, there will only be 4 or less letters after each additional swap
		System.out.println("Cycle with no extra characters false=="+doesSwapPathExist("ABCDE", "EBCDA")+"\n");
		
		System.out.println("All swap to A true=="+doesSwapPathExist("ABCD", "AAAA")+"\n");
		
		// First find any character missing in the input that is in the output
		// swap it to the output
		// A->E  EBCD
		// B->A  EACD
		// C->B  EABD
		// D->C  EABC
		System.out.println("Must use missing character as temp true=="+doesSwapPathExist("ABCD", "EABC")+"\n");

		// A->E  EBCD
		// B->A, C->A, D->A  EAAA
		System.out.println("Must free up character before mapping to it true=="+doesSwapPathExist("ABCD", "EAAA")+"\n");
		
		// Find anything that maps to itself
		// swap anything to that will map to it in the output
		// A maps to itself
		// B->A, C->A, D->A
		System.out.println("All map to A true=="+doesSwapPathExist("ABCDE", "AAAAA")+"\n");
		
		System.out.println("1 swap needed true=="+doesSwapPathExist("ABCDE", "ABCDD")+"\n");
		
		System.out.println("Free up extra character for use in cycle, true=="+doesSwapPathExist("ABCDE", "AADEC")+"\n");
		
		System.out.println("Cannot free up extra character false=="+doesSwapPathExist("ABCDE", "ACDEC")+"\n");
		
		System.out.println("Non trivial swap true=="+doesSwapPathExist("ABCDE", "BBCDE")+"\n");
	}

	static int MAX_SIZE = 5; // ABCDE
	
	private static boolean doesSwapPathExist(String string1, String string2) {
		System.out.println("doesSwapPathExist string1:"+string1+" string2:"+string2);
		
		if(string1 == string2) {
			System.out.println("Strings are equal (true)");
			return true;
		} else if (string1 == null || string2 == null) {
			System.out.println("One string is null, the other is not (false)");
			return false;
		} else if (string1.length() != string2.length()) {
			System.out.println("Strings are of different lengths (false)");
			return false;
		} else if (string1.equals(string2)) {
			System.out.println("Strings are equal (true)");
			return true;
		}
		
		Map<Character, Character> charToCharMap = findMap(string1, string2);
		if(charToCharMap == null) {
			System.out.println("Map does not exist (false)");
			return false;
		}

		Boolean characterIsAvailable = isACharacterAvailable(charToCharMap);
		if(characterIsAvailable) {
			System.out.println("Map exists and available character (true)");
			return true;
		}
		
		// All characters an in the charToCharMap since none are available
		
		Boolean canCreateAvailableCharacter = canCreateAvailableCharacter(charToCharMap);
		if(canCreateAvailableCharacter) {
			System.out.println("Map exists, no available character, a swap can make one available (true)");
			return true;
		} else {
			System.out.println("Map exists, no available character, no swap will make one available (false)");
			return false;
		}
	}

	private static Boolean canCreateAvailableCharacter(Map<Character, Character> charToCharMap) {
		for(Object inputObject: charToCharMap.keySet().toArray()) {
			Character input = (Character) inputObject;
			Character inputOutput = charToCharMap.get(input);
			Character inputOutputOutput = charToCharMap.get(inputOutput);

			// A -> B -> B means that we can free up A
			if(!input.equals(inputOutput) && inputOutput.equals(inputOutputOutput)) {
				return true;
			}
		}
		return false;
	}

	private static Boolean isACharacterAvailable(Map<Character, Character> charToCharMap) {
		return charToCharMap.size() < MAX_SIZE; // ABCDE
	}

	private static Map findMap(String string1, String string2) {
		Map<Character, Character> charToCharMap = new HashMap<Character, Character>();
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

// OUTPUT
//doesSwapPathExist string1:null string2:null
//Strings are equal (true)
//Both null true==true
//
//doesSwapPathExist string1:ABCA string2:null
//One string is null, the other is not (false)
//One string is null and the other is not false==false
//
//doesSwapPathExist string1:ABCA string2:ABC
//Strings are of different lengths (false)
//Strings separate lengths false==false
//
//doesSwapPathExist string1:ABCDE string2:ABCDE
//Strings are equal (true)
//0 swaps true==true
//
//doesSwapPathExist string1:ABCA string2:ABCD
//Map does not exist (false)
//Map does not exist false==false
//
//doesSwapPathExist string1:AAAAE string2:EAAAA
//Map does not exist (false)
//Map does not exist false==false
//
//doesSwapPathExist string1:ABCDE string2:EBCDA
//Map exists, no available character, no swap will make one available (false)
//Cycle with no extra characters false==false
//
//doesSwapPathExist string1:ABCD string2:AAAA
//Map exists and available character (true)
//All swap to A true==true
//
//doesSwapPathExist string1:ABCD string2:EABC
//Map exists and available character (true)
//Must use missing character as temp true==true
//
//doesSwapPathExist string1:ABCD string2:EAAA
//Map exists and available character (true)
//Must free up character before mapping to it true==true
//
//doesSwapPathExist string1:ABCDE string2:AAAAA
//Map exists, no available character, a swap can make one available (true)
//All map to A true==true
//
//doesSwapPathExist string1:ABCDE string2:ABCDD
//Map exists, no available character, a swap can make one available (true)
//1 swap needed true==true
//
//doesSwapPathExist string1:ABCDE string2:AADEC
//Map exists, no available character, a swap can make one available (true)
//Free up extra character for use in cycle, true==true
//
//doesSwapPathExist string1:ABCDE string2:ACDEC
//Map exists, no available character, no swap will make one available (false)
//Cannot free up extra character false==false
//
//doesSwapPathExist string1:ABCDE string2:BBCDE
//Map exists, no available character, a swap can make one available (true)
//Non trivial swap true==true