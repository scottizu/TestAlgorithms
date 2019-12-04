package graph;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class DirectionalGraphRouteSToE {
	public static void main(String[] args) {
		GraphNode graphA = GraphNode.buildGraphA();
		GraphNode graphNode1 = findNode(graphA, 1);
		GraphNode graphNode7 = findNode(graphA, 7);
		GraphNode graphNode12 = findNode(graphA, 12);
		System.out.println("1:"+existsPath(graphNode1, graphNode7));
		System.out.println("2:"+existsPath(graphNode1, graphNode12));
		System.out.println("3:"+existsPath(graphNode7, graphNode1));
		System.out.println("4:"+existsPath(graphNode7, graphNode12));
		System.out.println("5:"+existsPath(graphNode12, graphNode1));
		System.out.println("6:"+existsPath(graphNode12, graphNode7));
	}

	private static GraphNode findNode(GraphNode root, int i) {
		if(root.value == i) {
			return root;
		}

		Set<GraphNode> visited = new HashSet<GraphNode>();
		ArrayDeque<GraphNode> queue = new ArrayDeque<GraphNode>();
		queue.offer(root);
		visited.add(root);
		while(!queue.isEmpty()) {
			GraphNode node = queue.pop();
			for(GraphNode neighbor: node.neighbors) {
				if(!visited.contains(neighbor)) {
					if(neighbor.value == i) {
						return neighbor;
					} else {
						queue.offer(neighbor);
						visited.add(neighbor);
					}
				}
			}
		}
		return null;
	}

	private static boolean existsPath(GraphNode S, GraphNode E) {
		if(S == E) {
			return true;
		}
		Set<GraphNode> visited = new HashSet<GraphNode>();
		ArrayDeque<GraphNode> queue = new ArrayDeque<GraphNode>();
		queue.offer(S);
		visited.add(S);
		while(!queue.isEmpty()) {
			GraphNode node = queue.pop();
			for(GraphNode neighbor: node.neighbors) {
				if(!visited.contains(neighbor)) {
	 				if(neighbor == E) {
						return true;
					} else {
						queue.offer(neighbor);
						visited.add(neighbor);
					}
				}
			}
		}
		
		return false;
	}
}
