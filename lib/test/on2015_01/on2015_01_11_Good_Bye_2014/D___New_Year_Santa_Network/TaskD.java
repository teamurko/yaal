package on2015_01.on2015_01_11_Good_Bye_2014.D___New_Year_Santa_Network;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    private static final int UNDEFINED = -1;

    class MyEdge {
        int u;
        int v;
        long w;
        MyEdge(int u, int v, long w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    int[] parent;
    long[] a0;
    long[] a1;
    long[] c;
    long[] s;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        parent = ArrayUtils.createArray(n, UNDEFINED);
        a0 = ArrayUtils.createArray(n, 0L);
        a1 = ArrayUtils.createArray(n, 0L);
        c = ArrayUtils.createArray(n, 0L);
        s = ArrayUtils.createArray(n, 0L);
        MyEdge[] edges = new MyEdge[n - 1];
        Graph g = new BidirectionalGraph(n, n - 1);
        for (int i = 0; i < n - 1; ++i) {
            int u = in.readInt() - 1;
            int v = in.readInt() - 1;
            long w = in.readInt();
            edges[i] = new MyEdge(u, v, w);
            g.addWeightedEdge(u, v, w);
        }
        dfs(0, UNDEFINED, g);
        long sumLength = s[0];
        int q = in.readInt();
        for (int i = 0; i < q; ++i) {
            int ei = in.readInt() - 1;
            long w = in.readInt();
            long add = edges[ei].w - w;
            edges[ei].w = w;
            if (parent[edges[ei].u] == edges[ei].v) {
                add *= c[edges[ei].u] * (n - c[edges[ei].u]);
            } else {
                add *= c[edges[ei].v] * (n - c[edges[ei].v]);
            }
            sumLength -= add;
            double ans = sumLength * 6.0 / n / (n - 1);
            out.printLine(ans);
        }
    }

    private void dfs(int v, int p, Graph g) {
        c[v] = 0;
        parent[v] = p;
        long sa1 = 0;
        long sca1 = 0;
        long scw = 0;
        long sccw = 0;
        for (Edge e : g.outbound(v)) {
            int u =  e.getDestination();
            if (u != p) {
                dfs(u, v, g);
                c[v] += c[u];
                a1[v] += a1[u] + e.getWeight() * c[u];
                sa1 += a1[u];
                sca1 += c[u] * a1[u];
                scw += c[u] * e.getWeight();
                sccw += c[u] * c[u] * e.getWeight();
                s[v] += s[u];
            }
        }
        a0[v] = c[v] * sa1 - sca1;
        a0[v] += c[v] * scw - sccw;
        c[v] += 1;
        s[v] += a0[v] + a1[v];
    }
}
