package string;

import java.util.ArrayDeque;

public class StringReplaceSpaceWithPercent20 {
	public static void main(String[] args) {
		char[] characters = new char[15];
		putStringInArray("test it out", characters);
		System.out.println("replace:"+printCharacterArray(replaceSpaceWithPercent20(characters)));
	}
	

	private static String printCharacterArray(char[] characterArray) {
		StringBuilder out = new StringBuilder();
		for(int i=0; i<characterArray.length; i++) {
			out.append(characterArray[i]);
		}
		return out.toString();
	}


	private static void putStringInArray(String string, char[] characters) {
		for(int i=0; i<string.length(); i++) {
			characters[i] = string.charAt(i);
		}
	}


	private static char[] replaceSpaceWithPercent20(char[] characterArray) {
		ArrayDeque<Character> charsWaiting = new ArrayDeque();
		for(int i=0; i<characterArray.length; i++) {
			char character = characterArray[i];
			if(character ==' ') {
				charsWaiting.offer('%');
				charsWaiting.offer('2');
				charsWaiting.offer('0');
			} else {
				charsWaiting.offer(character);
			}
			characterArray[i] = charsWaiting.poll();
		}
		return characterArray;
	}
}
