package net.spak;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import javax.activation.UnsupportedDataTypeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskB {
    private static final int UNDEFINED = -1;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] p = ArrayUtils.createArray(n, 0);
        for (int i = 0; i < n; ++i) {
            p[i] = in.readInt() - 1;
        }
        Graph g = new Graph(n, n * n);
        for (int i = 0; i < n; ++i) {
            String s = in.readString();
            for (int j = 0; j < n; ++j) {
                if (s.charAt(j) == '1') {
                    g.addSimpleEdge(i, j);
                }
            }
        }

        int[] ans = ArrayUtils.createArray(n, UNDEFINED);
        boolean[] used = ArrayUtils.createArray(n, false);
        for (int v = 0; v < n; ++v) {
            if (used[v]) continue;
            List<Integer> visited = new ArrayList<>();
            dfs(v, g, used, visited);
            Collections.sort(visited);
            int[] values = new int[visited.size()];
            for (int i = 0; i < visited.size(); ++i) {
                values[i] = p[visited.get(i)];
            }
            Arrays.sort(values);
            for (int i = 0; i < visited.size(); ++i) {
                ans[visited.get(i)] = values[i];
            }
        }
        for (int i = 0; i < n; ++i) {
            if (i > 0) out.print(" " );
            out.print(ans[i] + 1);
        }
        out.printLine();
    }

    private void dfs(int v, Graph g, boolean[] used, List<Integer> visited) {
        used[v] = true;
        visited.add(v);
        for (Edge e : g.outbound(v)) {
            if (!used[e.getDestination()]) {
                dfs(e.getDestination(), g, used, visited);
            }
        }
    }
}
