package on2015_02.on2015_02_02_Codeforces_Round__290__Div__1_.A___Fox_And_Names;



import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        String[] w = new String[n];
        for (int i = 0; i < n; ++i) {
            w[i] = in.readString();
        }
        Graph graph = new Graph(26, n * n);
        boolean ok = true;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int p = 0;
                while (p < w[i].length() && p < w[j].length() && w[i].charAt(p) == w[j].charAt(p)) {
                    ++p;
                }
                if (p == w[j].length() && p < w[i].length()) {
                    ok = false;
                    break;
                }
                if (p == w[j].length() || p == w[i].length()) continue;
                int from = w[j].charAt(p) - 'a';
                int to = w[i].charAt(p) - 'a';
                graph.addSimpleEdge(from, to);
            }
            if (!ok) break;
        }
        if (!ok) {
            out.printLine("Impossible");
            return;
        }
        int[] used = ArrayUtils.createArray(26, 0);
        List<Integer> order = new ArrayList<>(26);
        for (int v = 0; v < 26; ++v) {
            if (used[v] == 0) {
                if (!dfs(v, graph, used, order)) {
                    ok = false;
                }
            }
        }
        if (!ok) {
            out.printLine("Impossible");
            return;
        }
        assert(order.size() == 26);
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < 26; ++i) {
            char c = toChar(order.get(i));
            ans.append(c);
        }
        out.printLine(ans);
    }

    private char toChar(int i) {
        return (char) (i + 'a');
    }

    private boolean dfs(int v, Graph graph, int[] used, List<Integer> order) {
        if (used[v] > 0) return false;
        used[v] = 1;
        for (Edge edge : graph.outbound(v)) {
            if (used[edge.getDestination()] != 2) {
                if (!dfs(edge.getDestination(), graph, used, order)) {
                    return false;
                }
            }
        }
        order.add(v);
        used[v] = 2;
        return true;
    }
}
