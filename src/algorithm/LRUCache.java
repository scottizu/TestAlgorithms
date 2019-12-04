package algorithm;

import java.util.ArrayDeque;
import java.util.Iterator;

public class LRUCache {

	public static void main(String[] args) {
		LRUCacheImpl cacheImpl = new LRUCacheImpl();
		cacheImpl.hit(4);
		cacheImpl.print();
		cacheImpl.hit(7);
		cacheImpl.print();
		cacheImpl.hit(8);
		cacheImpl.print();
		cacheImpl.hit(2);
		cacheImpl.print();
		cacheImpl.hit(10);
		cacheImpl.print();
		cacheImpl.hit(8);
		cacheImpl.print();
		cacheImpl.hit(2);
		cacheImpl.print();
		cacheImpl.hit(50);
		cacheImpl.print();
	}
	
	public static class LRUCacheImpl {
		ArrayDeque<Integer> cache = new ArrayDeque<Integer>();
		Integer size = 4;
		
		// If the queue contains the item being access, remove it and move it to the back of the queue
		// If the queue is greater than the max size, remove an item
		//   This can be modified if the items are variable size and the queue has a max size
		//   Before adding the new item, you can free up appropriate space by removing items from the front of the queue
		public void hit(Integer i) {
			if(cache.contains(i)) {
				cache.remove(i);
			} else {
				if(cache.size() >= size) {
					cache.poll();
				}
			} 
			cache.offer(i);
		}
		
		// Iterate over the queue and print the items in the queue
		public void print() {
			Iterator<Integer> iterator = cache.iterator();
			String out = "";
			String del = "";
			while(iterator.hasNext()) {
				Integer integer = iterator.next();
				out = out + del + integer;
				del = ",";
			}
			System.out.println("out:"+out);
		}
	}
}
