package on2015_03.on2015_03_15_Codeforces_Round__147__Div__2_.E___Build_String;



import net.egork.collections.Pair;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.graph.MinCostFlow;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int[] cnt = new int[26];
		for (char c : s) {
			cnt[c - 'a']++;
		}
		int n = in.readInt();
		int[][] count = new int[n][26];
		int N = 26 + n + 2;
		Graph graph = new Graph(N, N * N);
		for (int i = 0; i < 26; ++i) {
			if (cnt[i] > 0) {
				graph.addFlowWeightedEdge(0, i + 1, 0, cnt[i]);
			}
		}
		for (int i = 0; i < n; ++i) {
			char[] ss = in.readString().toCharArray();
			int limit = in.readInt();
			for (char c : ss) {
				count[i][c - 'a']++;
			}
			graph.addFlowWeightedEdge(27 + i, N - 1, 0, limit);
			for (int j = 0; j < 26; ++j) {
				int capacity = Math.min(cnt[j], count[i][j]);
				if (capacity > 0) {
					graph.addFlowWeightedEdge(j + 1, 27 + i, i + 1, capacity);
				}
			}
		}
		MinCostFlow mcf = new MinCostFlow(graph, 0, N - 1, false);
		Pair<Long, Long> ans = mcf.minCostMaxFlow();
		if (ans.second != s.length) {
			out.printLine(-1);
		} else {
			out.printLine(ans.first);
		}
    }
}
