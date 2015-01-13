package net.spak;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Edge;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class C {
    long ans;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        BidirectionalGraph g = new BidirectionalGraph(n, n);
        for (int i = 0; i < n - 1; ++i) {
            int u = in.readInt();
            int v = in.readInt();
            int w = in.readInt();
            --u;
            --v;
            g.addWeightedEdge(u, v, w);
        }
        ans = 0;
        dfs(0, -1, g);
        out.printLine(ans);
    }

    private long dfs(int v, int p, BidirectionalGraph g) {
        long m1 = (long) -10000;
        long m2 = (long) -10000;
        long res = 0;
        for (Edge e : g.outbound(v)) {
            int to = e.getDestination();
            if (to == v) {
                to = e.getSource();
            }
            if (to == p) continue;
            long ret = dfs(to, v, g) + e.getWeight();
            if (ret > m1) {
                m2 = m1;
                m1 = ret;
            } else if (ret > m2) {
                m2 = ret;
            }
            res = Math.max(res, ret);
        }
        ans = Math.max(ans, res);
        if (m2 > 0) {
            ans = Math.max(ans, m1 + m2);
        }
        return res;
    }
}
