package algorithm;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class graphbfs {
	
	public static void main(String[] args) {
		GraphNode one = buildGraphOne();
		String out1 = bfs(one);
		System.out.println("out1:"+out1);
		
		GraphNode two = buildGraphTwo();
		String out2 = bfs(two);
		System.out.println("out2:"+out2);
	}

	private static String bfs(GraphNode start) {
		String out = "";
		String del = "";
		Set<GraphNode> visitedNodes = new HashSet<GraphNode>();
		ArrayDeque<GraphNode> nodesWhichHaveMayHaveUnprocessedNeighbors = new ArrayDeque<GraphNode>();
		nodesWhichHaveMayHaveUnprocessedNeighbors.add(start);
		
		out = out + del + start.value;
		del = " ";
		visitedNodes.add(start);
		
		while(!nodesWhichHaveMayHaveUnprocessedNeighbors.isEmpty()) {
			GraphNode analyzeNode = nodesWhichHaveMayHaveUnprocessedNeighbors.poll(); // Use Queue
			
			for(GraphNode neighbor: analyzeNode.neighbors) {
				if(!visitedNodes.contains(neighbor)) {
					// Guarantee analyzeNode has not been visited
					out = out + del + neighbor.value;
					del = " ";
					visitedNodes.add(neighbor);

					nodesWhichHaveMayHaveUnprocessedNeighbors.offer(neighbor); // Use Queue
				}
			}
		}
		return out;
	}

	public static class GraphNode {
		Integer value;
		Set<GraphNode> neighbors = new HashSet<GraphNode>();
	}
	
	private static GraphNode buildGraphOne() {
		// https://cdncontribute.geeksforgeeks.org/wp-content/uploads/bfs-5.png
		GraphNode zero = new GraphNode();
		zero.value = 0;
		GraphNode one = new GraphNode();
		one.value = 1;
		GraphNode two = new GraphNode();
		two.value = 2;
		GraphNode three = new GraphNode();
		three.value = 3;
		
		zero.neighbors.add(one);
		zero.neighbors.add(two);
		one.neighbors.add(two);
		two.neighbors.add(zero);
		two.neighbors.add(three);
		three.neighbors.add(three);
		
		return two;
	}

	private static GraphNode buildGraphTwo() {
		// https://en.wikipedia.org/wiki/Depth-first_search
		GraphNode one = new GraphNode();
		one.value = 1;
		GraphNode two = new GraphNode();
		two.value = 2;
		GraphNode three = new GraphNode();
		three.value = 3;
		GraphNode four = new GraphNode();
		four.value = 4;
		GraphNode five = new GraphNode();
		five.value = 5;
		GraphNode six = new GraphNode();
		six.value = 6;
		GraphNode seven = new GraphNode();
		seven.value = 7;
		GraphNode eight = new GraphNode();
		eight.value = 8;
		GraphNode nine = new GraphNode();
		nine.value = 9;
		GraphNode ten = new GraphNode();
		ten.value = 10;
		GraphNode eleven = new GraphNode();
		eleven.value = 11;
		GraphNode twelve = new GraphNode();
		twelve.value = 12;
		
		one.neighbors.add(two);
		one.neighbors.add(seven);
		one.neighbors.add(eight);
		two.neighbors.add(three);
		two.neighbors.add(six);
		three.neighbors.add(four);
		three.neighbors.add(five);
		eight.neighbors.add(nine);
		eight.neighbors.add(twelve);
		nine.neighbors.add(ten);
		nine.neighbors.add(eleven);
		
		
		return one;
	}
}
