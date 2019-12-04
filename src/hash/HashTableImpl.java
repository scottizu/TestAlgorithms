package hash;

import java.util.Iterator;
import java.util.LinkedList;

public class HashTableImpl<T extends Object> {
	int size = 10000;
	LinkedList[] storeArray = new LinkedList[size];
	
	public static void main(String[] args) {
		HashTableImpl<String> myHashTable = new HashTableImpl<String>();
		
		myHashTable.put("key1", "value1");
		myHashTable.put("key2", "value2");
		
		System.out.println("key1: "+myHashTable.get("key1"));

		myHashTable.put("key1", "value3");
		
		System.out.println("key1: "+myHashTable.get("key1"));
	}

	private void put(String key, String value) {
		int hashCode = key.hashCode();
		int location = hashCode % size;
		LinkedList<HashTableImplEntry> current = storeArray[location];
		// System.out.println("current:"+current);
		if(current == null) {
			current = new LinkedList<HashTableImplEntry>();
			storeArray[location] = current;
		} 
		HashTableImplEntry he = new HashTableImplEntry();
		he.key = key;
		he.value = value;
		
		// See if an Object already exists with the key
		Iterator lli = current.iterator();
		while(lli.hasNext()) {
			HashTableImplEntry next = (HashTableImplEntry) lli.next();
			if(next.key == key) {
				next.value = value;
				return;
			}
		}
		
		current.push(he);
	}
	
	private Object get(String key) {
		int hashCode = key.hashCode();
		int location = hashCode % size;
		LinkedList<HashTableImplEntry> current = storeArray[location];
		// System.out.println("current:"+current);
		if(current == null) {
			return null;
		} else {

			// See if an Object already exists with the key
			Iterator lli = current.iterator();
			while(lli.hasNext()) {
				HashTableImplEntry next = (HashTableImplEntry) lli.next();
				if(next.key == key) {
					return next.value;
				}
			}
		}
		return null;
	}
	
	public static class HashTableImplEntry {
		Object key;
		Object value;
	}
}
