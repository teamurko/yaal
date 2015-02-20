package on2015_02.on2015_02_10_Codeforces_Round__214__Div__2_.C___Dima_and_Salad;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	private static final int NMAX = 100005;
	private static final int INF = (int) 1e8;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		int[] a = new int[n];
		for (int i = 0; i < n; ++i) a[i] = in.readInt();
		int[] b = new int[n];
		for (int i = 0; i < n; ++i) b[i] = in.readInt();
		int[][] d = new int[n + 1][NMAX * 2];
		ArrayUtils.fill(d, INF);
		d[0][NMAX] = 0;
		for (int i = 0; i < n; ++i) {
			for (int x = 0; x < d[i].length; ++x) {
				d[i + 1][x] = Math.min(d[i + 1][x], d[i][x]);
				if (d[i][x] == INF) continue;
				int y = x + a[i] - b[i] * k;
				d[i + 1][y] = Math.min(d[i + 1][y], d[i][x] - a[i]);
			}
		}
		if (d[n][NMAX] >= 0) {
			out.printLine(-1);
		} else {
			out.printLine(-d[n][NMAX]);
		}
    }
}
