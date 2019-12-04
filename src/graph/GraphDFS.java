package graph;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class GraphDFS {
	
	public static void main(String[] args) {
		GraphNode one = GraphNode.buildGraphOne();
		String one1 = dfs(one);
		System.out.println("one1:"+one1);
		
		GraphNode graphA = GraphNode.buildGraphA();
		String graphAOut = dfs(graphA);
		System.out.println("graphAOut:"+graphAOut);
	}

	/**
	 * Since this is using a stack push/pop, it can also be implemented using recursion
	 * @param start
	 * @return
	 */
	private static String dfs(GraphNode start) {
		String out = "";
		String del = "";
		Set<GraphNode> visitedNodes = new HashSet<GraphNode>();
		ArrayDeque<GraphNode> nodesWhichHaveMayHaveUnprocessedNeighbors = new ArrayDeque<GraphNode>();
		nodesWhichHaveMayHaveUnprocessedNeighbors.add(start);
		while(!nodesWhichHaveMayHaveUnprocessedNeighbors.isEmpty()) {
			GraphNode analyzeNode = nodesWhichHaveMayHaveUnprocessedNeighbors.pop(); // Use Stack
			if(!visitedNodes.contains(analyzeNode)) {
				// Guarantee analyzeNode has not been visited
				out = out + del + analyzeNode.value;
				del = " ";
				visitedNodes.add(analyzeNode);
				for(GraphNode neighbor: analyzeNode.neighbors) {
					nodesWhichHaveMayHaveUnprocessedNeighbors.push(neighbor); // Use Stack
				}
			}
		}
		return out;
	}
}
