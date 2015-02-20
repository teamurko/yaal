package net.spak;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG2 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		int[] p = new int[n];
		for (int i = 0; i < n; ++i) {
			p[i] = in.readInt() - 1;
		}
		double[][][] d = new double[k + 1][n][n];
		ArrayUtils.fill(d, 0);
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				if (p[i] > p[j]) d[0][i][j] = 1.0;
			}
		}
		final double pc = 2.0 / n / (n + 1);
		for (int i = 0; i < k; ++i) {
			for (int x = 0; x < n; ++x) {
				for (int y = x + 1; y < n; ++y) {
					for (int a = 0; a < n; ++a) {
						for (int b = a; b < n; ++b) {
							int ii = x;
							int cnt = 0;
							if (a <= ii && ii <= b) {
								ii = a + (b - ii);
								++cnt;
							}
							int jj = y;
							if (a <= jj && jj <= b) {
								jj = a + (b - jj);
								++cnt;
							}
							double add = d[i][x][y];
							if (cnt == 2) {
								add = 1 - add;
								int c = ii;
								ii = jj;
								jj = c;
							}
							d[i + 1][ii][jj] += pc * add;
						}
					}
				}
			}
		}
		double ans = 0;
		for (int x = 0; x < n; ++x) {
			for (int y = x + 1; y < n; ++y) {
				ans += d[k][x][y];
			}
		}
		out.printLine(String.format("%.15f", ans));
    }
}
