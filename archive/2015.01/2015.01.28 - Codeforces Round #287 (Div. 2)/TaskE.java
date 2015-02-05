package net.spak;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.LinkedList;
import java.util.Queue;

public class TaskE {
    private static final int UNDEFINED = -1;
    private static final int INF = (int) 1e8;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] x = new int[m];
        int[] y = new int[m];
        int[] z = new int[m];
        Graph graph = new BidirectionalGraph(n, m);
        for (int i = 0; i < m; ++i) {
            x[i] = in.readInt() - 1;
            y[i] = in.readInt() - 1;
            z[i] = in.readInt();
            graph.addWeightedEdge(x[i], y[i], i * 2 + z[i]);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int[] prev = ArrayUtils.createArray(n, UNDEFINED);
        int[] prevEdge = ArrayUtils.createArray(n, UNDEFINED);
        int[] dist = ArrayUtils.createArray(n, INF);
        int[] cost = ArrayUtils.createArray(n, INF);
        dist[0] = 0;
        cost[0] = 0;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (Edge edge : graph.outbound(v)) {
                int to = edge.getDestination();
                int w = (int) edge.getWeight() % 2;
                if (dist[to] == INF) {
                    dist[to] = dist[v] + 1;
                    queue.add(to);
                    prev[to] = v;
                    prevEdge[to] = (int) edge.getWeight() / 2;
                }
                int cst = cost[v];
                if (w == 0) ++cst;
                if (dist[to] == dist[v] + 1 && cst < cost[to]) {
                    cost[to] = cst;
                    prev[to] = v;
                    prevEdge[to] = (int) edge.getWeight() / 2;
                }
            }
        }
        int v = n - 1;
        boolean[] used = ArrayUtils.createArray(m, false);
        while (v != 0) {
            used[prevEdge[v]] = true;
            v = prev[v];
        }
        int cnt = 0;
        for (int i = 0; i < m; ++i) {
            if (used[i] && z[i] == 0) {
                ++cnt;
            } else if (!used[i] && z[i] == 1) {
                ++cnt;
            }
        }
        out.printLine(cnt);
        for (int i = 0; i < m; ++i) {
            if (used[i] && z[i] == 0) {
                out.printLine(String.format("%d %d %d", x[i] + 1, y[i] + 1, 1));
            } else if (!used[i] && z[i] == 1) {
                out.printLine(String.format("%d %d %d", x[i] + 1, y[i] + 1, 0));
            }
        }
    }
}
