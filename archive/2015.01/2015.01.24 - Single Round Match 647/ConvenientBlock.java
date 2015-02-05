package net.spak;

import net.egork.collections.Pair;
import net.egork.graph.*;
import net.egork.misc.ArrayUtils;

public class ConvenientBlock {
    private static final long INF = (long) 1e14;

    public long minCost(int N, int[] from, int[] to, int[] cost) {
        Graph graph = new Graph(N, from.length);
        Graph revGraph = new Graph(N, from.length);
        for (int i = 0; i < from.length; ++i) {
            graph.addSimpleEdge(from[i], to[i]);
            revGraph.addSimpleEdge(to[i], from[i]);
        }
        boolean[] visFromSrc = DFS.visit(graph, 0);
        boolean[] visToDst = DFS.visit(revGraph, N - 1);
        Pair<int[], Graph> scc = StronglyConnectedComponents.kosaraju(graph);
        if (scc.first[0] == scc.first[N - 1]) return -1;
        Graph flowGraph = new Graph(scc.second.vertexCount(), scc.second.edgeCount());
        int n = flowGraph.vertexCount();
        long[][] cap = new long[n][];
        for (int i = 0; i < n; ++i) {
            cap[i] = ArrayUtils.createArray(n, 0L);
        }
        for (int i = 0; i < from.length; ++i) {
            if (!visFromSrc[from[i]] || !visToDst[from[i]]) continue;
            if (!visFromSrc[to[i]] || !visToDst[to[i]]) continue;
            int src = scc.first[from[i]];
            int dst = scc.first[to[i]];
            if (src != dst) {
                cap[src][dst] += cost[i];
            }
        }
        for (int src = 0; src < n; ++src) {
            for (int dst = 0; dst < n; ++dst) {
                if (cap[src][dst] > 0) {
                    flowGraph.addFlowEdge(src, dst, cap[src][dst]);
                    flowGraph.addFlowEdge(dst, src, INF);
                }
            }
        }
        long flow = MaxFlow.dinic(flowGraph, scc.first[0], scc.first[N - 1]);
        if (flow >= INF) return -1;
        return flow;
    }
}
