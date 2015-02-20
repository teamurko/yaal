package net.spak;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	private static final int N = 10005;
	private static final double EPS = 1e-8;
	double[][] pm;
	double[][] plm;
	double[][] e;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] l = new int[n];
		int[] r = new int[n];
		for (int i = 0; i < n; ++i) {
			l[i] = in.readInt();
			r[i] = in.readInt();
		}
		int m = 1 << n;
		pm = new double[m][];
		plm = new double[m][];
		e = new double[m][];
		for (int mask = 0; mask < m; ++mask) {
			pm[mask] = ArrayUtils.createArray(N, 0.0);
			plm[mask] = ArrayUtils.createArray(N, 0.0);
			e[mask] = ArrayUtils.createArray(N, 0.0);
		}
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < N; ++j) {
				if (j >= l[i] && j <= r[i]) {
					pm[1 << i][j] += 1.0 / (r[i] - l[i] + 1);
				}
			}
		}
		for (int mask = 1; mask < m; ++mask) {
			int i = 0;
			while ((mask & (1 << i)) == 0) ++i;
			int nmask = mask ^ (1 << i);
			if (nmask > 0) {
				for (int j = 1; j < N; ++j) {
					pm[mask][j] += pm[1 << i][j] * plm[nmask][j];
					pm[mask][j] += plm[1 << i][j - 1] * pm[nmask][j];
				}
			}
			for (i = 1; i < N; ++i) {
				plm[mask][i] += pm[mask][i];
				plm[mask][i] += plm[mask][i - 1];
			}
		}
		for (int mask = 1; mask < m; ++mask) {
			for (int i = 1; i < N; ++i) {
				e[mask][i] += i * pm[mask][i];
				e[mask][i] += e[mask][i - 1];
			}
		}
		double ans = 0;
		if (n == 1) {
			for (int a = l[0]; a <= r[0]; ++a) {
				for (int b = l[1]; b <= r[1]; ++b) {
					for (int c = l[2]; c <= r[2]; ++c) {
						int[] d = {a, b, c};
						Arrays.sort(d);
						ans += d[1] * 1.0 / (r[2] - l[2] + 1) / (r[1] - l[1] + 1) / (r[0] - l[0] + 1);
					}
				}
			}
		} else {
			for (int i = 1; i < N; ++i) {
				for (int mask = 1; mask < m; ++mask) {
					int nmask = (m - 1) ^ mask;
					double p = 1.0;
					for (int j = 0; j < n; ++j) {
						if ((mask & (1 << j)) > 0) {
							p *= pm[1 << j][i];
						}
					}
					if (nmask == 0) {
						ans += p * i;
					} else {
						if (Integer.bitCount(mask) == 1) {
							ans += e[nmask][i - 1] * p;
						} else {
							ans += p * plm[nmask][i - 1] * i;
						}
					}
				}
			}
		}
		out.printLine(ans);
	}
}
