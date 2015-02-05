package on2015_02.on2015_02_02_Codeforces_Round__290__Div__1_.C___Fox_And_Dinner;



import net.egork.collections.CollectionUtils;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.*;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = new int[n];
		boolean[][] match = new boolean[n][];
		Graph graph = new Graph(n + 2, n * n + 2 * n);
		for (int i = 0; i < n; ++i) {
			match[i] = new boolean[n];
			a[i] = in.readInt();
		}
		int oddCount = 0;
		for (int i = 0; i < n; ++i) {
			if (a[i] % 2 == 1) oddCount++;
			for (int j = 0; j < n; ++j) {
				match[i][j] = IntegerUtils.isPrime(a[i] + a[j]);
			}
		}
		if (oddCount * 2 != n) {
			out.printLine("Impossible");
			return;
		}
		int source = 0;
		int target = n + 1;
		for (int i = 0; i < n; ++i) {
			if (a[i] % 2 == 1) {
				graph.addFlowEdge(source, i + 1, 2);
			} else {
				graph.addFlowEdge(i + 1, target, 2);
			}
			if (a[i] % 2 == 0) continue;
			for (int j = 0; j < n; ++j) {
				if (a[j] % 2 == 1) continue;
				if (match[i][j]) {
					graph.addFlowEdge(i + 1, j + 1, 1);
				}
			}
		}
		long flow = MaxFlow.dinic(graph, source, target);
		if (flow != n) {
			out.printLine("Impossible");
			return;
		}
		Graph conGraph = new BidirectionalGraph(n, n);
		RecursiveIndependentSetSystem dsu = new RecursiveIndependentSetSystem(n);
		int numEdgesAdded = 0;
		for (int edgeId = 0; edgeId < graph.edgeCount(); ++edgeId) {
			Edge edge = graph.edge(edgeId);
			if (edge.getSource() == source || edge.getSource() == target) continue;
			if (edge.getDestination() == source || edge.getDestination() == target) continue;
			if (!match[edge.getSource() - 1][edge.getDestination() - 1]) continue;
			if (a[edge.getSource() - 1] % 2 == 0) continue;
			if (graph.flow(edgeId) == 1) {
				int u = edge.getSource() - 1;
				int v = edge.getDestination() - 1;
				conGraph.addSimpleEdge(u, v);
				++numEdgesAdded;
				dsu.join(u, v);
			}
		}
		int numGroups = getNumGroups(dsu, n);
		boolean[] used = ArrayUtils.createArray(n, false);
		out.printLine(numGroups);
		for (int v = 0; v < n; ++v) {
			if (used[v]) continue;
			List<Integer> ids = new ArrayList<>();
			GraphAlgorithms.dfs(conGraph, v, used, ids);
			out.print(ids.size());
			for (Integer u : ids) {
				out.print(" ");
				out.print(u);
			}
			for (int i = 0; i < ids.size(); ++i) {
				int aa = ids.get(i);
				int b = ids.get((i + 1) % ids.size());
				if (!match[aa][b]) {
					throw new RuntimeException();
				}
			}
			out.printLine();
		}
	}

	private int getNumGroups(RecursiveIndependentSetSystem dsu, int n) {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < n; ++i) {
			set.add(dsu.get(i));
		}
		return set.size();
	}

	private int[] makeOrder(
		List<Integer> group,
		RecursiveIndependentSetSystem dsu,
		boolean[][] match
	) {
		int[] res = new int[group.size()];
		int cnt = 0;
		res[cnt++] = group.get(0);
		Set<Integer> used = new HashSet<>();
		used.add(group.get(0));
		while (used.size() != group.size()) {
			for (Integer candidate : group) {
				if (!used.contains(candidate) && match[res[cnt - 1]][candidate]) {
					used.add(candidate);
					res[cnt++] = candidate;
				}
			}
		}
		assert (res.length == cnt);
		return res;
	}
}
