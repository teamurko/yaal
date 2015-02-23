package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class FibonacciFindingEasy {
	private static final long MOD = 1000000007;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long a = in.readInt();
		long b = in.readInt();
		long n = in.readInt();
		long[][] m = new long[][]{{1, 1}, {1, 0}};
		m = pow(m, n - 1);
		long ans = (m[0][0] * b + m[0][1] * a) % MOD;
		out.printLine(ans);
    }

	private long[][] pow(long[][] m, long n) {
		if (n == 0) {
			return new long[][]{{1, 0}, {0, 1}};
		}
		long[][] t = pow(m, n >> 1);
		t = mult(t, t);
		if ((n & 1) == 1) {
			t = mult(t, m);
		}
		return t;
	}

	private long[][] mult(long[][] t, long[][] m) {
		long[][] res = new long[2][2];
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 2; ++j) {
				for (int k = 0; k < 2; ++k) {
					res[i][j] += t[i][k] * m[k][j] % MOD;
				}
				res[i][j] %= MOD;
			}
		}
		return res;
	}
}
