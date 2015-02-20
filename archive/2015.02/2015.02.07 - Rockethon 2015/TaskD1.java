package net.spak;

import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD1 {
	private static final int INF = (int) 1e8;

	class Node {
		final int id;
		Node left;
		Node right;

		Node(int id) {
			this.id = id;
		}
	}
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int c = in.readInt();
		Graph graph = new Graph(n, c);
		for (int i = 0; i < c; ++i) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			if (from >= to) {
				out.printLine("IMPOSSIBLE");
				return;
			}
			String s = in.readString();
			if ("LEFT".equals(s)) {
				graph.addWeightedEdge(from, to, 1);
			} else {
				graph.addWeightedEdge(from, to, 2);
			}
		}
		Node root = calc(graph, 0, n - 1);
		if (root == null) {
			out.printLine("IMPOSSIBLE");
		} else {
			StringBuilder sb = new StringBuilder();
			printAns(root, sb);
			out.printLine(sb);
		}
    }

	private void printAns(Node root, StringBuilder sb) {
		if (root.left != null) {
			printAns(root.left, sb);
		}
		if (sb.toString().length() > 0) {
			sb.append(" ");
		}
		sb.append(root.id + 1);
		if (root.right != null) {
			printAns(root.right, sb);
		}
	}

	private Node calc(Graph graph, int l, int r) {
		if (l > r) return null;
		Node root = new Node(l++);
		if (l > r) return root;
		int l1 = INF, r1 = -INF, l2 = INF, r2 = -INF;
		for (Edge edge : graph.outbound(root.id)) {
			int u = edge.getDestination();
			long type = edge.getWeight();
			if (type == 1) {
				l1 = Math.min(l1, u);
				r1 = Math.max(r1, u);
			} else {
				l2 = Math.min(l2, u);
				r2 = Math.max(r2, u);
			}
		}
		l1 = Math.min(l1, l);
		r2 = Math.max(r2, r);
		if (r1 >= l2) return null;
		while (true) {
			boolean extended = false;
			for (int v = l; v <= r; ++v) {
				for (Edge edge : graph.outbound(v)) {
					int to = edge.getDestination();
					if (to <= r1) {
						if (r1 < v) {
							r1 = v;
							extended = true;
						}
					}
					if (to >= l2) {
						if (v < l2) {
							l2 = v;
							extended = true;
						}
					}
				}
			}
			if (!extended) break;
		}
		if (r1 >= l2) return null;
		if (r1 == -INF && l2 == INF) {
			l1 = r1 = l;
		}
		if (r1 != -INF) {
			while (r1 + 1 < Math.min(l2, r + 1)) ++r1;
			root.left = calc(graph, l1, r1);
			if (root.left == null) return null;
		}
		if (l2 != INF) {
			while (Math.max(r1 + 1, l) < l2) --l2;
			root.right = calc(graph, l2, r2);
			if (root.right == null) return null;
		}
		return root;
	}
}
