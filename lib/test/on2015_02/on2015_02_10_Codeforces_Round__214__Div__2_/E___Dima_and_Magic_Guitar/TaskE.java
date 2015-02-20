package on2015_02.on2015_02_10_Codeforces_Round__214__Div__2_.E___Dima_and_Magic_Guitar;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	private static final int INF = (int) 1e8;
	int[][] a;
	int[][] lu, ld, ru, rd;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int k = in.readInt();
		int s = in.readInt();
		a = new int[n][m];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				a[i][j] = in.readInt() - 1;
			}
		}
		lu = new int[n][m];
		ru = new int[n][m];
		ld = new int[n][m];
		rd = new int[n][m];
		int[][] mx = new int[k][k];
		ArrayUtils.fill(mx, 0);

		for (int i = 0; i < k; ++i) {
			ArrayUtils.fill(lu, -INF);
			ArrayUtils.fill(ru, -INF);
			ArrayUtils.fill(ld, -INF);
			ArrayUtils.fill(rd, -INF);
			for (int x = 0; x < n; ++x) {
				for (int y = 0; y < m; ++y) {
					if (a[x][y] == i) {
						lu[x][y] = -x - y;
					}
					if (x > 0) lu[x][y] = Math.max(lu[x][y], lu[x - 1][y]);
					if (y > 0) lu[x][y] = Math.max(lu[x][y], lu[x][y - 1]);
				}
				for (int y = m - 1; y >= 0; --y) {
					if (a[x][y] == i) {
						ru[x][y] = -x + y;
					}
					if (x > 0) ru[x][y] = Math.max(ru[x][y], ru[x - 1][y]);
					if (y + 1 < m) ru[x][y] = Math.max(ru[x][y], ru[x][y + 1]);
				}
			}
			for (int x = n - 1; x >= 0; --x) {
				for (int y = 0; y < m; ++y) {
					if (a[x][y] == i) {
						ld[x][y] = x - y;
					}
					if (x + 1 < n) ld[x][y] = Math.max(ld[x][y], ld[x + 1][y]);
					if (y > 0) ld[x][y] = Math.max(ld[x][y], ld[x][y - 1]);
				}
				for (int y = m - 1; y >= 0; --y) {
					if (a[x][y] == i) {
						rd[x][y] = x + y;
					}
					if (x + 1 < n) rd[x][y] = Math.max(rd[x][y], rd[x + 1][y]);
					if (y + 1 < m) rd[x][y] = Math.max(rd[x][y], rd[x][y + 1]);
				}
			}

			for (int x = 0; x < n; ++x) {
				for (int y = 0; y < m; ++y) {
					int type = a[x][y];
					if (lu[x][y] != -INF) {
						mx[type][i] = Math.max(mx[type][i], lu[x][y] + x + y);
					}
					if (ru[x][y] != -INF) {
						mx[type][i] = Math.max(mx[type][i], ru[x][y] + x - y);
					}
					if (ld[x][y] != -INF) {
						mx[type][i] = Math.max(mx[type][i], ld[x][y] - x + y);
					}
					if (rd[x][y] != -INF) {
						mx[type][i] = Math.max(mx[type][i], rd[x][y] - x - y);
					}
				}
			}
		}

		int[] ts = new int[s];
		for (int i = 0; i < s; ++i) {
			ts[i] = in.readInt() - 1;
		}
		int ans = 0;
		for (int i = 0; i + 1 < s; ++i) {
			ans = Math.max(ans, mx[ts[i]][ts[i + 1]]);
		}
		out.printLine(ans);
    }
}
