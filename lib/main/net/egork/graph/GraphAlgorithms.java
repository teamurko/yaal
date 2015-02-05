package net.egork.graph;

import java.util.List;

/**
 * @author egor@egork.net
 */
public class GraphAlgorithms {
	public static int[] topologicalSort(Graph graph) {
		int count = graph.vertexCount();
		int[] queue = new int[count];
		int[] degree = new int[count];
		int size = 0;
		for (int i = 0; i < graph.edgeCount(); i++) {
			if (!graph.isRemoved(i))
				degree[graph.destination(i)]++;
		}
		for (int i = 0; i < count; i++) {
			if (degree[i] == 0)
				queue[size++] = i;
		}
		for (int i = 0; i < size; i++) {
			int current = queue[i];
			for (int j = graph.firstOutbound(current); j != -1; j = graph.nextOutbound(j)) {
				int next = graph.destination(j);
				if (--degree[next] == 0)
					queue[size++] = next;
			}
		}
		if (size != count)
			return null;
		return queue;
	}

	public static void dfs(Graph graph, int v, boolean[] used, List<Integer> visited) {
		used[v] = true;
		for (Edge edge : graph.outbound(v)) {
			if (!used[edge.getDestination()]) {
				dfs(graph, edge.getDestination(), used, visited);
			}
		}
		visited.add(v);
	}
}
