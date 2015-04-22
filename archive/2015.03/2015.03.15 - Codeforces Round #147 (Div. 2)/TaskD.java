package net.spak;

import net.egork.collections.Pair;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskD {
	private static final int UNDEFINED = -1;

	class Node {
		final int id;
		final int a;
		final int b;

		Node(int id, int a, int b) {
			this.id = id;
			this.a = a;
			this.b = b;
		}
	}
	List<Pair<Integer, Integer>> edges = new ArrayList<>();
	List<Node> vertices = new ArrayList<>();
	int count;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		Graph tree = new BidirectionalGraph(n, n);
		for (int i = 0; i < n - 1; ++i) {
			tree.addSimpleEdge(in.readInt() - 1, in.readInt() - 1);
		}
		dfs(0, UNDEFINED, tree);
		out.printLine(vertices.size());
		for (Node node : vertices) {
			out.printLine(2, node.a + 1, node.b + 1);
		}
		for (Pair<Integer, Integer> edge : edges) {
			out.printLine(edge.first + 1, edge.second + 1);
		}
    }

	private Node dfs(int v, int p, Graph tree) {
		List<Node> ch = new ArrayList<>();
		for (Edge e : tree.outbound(v)) {
			int u = e.getDestination();
			if (u != p) {
				Node rnode = dfs(u, v, tree);
				Node node = new Node(count++, u, v);
				vertices.add(node);
				if (rnode != null) {
					edges.add(Pair.makePair(rnode.id, node.id));
				}
				ch.add(node);
			}
		}
		if (ch.isEmpty()) return null;
		for (int i = 0; i + 1 < ch.size(); ++i) {
			edges.add(Pair.makePair(ch.get(i).id, ch.get(i + 1).id));
		}
		return ch.get(0);
	}
}
