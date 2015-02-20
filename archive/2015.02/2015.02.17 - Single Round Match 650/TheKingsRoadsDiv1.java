package net.spak;

import net.egork.misc.ArrayUtils;

import java.util.*;

public class TheKingsRoadsDiv1 {

	public static final String CORRECT = "Correct";
	public static final String INCORRECT = "Incorrect";
	private static final int UNDEFINED = -1;

	class Edge implements Comparable<Edge> {
		final int id;
		final int to;

		Edge(int id, int to) {
			this.id = id;
			this.to = to;
		}

		@Override
		public int compareTo(Edge o) {
			return id - o.id;
		}
	}
    public String getAnswer(int h, int[] a, int[] b) {
		int n = (1 << h) - 1;
		List<Edge>[] graph = new List[n];
		for (int i = 0; i < n; ++i) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < a.length; ++i) {
			int u = a[i] - 1;
			int v = b[i] - 1;
			if (u == v) continue;
			graph[v].add(new Edge(i, u));
			graph[u].add(new Edge(i, v));
		}
		for (int root = 0; root < n; ++root) {
			int[] tree = ArrayUtils.createArray(n, UNDEFINED);
			int[] pos = ArrayUtils.createArray(n, UNDEFINED);
			pos[root] = 0;
			tree[0] = root;
			if (dfs(0, graph, tree, pos)) {
				return CORRECT;
			}
		}
		return INCORRECT;
    }


	private boolean dfs(int idx, List<Edge>[] graph, int[] tree, int[] pos) {
		if (idx == graph.length) {
			return true;
		}
		Set<Integer> cSet = new HashSet<>();
		int v = tree[idx];
		for (Edge edge : graph[v]) {
			if (pos[edge.to] != UNDEFINED) continue;
			cSet.add(edge.to);
		}
		if (leftChild(idx) >= tree.length) {
			return dfs(idx + 1, graph, tree, pos);
		}
		List<Integer> candidates = new ArrayList<>(cSet);
		if (cSet.size() < 2) return false;
		if (cSet.size() > 5) return false;
		int l = leftChild(idx);
		int r = rightChild(idx);
		for (int mask = 1; mask < 1 << candidates.size(); ++mask) {
			if (Integer.bitCount(mask) != 2) continue;
			int a = UNDEFINED;
			int b = UNDEFINED;
			for (int i = 0; i < candidates.size(); ++i) {
				if ((mask & (1 << i)) > 0) {
					if (a == UNDEFINED) a = candidates.get(i);
					else b = candidates.get(i);
				}
			}
			tree[l] = a;
			tree[r] = b;
			pos[a] = l;
			pos[b] = r;
			if (dfs(idx + 1, graph, tree, pos)) {
				return true;
			}
			pos[a] = UNDEFINED;
			pos[b] = UNDEFINED;
		}
		return false;
	}

	private int rightChild(int v) {
		return (v + 1) << 1;
	}

	private int leftChild(int v) {
		return (v << 1) + 1;
	}

}
