package on2015_03.on2015_03_02_Codeforces_Round__294__Div__2_.E___A_and_B_and_Lecture_Rooms;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	private static final int LOGMAX = 20;
	private static final int UNDEFINED = -1;
	int[][] up;
	int[] timeIn;
	int[] timeOut;
	int time;
	int[] depth;
	int[] size;
	int[] parent;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		up = new int[n][LOGMAX];
		ArrayUtils.fill(up, UNDEFINED);
		Graph tree = new BidirectionalGraph(n, n);
		for (int i = 0; i < n - 1; ++i) {
			int u = in.readInt() - 1;
			int v = in.readInt() - 1;
			tree.addSimpleEdge(u, v);
		}
		time = 0;
		timeIn = new int[n];
		timeOut = new int[n];
		depth = new int[n];
		size = new int[n];
		parent = ArrayUtils.createArray(n, UNDEFINED);
		buildUp(0, UNDEFINED, tree);
		int numq = in.readInt();
		for (int q = 0; q < numq; ++q) {
			int a = in.readInt() - 1;
			int b = in.readInt() - 1;
			int p = lca(a, b);
			int pathLen = depth[a] + depth[b] - 2 * depth[p];
			if (pathLen % 2 == 1) {
				out.printLine(0);
				continue;
			}
			if (a == b) {
				out.printLine(n);
				continue;
			}
			if (depth[a] > depth[b]) {
				int c = a;
				a = b;
				b = c;
			}
			int v = goUp(b, pathLen / 2);
			int ans = size[v];
			if (isAncestor(v, a)) {
				ans -= size[goUp(a, depth[a] - depth[v] - 1)];
			}
			if (isAncestor(v, b)) {
				ans -= size[goUp(b, depth[b] - depth[v] - 1)];
			}
			if (v == p) {
				ans += n - size[v];
			}
			out.printLine(ans);
		}
	}

	private int goUp(int v, int h) {
		for (int i = LOGMAX; i >= 0; --i) {
			if ((h & (1 << i)) > 0) {
				v = up[v][i];
			}
		}
		return v;
	}

	private int preLca(int a, int b) {
		for (int i = LOGMAX - 1; i >= 0; --i) {
			if (up[a][i] != UNDEFINED && !isAncestor(up[a][i], b)) {
				a = up[a][i];
			}
		}
		return a;
	}

	private int lca(int a, int b) {
		if (isAncestor(a, b)) return a;
		if (isAncestor(b, a)) return b;
		return up[preLca(a, b)][0];
	}

	private boolean isAncestor(int a, int b) {
		return timeIn[a] <= timeIn[b] && timeOut[b] <= timeOut[a];
	}

	private void buildUp(int v, int p, Graph tree) {
		if (p != UNDEFINED) {
			depth[v] = depth[p] + 1;
		}
		parent[v] = p;
		size[v] = 1;
		up[v][0] = p;
		timeIn[v] = time++;
		for (int i = 1; i < LOGMAX; ++i) {
			int u = up[v][i - 1];
			if (u != UNDEFINED) {
				up[v][i] = up[u][i - 1];
			}
		}
		for (Edge edge : tree.outbound(v)) {
			int to = edge.getDestination();
			if (to != p) {
				buildUp(to, v, tree);
				size[v] += size[to];
			}
		}
		timeOut[v] = time++;
	}
}
