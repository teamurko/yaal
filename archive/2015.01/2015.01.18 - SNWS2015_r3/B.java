package net.spak;

import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class B {
    private static final int UNDEFINED = -1;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int s = in.readInt();
        int[] sa = ArrayUtils.createArray(n, 0);
        int[] deg = ArrayUtils.createArray(n, 0);
        Graph g = new Graph(n, n * n);
        for (int i = 0; i < n; ++i) {
            sa[i] = in.readInt();
            int k = in.readInt();
            for (int j = 0; j < k; ++j) {
                int u = in.readInt();
                g.addSimpleEdge(u, i);
            }
            deg[i] = k;
        }
        int ans = UNDEFINED;
        int anss = UNDEFINED;
        for (int v = 0; v < n; ++v) {
            Queue<Integer> q = new LinkedList<>();
            q.add(v);
            int[] outDeg = Arrays.copyOf(deg, n);
            boolean[] used = ArrayUtils.createArray(n, false);
            int w = 0;
            used[v] = true;
            while (!q.isEmpty()) {
                int u = q.poll();
                w += sa[u];
                for (Edge e : g.outbound(u)) {
                    if (--outDeg[e.getDestination()] == 0) {
                        if (!used[e.getDestination()]) {
                            used[e.getDestination()] = true;
                            q.add(e.getDestination());
                        }
                    }
                }
            }
            if (w >= s) {
                if (ans == UNDEFINED || anss >= w) {
                    anss = w;
                    ans = v;
                }
            }
        }
        out.printLine(ans);
    }
}
