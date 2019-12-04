package string;

public class StringCompression {
	public static void main(String[] args) {
		System.out.println(":"+compressString("aaaabbbbb"));
		System.out.println(":"+compressString("aabcccccaaa"));
		System.out.println(":"+compressString("abcdalf"));
	}

	private static String compressString(String string) {
		if(string == null || string.length() == 0) {
			return string;
		}
		StringBuilder sb = new StringBuilder();
		Character previousCharacter = string.charAt(0);
		Integer previousCharacterCount = 1;
		for(int i=1; i<string.length(); i++) {
			Character currentCharacter = string.charAt(i);
			if(currentCharacter == previousCharacter) {
				previousCharacterCount++;
			} else {
				appendPreviousCharacterAndCount(sb, previousCharacter, previousCharacterCount);
				previousCharacter = currentCharacter;
				previousCharacterCount = 1;
			}
		}
		appendPreviousCharacterAndCount(sb, previousCharacter, previousCharacterCount);

		if(sb.length() < string.length()) {
			return sb.toString();
		} else {
			return string;
		}
	}
	
	// Can be modified to check for 1 count.  If it is 1, it could be left off
	// This would ensure the final string is always shorter than the original string
	public static void appendPreviousCharacterAndCount(StringBuilder sb, Character previousCharacter, Integer previousCharacterCount) {
		sb.append(previousCharacter);
		sb.append(previousCharacterCount);
	}
}
