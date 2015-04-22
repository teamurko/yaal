package net.spak;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskE {
	private static final int UNDEFINED = -1;
	private static final int INF = (int) 1e5;

	class Edge {
		int to;
		int flow;
		int capacity;
		int cost;
		Edge rev;

		public Edge(int to, int flow, int capacity, int cost, Edge rev) {
			this.to = to;
			this.flow = flow;
			this.capacity = capacity;
			this.cost = cost;
			this.rev = rev;
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		List<Edge>[] graph = new List[n];
		int[][] capacity = new int[n][n];
		for (int i = 0; i < n; ++i) {
			graph[i] = new ArrayList<>();
		}
		for (int u = 0; u < n; ++u) {
			for (int v = 0; v < n; ++v) {
				int cap = in.readInt();
				capacity[u][v] = cap;
				if (u != v && cap > 0) {
					Edge e = new Edge(v, 0, cap, 0, null);
					Edge rev = new Edge(u, 0, 0, 0, e);
					e.rev = rev;
					graph[u].add(e);
					graph[v].add(rev);
				}
			}
		}
		int flow = maxFlow(graph, 0, n - 1);
		for (int u = 0; u < n; ++u) {
			for (int v = 0; v < n; ++v) {
				if (v != u && capacity[u][v] > 0) {
					Edge e = new Edge(v, 0, 1000000, 1, null);
					Edge rev = new Edge(u, 0, 0, -1, e);
					e.rev = rev;
					graph[u].add(e);
					graph[v].add(rev);
				}
			}
		}
		flow += minCostMaxFlow(graph, 0, n - 1, k);
		out.printLine(flow);
	}

	private int minCostMaxFlow(List<Edge>[] graph, int source, int target, int costLimit) {
		int result = 0;
		int n = graph.length;
		int[] queue = new int[n + 2];
		int[] phi = new int[n];
		while (true) {
			int[] dist = ArrayUtils.createArray(n, INF);
			dist[source] = 0;
			Edge[] prev = new Edge[n];
			boolean[] used = new boolean[n];
			while (true) {
				int u = UNDEFINED;
				for (int v = 0; v < n; ++v) {
					if (dist[v] < INF && !used[v]) {
						if (u == UNDEFINED || dist[u] > dist[v]) u = v;
					}
				}
				if (u == UNDEFINED) break;
				used[u] = true;
				for (Edge edge : graph[u]) {
					if (edge.capacity - edge.flow > 0) {
						int w = dist[u] + edge.cost + phi[u] - phi[edge.to];
						assert w >= 0;
						if (dist[edge.to] > w) {
							dist[edge.to] = w;
							prev[edge.to] = edge;
						}
					}
				}
			}
			if (prev[target] == null) break;
			costLimit -= dist[target] + phi[target];

			if (costLimit < 0) break;
			for (int i = 0; i < n; ++i) {
				phi[i] = dist[i];
			}
			result++;
			int v = target;
			while (v != source) {
				Edge edge = prev[v];
				edge.flow += 1;
				edge.rev.flow -= 1;
				v = edge.rev.to;
			}
		}
		return result;
	}

	private int maxFlow(List<Edge>[] graph, int source, int target) {
		int result = 0;
		int flow;
		boolean[] used = new boolean[target + 1];
		for (int limit = 1000000; limit > 0; limit >>= 1) {
			while ((flow = maxFlow(graph, used, source, target, INF, limit)) > 0) {
				result += flow;
			}
		}
		return result;
	}

	private int maxFlow(List<Edge>[] graph, boolean[] used, int v, int target, int minFlow, int limit) {
		if (v == target) {
			return minFlow;
		}
		used[v] = true;
		for (Edge edge : graph[v]) {
			if (!used[edge.to] && edge.capacity - edge.flow >= limit) {
				int flow = maxFlow(graph, used, edge.to, target, Math.min(minFlow, edge.capacity - edge.flow), limit);
				if (flow > 0) {
					edge.flow += flow;
					edge.rev.flow -= flow;
					return flow;
				}
			}
		}
		return 0;
	}
}
