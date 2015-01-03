package on2015_01.on2015_01_03_HackerRankProblemset.BikeRacers;



import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Point;
import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BikeRacers {
    int n, m, k;
    int[] bx, by, tx, ty;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.readInt();
        m = in.readInt();
        k = in.readInt();
        bx = ArrayUtils.createArray(n, 0);
        by = ArrayUtils.createArray(n, 0);
        for (int i = 0; i < n; ++i) {
            bx[i] = in.readInt();
            by[i] = in.readInt();
        }
        tx = ArrayUtils.createArray(m, 0);
        ty = ArrayUtils.createArray(m, 0);
        for (int i = 0; i < m; ++i) {
            tx[i] = in.readInt();
            ty[i] = in.readInt();
        }
        long l = 0;
        long r = (long) 1e16;
        while (l < r) {
            long m = (l + r) >> 1;
            if (isReachable(m)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        out.printLine(l);
    }

    private boolean isReachable(long r) {
        int source = 0;
        int target = 1 + n + m;
        int N = n + m + 2;
        Graph graph = new Graph(N, N);
        for (int v = 1; v <= n; ++v) {
            graph.addFlowEdge(source, v, 1);
        }
        for (int v = 1; v <= m; ++v) {
            graph.addFlowEdge(n + v, target, 1);
        }
        for (int v = 0; v < n; ++v) {
            for (int u = 0; u < m; ++u) {
                if (dist2(bx[v] - tx[u], by[v] - ty[u]) <= r) {
                    graph.addFlowEdge(v + 1, u + 1 + n, 1);
                }
            }
        }
        return MaxFlow.dinic(graph, source, target) >= k;
    }

    private long dist2(long x, long y) {
        return x * x + y * y;
    }
}
