package net.spak;

import net.egork.collections.FenwickTree;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.TreeSet;

public class SimilarPair {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        final int n = in.readInt();
        final int maxDiff = in.readInt();
        Graph graph = new Graph(n);
        boolean[] hasInbound = ArrayUtils.createArray(n, false);
        for (int i = 0; i < n - 1; ++i) {
            int from = in.readInt();
            int to = in.readInt();
            --from;
            --to;
            graph.addSimpleEdge(from, to);
            hasInbound[to] = true;
        }
        int root = -1;
        for (int i = 0; i < n; ++i) {
            if (!hasInbound[i]) {
                root = i;
                break;
            }
        }
        FenwickTree tree = new FenwickTree(n);
        out.printLine(dfs(root, graph, tree, maxDiff));
    }
    long dfs(int v, Graph graph, FenwickTree ancestors, int shift) {
        long result = ancestors.get(v - shift, v + shift);
        ancestors.add(v, 1);
        for (Edge e : graph.outbound(v)) {
            result += dfs(e.getDestination(), graph, ancestors, shift);
        }
        ancestors.add(v, -1);
        return result;
    }
}
