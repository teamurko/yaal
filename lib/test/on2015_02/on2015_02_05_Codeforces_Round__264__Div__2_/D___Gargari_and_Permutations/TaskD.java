package on2015_02.on2015_02_05_Codeforces_Round__264__Div__2_.D___Gargari_and_Permutations;



import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		int[][] pos = new int[n][];
		for (int i = 0; i < n; ++i) {
			pos[i] = ArrayUtils.createArray(k, 0);
		}
		for (int j = 0; j < k; ++j) {
			for (int i = 0; i < n; ++i) {
				int a = in.readInt() - 1;
				pos[a][j] = i;
			}
		}
		Graph graph = new Graph(n, n * n);
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				boolean less = true;
				for (int t = 0; t < k; ++t) {
					if (pos[i][t] >= pos[j][t]) {
						less = false;
						break;
					}
				}
				if (less) {
					graph.addSimpleEdge(j, i);
				}
			}
		}

		boolean[] used = ArrayUtils.createArray(n, false);
		int[] maxLen = ArrayUtils.createArray(n, 0);
		for (int v = 0; v < n; ++v) {
			if (!used[v]) {
				dfs(v, graph, used, maxLen);
			}
		}
		out.printLine(ArrayUtils.maxElement(maxLen));
    }

	private void dfs(int v, Graph graph, boolean[] used, int[] maxLen) {
		used[v] = true;
		maxLen[v] = 1;
		for (Edge edge : graph.outbound(v)) {
			if (!used[edge.getDestination()]) {
				dfs(edge.getDestination(), graph, used, maxLen);
			}
			maxLen[v] = Math.max(maxLen[v], maxLen[edge.getDestination()] + 1);
		}
	}
}
