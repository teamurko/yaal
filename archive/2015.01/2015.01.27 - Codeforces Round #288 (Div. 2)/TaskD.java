package net.spak;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        String[] ws = new String[n];
        for (int i = 0; i < n; ++i) {
            ws[i] = in.readString();
        }
        wToId = new HashMap<>();
        for (String w : ws) {
            getId(w.substring(0, 2));
            getId(w.substring(1, 3));
        }
        int m = wToId.size();
        idTow = new String[m];
        for (Map.Entry<String, Integer> entry : wToId.entrySet()) {
            idTow[entry.getValue()] = entry.getKey();
        }
        Graph graph = new Graph(m, n);
        int[] deg = ArrayUtils.createArray(m, 0);
        for (String w : ws) {
            int u = getId(w.substring(0, 2));
            int v = getId(w.substring(1, 3));
            --deg[u];
            ++deg[v];
            graph.addSimpleEdge(v, u);
        }
        int posCount = 0;
        int negCount = 0;
        int start = 0;
        for (int i = 0; i < m; ++i) {
            if (deg[i] > 1 || deg[i] < -1) {
                out.printLine("NO");
                return;
            }
            if (deg[i] > 0) {
                ++posCount;
                start = i;
            } else if (deg[i] < 0) {
                ++negCount;
            }
        }
        if (posCount + negCount > 2) {
            out.printLine("NO");
            return;
        }
        if (posCount > 1 || negCount > 1) {
            out.printLine("NO");
            return;
        }
        List<Integer> visited = new ArrayList<>(n);
        boolean[] used = ArrayUtils.createArray(n, false);
        outbound = new Iterator[m];
        for (int i = 0; i < m; ++i) {
            outbound[i] = graph.outbound(i).iterator();
        }
        dfs(start, graph, used, visited);
        StringBuilder ans = new StringBuilder();
        ans.append(idTow[visited.get(0)]);
        for (int i = 1; i < visited.size(); ++i) {
            ans.append(idTow[visited.get(i)].charAt(1));
        }
        if (ans.toString().length() != n + 2) {
            out.printLine("NO");
            return;
        }
        out.printLine("YES");
        out.printLine(ans);
    }

    private void dfs(int v, Graph graph, boolean[] used, List<Integer> visited) {
        Iterator<Edge> out = outbound[v];
        while (out.hasNext()) {
            Edge e = out.next();
            dfs(e.getDestination(), graph, used, visited);
        }
        visited.add(v);
    }

    private Map<String, Integer> wToId;
    private String[] idTow;
    private Iterator<Edge>[] outbound;

    int getId(String w) {
        if (!wToId.containsKey(w)) {
            wToId.put(w, wToId.size());
        }
        return wToId.get(w);
    }
}
