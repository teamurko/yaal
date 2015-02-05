package net.egork.graph;

import net.egork.misc.ArrayUtils;

public class DFS {
	public static boolean[] visit(Graph graph, int root) {
		int count = graph.vertexCount();
		boolean[] visited = ArrayUtils.createArray(count, false);
		dfs(graph, root, visited);
		return visited;
	}
	private static void dfs(Graph graph, int vertex, boolean[] visited) {
		if (visited[vertex]) return;
		visited[vertex] = true;
		for (Edge edge : graph.outbound(vertex)) {
			dfs(graph, edge.getDestination(), visited);
		}
	}
}
