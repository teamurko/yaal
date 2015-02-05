package on2015_02.on2015_02_03_Codeforces_Round__289__Div__2__ACM_ICPC_Rules_.F___Progress_Monitoring;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	private static final long UNDEFINED = -1L;
	private static final long MOD = 1000000007L;
	long[][] t, d;
	int[] p;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		p = new int[n];
		for (int i = 0; i < n; ++i) p[i] = in.readInt() - 1;
		t = new long[n][];
		d = new long[n][];
		for (int i = 0; i < n; ++i) {
			t[i] = ArrayUtils.createArray(n, UNDEFINED);
			d[i] = ArrayUtils.createArray(n, UNDEFINED);
		}
		out.printLine(calcT(0, n - 1));
    }

	private long calcT(int l, int r) {
		if (l == r) {
			return 1L;
		}
		if (t[l][r] != UNDEFINED) {
			return t[l][r];
		}
		return t[l][r] = calcD(l + 1, r);
	}

	private long calcD(int l, int r) {
		if (l == r) {
			return 1L;
		}
		if (d[l][r] != UNDEFINED) {
			return d[l][r];
		}
		d[l][r] = calcT(l, r);
		for (int i = l; i < r; ++i) {
			if (p[l] < p[i + 1]) {
				d[l][r] += calcT(l, i) * calcD(i + 1, r) % MOD;
			}
		}
		return d[l][r] %= MOD;
	}
}
