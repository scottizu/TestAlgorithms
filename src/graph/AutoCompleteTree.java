package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Autocomplete Tree
 * rootNode is a null value and leaf nodes are null values
 * Each letter is a child and depth search from root to leaf becomes a word
 * 
 * @author sizu
 *
 */
public class AutoCompleteTree {

	public static void main(String[] args) {
		List<String> dictionary = new ArrayList<String>();
		dictionary.add("mark");
		dictionary.add("married");
		dictionary.add("mama");
		dictionary.add("pot");
		dictionary.add("potato");
		dictionary.add("pasta");
		
		Node tree = buildTree(dictionary);
		printTree(tree);
		List<String> words = findWords(tree, "");
		System.out.println("words:"+words); // Returns all
		List<String> words2 = findWords(tree, "pot");
		System.out.println("words2:"+words2);
		List<String> words3 = findWords(tree, "mar");
		System.out.println("words3:"+words3);
		List<String> words4 = findWords(tree, "p");
		System.out.println("words4:"+words4);
	}

	private static List<String> findWords(Node tree, String string) {
		List<String> words = new ArrayList<String>();
		Node currNode = tree;
		for(int i = 0; i<string.length(); i++) {
			Character character = string.charAt(i);
			Node nextNode = currNode.childrenNode.get(character);
			if(nextNode == null) {
				return words; // No words found
			}
			currNode = nextNode;
		}
		
		// DFS Search with building string
		ArrayDeque<Node> stack = new ArrayDeque<Node>();
		stack.push(currNode);
		while(!stack.isEmpty()){
			Node node = stack.pop();
			if(node.value == null && !node.parentCharacters.isEmpty()) { // value == null signifies end of word, Don't add empty string
				words.add(node.parentCharacters);
			}
			for(Node childNode: node.childrenNode.values()) {
				stack.push(childNode);
			}
		}
		
		return words;
	}

	public static Node buildTree(List<String> dictionary) {
		Node rootNode = new Node();
		rootNode.depth = 0;
		
		for(String string: dictionary) {
			Node currNode = rootNode;
			for(int i = 0; i<string.length(); i++) {
				Character character = string.charAt(i);
				Node nextNode = currNode.childrenNode.get(character);
				if(nextNode == null) {
					nextNode = new Node();
					nextNode.value = character;
					nextNode.depth = currNode.depth + 1;
					nextNode.parentCharacters = currNode.parentCharacters + currNode.displayCharacter;
					nextNode.displayCharacter = character.toString();
					currNode.childrenNode.put(character, nextNode);
				}
				currNode = nextNode;
			}
			Node nullNode = new Node();
			nullNode.depth = currNode.depth + 1;
			nullNode.parentCharacters = currNode.parentCharacters + currNode.displayCharacter;
			currNode.childrenNode.put(null, nullNode); // Signify end
		}
		return rootNode;
	}
	
	private static void printTree(Node rootNode) {
		Map<Integer, StringBuilder> outputStrings = new LinkedHashMap<Integer, StringBuilder>();
		ArrayDeque<Node> queue = new ArrayDeque<Node>();
		queue.offer(rootNode);
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			addToMap(node.depth,  " "+node.parentCharacters+":"+node.displayCharacter, outputStrings);
			for(Node childNode: node.childrenNode.values()) {
				queue.add(childNode);
			}
		}
		
		for(StringBuilder value: outputStrings.values()) {
			System.out.println(value);
		}
	}

	private static void addToMap(Integer depth, String out, Map<Integer, StringBuilder> outputStrings) {
		StringBuilder curr = outputStrings.get(depth);
		if(curr == null) {
			curr = new StringBuilder();
			outputStrings.put(depth, curr);
		} 
		curr.append(out);
	}

	public static class Node {
		Integer depth = null;
		String parentCharacters = ""; // for null parent
		String displayCharacter = ""; // for null value
		
		Character value = null;
		Map<Character, Node> childrenNode = new HashMap<Character, Node>(); // Can also use Node array
	}
}

// OUTPUT
//:
//:p :m
//p:a p:o m:a
//pa:s po:t ma:r ma:m
//pas:t pot: pot:a mar:r mar:k mam:a
//past:a pota:t marr:i mark: mama:
//pasta: potat:o marri:e
//potato: marrie:d
//married:
//words:[mama, mark, married, potato, pot, pasta]
//words2:[potato, pot]
//words3:[mark, married]
//words4:[potato, pot, pasta]