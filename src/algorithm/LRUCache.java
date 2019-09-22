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
