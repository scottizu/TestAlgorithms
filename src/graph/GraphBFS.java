package graph;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class GraphBFS {
	
	public static void main(String[] args) {
		GraphNode one = GraphNode.buildGraphOne();
		String out1 = bfs(one);
		System.out.println("out1:"+out1);
		
		GraphNode two = GraphNode.buildGraphTwo();
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
}
