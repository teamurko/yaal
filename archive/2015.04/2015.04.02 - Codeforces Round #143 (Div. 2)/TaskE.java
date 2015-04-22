package net.spak;

import net.egork.collections.Pair;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	private static final int UNDEFINED = -1;
	private static final int LOGMAX = 20;
	private static final int MOD = 1000000007;
	int[][] up;
	int[][] cnt;
	int time;
	int[] tin;
	int[] tout;
	int[] weight;
	int[] id;
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		Graph graph = new BidirectionalGraph(n, m);
		for (int i = 0; i < m; ++i) {
			int u = in.readInt() - 1;
			int v = in.readInt() - 1;
			graph.addSimpleEdge(u, v);
		}
		Pair<Graph, int[]> contracted = contract(graph);
		weight = contracted.second;
		Graph cg = contracted.first;
		up = new int[n][LOGMAX];
		ArrayUtils.fill(up, UNDEFINED);
		tin = new int[n];
		tout = new int[n];
		time = 0;
		cnt = new int[n][LOGMAX];
		calcAnscestors(0, UNDEFINED, cg, new boolean[n]);
		int numQueries = in.readInt();
		for (int i = 0; i < numQueries; ++i) {
			int u = in.readInt() - 1;
			u = id[u];
			int v = in.readInt() - 1;
			v = id[v];
			long ans = 1;
			int p = commonAncestor(u, v);
			for (int j = LOGMAX - 1; j >= 0; --j) {
				if (up[u][j] != UNDEFINED && !isAncestor(up[u][j], p)) {
					ans = (ans * cnt[u][j]) % MOD;
					u = up[u][j];
				}
				if (up[v][j] != UNDEFINED && !isAncestor(up[v][j], p)) {
					ans = (ans * cnt[v][j]) % MOD;
					v = up[v][j];
				}
			}
			if (v != p) ans = (ans * weight[v]) % MOD;
			if (u != p) ans = (ans * weight[u]) % MOD;
			ans = (ans * weight[p]) % MOD;
			out.printLine(ans);
		}
    }

	private int commonAncestor(int u, int v) {
		if (isAncestor(u, v)) return u;
		if (isAncestor(v, u)) return v;
		for (int i = LOGMAX - 1; i >= 0; --i) {
			if (up[u][i] == UNDEFINED) continue;
			if (!isAncestor(up[u][i], v)) {
				u = up[u][i];
			}
		}
		return up[u][0];
	}

	private void calcAnscestors(int v, int p, Graph cg, boolean[] used) {
		tin[v] = time++;
		used[v] = true;
		up[v][0] = p;
		cnt[v][0] = weight[v];
		for (int i = 1; i < LOGMAX; ++i) {
			int u = up[v][i - 1];
			cnt[v][i] = cnt[v][i - 1];
			if (u != UNDEFINED) {
				up[v][i] = up[u][i - 1];
				cnt[v][i] = (cnt[v][i] * cnt[u][i - 1]) % MOD;
			}
		}
		for (Edge edge : cg.outbound(v)) {
			int u = edge.getDestination();
			if (u != p && !used[u]) {
				calcAnscestors(u, v, cg, used);
			}
		}
		tout[v] = time++;
	}
	
	boolean isAncestor(int u, int v) {
		return tin[u] <= tin[v] && tout[v] <= tout[u];
	}

	private Pair<Graph, int[]> contract(Graph graph) {
		int n = graph.vertexCount();
		id = new int[n];
		int[] color = new int[n];
		dfs(0, UNDEFINED, graph, id, color);
		Graph g = new BidirectionalGraph(n, graph.edgeCount());
		int[] type = ArrayUtils.createArray(n, 1);
		for (int v = 0; v < n; ++v) {
			for (Edge edge : graph.outbound(v)) {
				int u = edge.getDestination();
				if (id[v] != id[u]) {
					g.addSimpleEdge(id[v], id[u]);
				} else {
					type[id[v]] = 2;
				}
			}
		}
		return Pair.makePair(g, type);
	}

	private static void dfs(int v, int p, Graph g, int[] id, int[] color) {
		color[v] = 1;
		id[v] = v;
		for (Edge edge : g.outbound(v)) {
			int u = edge.getDestination();
			if (u == p) continue;
			if (color[u] == 2) continue;
			if (color[u] == 1) {
				id[v] = id[u];
				continue;
			}
			dfs(u, v, g, id, color);
			if (id[u] != u) {
				id[v] = id[u];
			}
		}
		color[v] = 2;
	}
}
