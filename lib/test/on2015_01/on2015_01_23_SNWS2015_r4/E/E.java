package on2015_01.on2015_01_23_SNWS2015_r4.E;



import net.egork.collections.Pair;
import net.egork.graph.*;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.LinkedList;
import java.util.Queue;

public class E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        long x = in.readLong();
        Graph g = new Graph(n, k);
        for (int i = 0; i < k; ++i) {
            int from = in.readInt() - 1;
            int to = in.readInt() - 1;
            g.addSimpleEdge(from, to);
        }
        long[] d = new long[n];
        for (int i = 0; i < n; ++i) {
            d[i] = in.readLong();
        }

        Pair<int[], Graph> result = StronglyConnectedComponents.kosaraju(g);
        int m = result.second.vertexCount();
        long[] dc = ArrayUtils.createArray(m, 0L);

        for (int i = 0; i < n; ++i) {
            int j = result.first[i];
            dc[j] = IntegerUtils.gcd(dc[j], d[i]);
        }

        Graph gc = result.second;
        int[] deg = ArrayUtils.createArray(m, 0);
        for (int i = 0; i < gc.edgeCount(); ++i) {
            ++deg[gc.edge(i).getDestination()];
        }
        Queue<Integer> q = new LinkedList<>();
        for (int v = 0; v < m; ++v) {
            if (deg[v] == 0) q.add(v);
        }

        while (!q.isEmpty()) {
            int v = q.poll();
            for (Edge e : gc.outbound(v)) {
                dc[e.getDestination()] = IntegerUtils.gcd(dc[e.getDestination()], dc[v]);
                if (--deg[e.getDestination()] == 0) {
                    q.add(e.getDestination());
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            long dv = dc[result.first[i]];
            if (dv == x) ++ans;
        }
        out.printLine(ans);
    }
}
