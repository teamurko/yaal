package on2015_02.on2015_02_10_Codeforces_Round__214__Div__2_.D___Dima_and_Trap_Graph;



import net.egork.collections.set.TreapSet;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskD {
	private static final int UNDEFINED = -1;
	private static final int INF = (int) 1e8;

	class TrapEdge {
		final int to;
		final int l;
		final int r;

		TrapEdge(int to, int l, int r) {
			this.to = to;
			this.l = l;
			this.r = r;
		}
	}
	List<TrapEdge>[] g;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		if (m == 0) {
			out.printLine("Nice work, Dima!");
			return;
		}
		g = new List[n];
		for (int i = 0; i < n; ++i) {
			g[i] = new ArrayList<>();
		}
		Set<Integer> ends = new TreeSet<>();
		for (int i = 0; i < m; ++i) {
			int u = in.readInt() - 1;
			int v = in.readInt() - 1;
			int l = in.readInt();
			int r = in.readInt();
			g[u].add(new TrapEdge(v, l, r));
			g[v].add(new TrapEdge(u, l, r));
			ends.add(r);
		}

		int[] es = toArray(ends);

		int ans = UNDEFINED;
		boolean[] used = new boolean[n];
		for (int e : es) {
			int l = 1, r = e;
			while (l < r) {
				int mid = (l + r) / 2;
				for (int i = 0; i < n; ++i) used[i] = false;
				if (dfs(0, n - 1, g, used, mid, e)) {
					r = mid;
				} else {
					l = mid + 1;
				}
			}
			for (int i = 0; i < n; ++i) used[i] = false;
			if (dfs(0, n - 1, g, used, l, l)) {
				ans = Math.max(ans, e - l + 1);
			}
		}

		if (ans == UNDEFINED) {
			out.printLine("Nice work, Dima!");
		} else {
			out.printLine(ans);
		}
    }

	private boolean dfs(int v, int target, List<TrapEdge>[] g, boolean[] used, int l, int r) {
		used[v] = true;
		if (v == target) return true;
		for (TrapEdge edge : g[v]) {
			if (!used[edge.to] && edge.l <= l && r <= edge.r) {
				if (dfs(edge.to, target, g, used, l, r)) return true;
			}
		}
		return false;
	}

	private int[] toArray(Set<Integer> starts) {
		int[] res = new int[starts.size()];
		int i = 0;
		for (Integer x : starts) {
			res[i++] = x;
		}
		Arrays.sort(res);
		return res;
	}
}
