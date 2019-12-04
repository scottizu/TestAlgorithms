package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class SkipIter {

	public static void main(String[] args) {
		handleCase1();
		handleCase2();
		handleCase3();
	}

	private static void handleCase1() {
		System.out.println("handleCase1 1,3,8,8");
		Iterator<Integer> iter1 = Arrays.asList(1, 3, 8, 8).iterator();
		SkipIter skipIter1 = new SkipIter(iter1);
		System.out.println("1 - skipIter1.next():"+skipIter1.next());
		System.out.println("3 - skipIter1.next():"+skipIter1.next());
		skipIter1.skip(8);
		System.out.println("8 - skipIter1.next():"+skipIter1.next());
		System.out.println("false - skipIter1.hasNext():"+skipIter1.hasNext());
	}

	private static void handleCase2() {
		System.out.println("\nhandleCase2 8,1,3,8,8");
		Iterator<Integer> iter2 = Arrays.asList(8, 1, 3, 8, 8).iterator();
		SkipIter skipIter2 = new SkipIter(iter2);
		System.out.println("8 - skipIter2.next():"+skipIter2.next());
		System.out.println("1 - skipIter2.next():"+skipIter2.next());
		System.out.println("3 - skipIter2.next():"+skipIter2.next());
		skipIter2.skip(8);
		System.out.println("true - skipIter2.hasNext():"+skipIter2.hasNext());
		System.out.println("true - skipIter2.hasNext():"+skipIter2.hasNext());
		System.out.println("true - skipIter2.hasNext():"+skipIter2.hasNext());
		System.out.println("8 - skipIter2.next():"+skipIter2.next());
		System.out.println("false - skipIter2.hasNext():"+skipIter2.hasNext());
	}

	private static void handleCase3() {
		System.out.println("\nhandleCase3 8,1,3,8,8");
		Iterator<Integer> iter2 = Arrays.asList(8, 1, 3, 8, 8).iterator();
		SkipIter skipIter2 = new SkipIter(iter2);
		skipIter2.skip(8);
		skipIter2.skip(8);
		skipIter2.skip(8);
		System.out.println("true - skipIter2.hasNext():"+skipIter2.hasNext());
		System.out.println("1 - skipIter2.next():"+skipIter2.next());
		System.out.println("true - skipIter2.hasNext():"+skipIter2.hasNext());
		System.out.println("true - skipIter2.hasNext():"+skipIter2.hasNext());
		System.out.println("true - skipIter2.hasNext():"+skipIter2.hasNext());
		System.out.println("3 - skipIter2.next():"+skipIter2.next());
		System.out.println("false - skipIter2.hasNext():"+skipIter2.hasNext());
		System.out.println("false - skipIter2.hasNext():"+skipIter2.hasNext());
		System.out.println("false - skipIter2.hasNext():"+skipIter2.hasNext());
	}

	Integer cachedVal = null;
	Iterator<Integer> iter = null;
	Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
	public SkipIter(Iterator<Integer> iter) {
		this.iter = iter;
	}
	
	public Integer next() throws NoSuchElementException {
		// if there happens to be a cached val, return it, otherwise get the next val
		if(cachedVal != null) {
			Integer retVal = cachedVal;
			cachedVal = null;
			return retVal;
		} else {
			return getNextValidVal();
		}
	}
	
	private Integer getNextValidVal() throws NoSuchElementException {
		Integer nextVal = iter.next();
		while(countMap.containsKey(nextVal)) {
			decrementMap(nextVal);
			nextVal = iter.next();
		}
		return nextVal;
	}

	public boolean hasNext() {
		try {
			// Return true if there is a next val in cached val or if can get a next val
			if(cachedVal == null) {
				cachedVal = getNextValidVal();
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public void skip(Integer integer) {
		System.out.println("skip integer:"+integer);
		// If the integer to skip, happens to be the cached val, just remove the cached val, otherwise increment the map
		if(cachedVal == integer) {
			cachedVal = null;
		} else {
			incrementMap(integer);
		}
	}

	private void incrementMap(Integer integer) {
		Integer curr = countMap.get(integer);
		if(curr == null) {
			curr = 0;
		}
		curr++;
		countMap.put(integer, curr);
	}

	private void decrementMap(Integer integer) {
		Integer curr = countMap.get(integer);
		if(curr == 1) {
			countMap.remove(integer);
		} else {
			curr--;
			countMap.put(integer, curr);
		}
	}
}

// OUTPUT
//handleCase1 1,3,8,8
//1 - skipIter1.next():1
//3 - skipIter1.next():3
//skip integer:8
//8 - skipIter1.next():8
//false - skipIter1.hasNext():false
//
//handleCase2 8,1,3,8,8
//8 - skipIter2.next():8
//1 - skipIter2.next():1
//3 - skipIter2.next():3
//skip integer:8
//true - skipIter2.hasNext():true
//true - skipIter2.hasNext():true
//true - skipIter2.hasNext():true
//8 - skipIter2.next():8
//false - skipIter2.hasNext():false
//
//handleCase3 8,1,3,8,8
//skip integer:8
//skip integer:8
//skip integer:8
//true - skipIter2.hasNext():true
//1 - skipIter2.next():1
//true - skipIter2.hasNext():true
//true - skipIter2.hasNext():true
//true - skipIter2.hasNext():true
//3 - skipIter2.next():3
//false - skipIter2.hasNext():false
//false - skipIter2.hasNext():false
//false - skipIter2.hasNext():false
