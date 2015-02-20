package net.spak;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskE {
	private static final int UNDEFINED = -1;
	final int[] dx = {1, 0, -1, 0};
	final int[] dy = {0, 1, 0, -1};
	int n;
	int m;
	boolean[][] visited;

	boolean isValid(int x, int y, int n, int m) {
		return x >= 0 && y >= 0 && x < n && y < m;
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		n = in.readInt();
		m = in.readInt();
		visited = new boolean[n][m];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				visited[i][j] = in.readInt() == 1;
			}
		}
		Map<Integer, Integer> posToId = new HashMap<>();
		boolean[][] isInter = new boolean[n][m];
		ArrayUtils.fill(isInter, false);
		int visitedCount = 0;
		int anyVisitedX = UNDEFINED;
		int anyVisitedY = UNDEFINED;
		for (int x = 0; x < n; ++x) {
			for (int y = 0; y < m; ++y) {
				if (!visited[x][y]) continue;
				++visitedCount;
				anyVisitedX = x;
				anyVisitedY = y;
				int key = x * m + y;
				boolean isIntersection = false;
				int nCount = 0;
				for (int i = 0; i < dx.length; ++i) {
					int x1 = x + dx[i];
					int y1 = y + dy[i];
					if (isValid(x1, y1, n, m) && visited[x1][y1]) ++nCount;
					int j = (i + 1) % dx.length;
					int x2 = x + dx[j];
					int y2 = y + dy[j];
					if (isValid(x1, y1, n, m) && isValid(x2, y2, n, m) && visited[x1][y1] && visited[x2][y2]) {
						isIntersection = true;
						break;
					}
				}
				isInter[x][y] = isIntersection || (nCount == 1);
				if (isInter[x][y] && !posToId.containsKey(key)) {
					posToId.put(key, posToId.size());
				}
			}
		}

		if (visitedCount == 1) {
			out.printLine(-1);
			return;
//			int k = Math.min(n - anyVisitedX - 1, anyVisitedX);
//			k = Math.min(k, Math.min(m - anyVisitedY - 1, anyVisitedY));
//			if (k <= 1) {
//				out.printLine(-1);
//			} else {
//				for (int i = 2; i <= k; ++i) {
//					if (i > 2) out.print(" ");
//					out.print(i);
//				}
//				out.printLine();
//			}
//			return;
		}
		int[] deg = ArrayUtils.createArray(posToId.size(), 0);
		List<Integer>[] graph = new ArrayList[posToId.size()];
		int commonLen = 0;
		for (int x = 0; x < n; ++x) {
			int l = 0;
			while (l < m && !isInter[x][l]) ++l;
			while (l < m) {
				int r = l + 1;
				boolean miss = false;
				while (r < m && !isInter[x][r]) {
					if (!visited[x][r]) {
						miss = true;
					}
					++r;
				}
				if (r < m && !miss) {
					Integer u = posToId.get(x * m + l);
					deg[u]++;
					Integer v = posToId.get(x * m + r);
					deg[v]++;
					commonLen = IntegerUtils.gcd(commonLen, r - l);
					if (graph[u] == null) {
						graph[u] = new ArrayList<>();
					}
					if (graph[v] == null) {
						graph[v] = new ArrayList<>();
					}
					graph[v].add(u);
					graph[u].add(v);
				}
				l = r;
			}
		}
		for (int y = 0; y < m; ++y) {
			int l = 0;
			while (l < n && !isInter[l][y]) ++l;
			while (l < n) {
				int r = l + 1;
				boolean miss = false;
				while (r < n && !isInter[r][y]) {
					if (!visited[r][y]) {
						miss = true;
					}
					++r;
				}
				if (r < n && !miss) {
					Integer u = posToId.get(l * m + y);
					deg[u]++;
					Integer v = posToId.get(r * m + y);
					deg[v]++;
					commonLen = IntegerUtils.gcd(commonLen, r - l);
					if (graph[u] == null) {
						graph[u] = new ArrayList<>();
					}
					if (graph[v] == null) {
						graph[v] = new ArrayList<>();
					}
					graph[v].add(u);
					graph[u].add(v);
				}
				l = r;
			}
		}

		int oddCount = 0;
		for (int i = 0; i < posToId.size(); ++i) {
			if (deg[i] % 2 == 1) ++oddCount;
		}

		if (oddCount > 2 || !isConnected(anyVisitedX, anyVisitedY, visitedCount)) {
			out.printLine(-1);
		} else {
			List<Integer> divs = new ArrayList<>();
			for (int i = 2; i <= commonLen; ++i) {
				if (commonLen % i == 0) {
					divs.add(i);
				}
			}
			if (divs.isEmpty()) {
				out.printLine(-1);
			} else {
				for (int i = 0; i < divs.size(); ++i) {
					if (i > 0) out.print(" ");
					out.print(divs.get(i));
				}
				out.printLine();
			}
		}
    }

	private boolean isConnected(int x, int y, int total) {
		boolean[][] used = new boolean[n][m];
		ArrayUtils.fill(used, false);
		return dfs(x, y, used) == total;
	}

	private int dfs(int x, int y, boolean[][] used) {
		used[x][y] = true;
		int ret = 1;
		for (int i = 0; i < dx.length; ++i) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			if (isValid(xx, yy, n, m) && visited[xx][yy] && !used[xx][yy]) {
				ret += dfs(xx, yy, used);
			}
		}
		return ret;
	}
}
