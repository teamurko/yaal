package on2015_02.on2015_02_01_SNWS2015.D;



import net.egork.collections.Pair;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.graph.ShortestDistance.*;

public class D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int s = in.readInt() - 1;
        Graph graph = new Graph(n, m);
        for (int i = 0; i < m; ++i) {
            int a = in.readInt() - 1;
            int b = in.readInt() - 1;
            int t = in.readInt();
            graph.addWeightedEdge(b, a, t);
        }
        Pair<long[], int[]> result = ShortestDistance.dijkstraAlgorithm(graph, s);
        int cnt = 0;
        long mx = 0;
        for (int v = 0; v < n; ++v) {
            if (result.first[v] != Long.MAX_VALUE) {
                ++cnt;
                mx = Math.max(mx, result.first[v]);
            }
        }
        out.printLine(cnt + " " + mx);
    }
}
