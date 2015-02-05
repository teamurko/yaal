package on2015_02.on2015_02_03_Codeforces_Round__289__Div__2__ACM_ICPC_Rules_.D___Restoring_Numbers;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	private static final long UNDEFINED = -1;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		long[][] a = new long[n][];
		for (int i = 0; i < n; ++i) {
			a[i] = new long[m];
			for (int j = 0; j < m; ++j) {
				a[i][j] = in.readInt();
			}
		}
		long k = findMod(a);
		if (k == UNDEFINED) {
			out.printLine("NO");
			return;
		}
		long[] x = new long[n];
		long[] y = new long[m];
		y[0] = 0;
		for (int i = 0; i < n; ++i) {
			x[i] = a[i][0];
		}
		for (int j = 1; j < m; ++j) {
			y[j] = (a[0][j] + k - x[0]) % k;
			for (int i = 1; i < n; ++i) {
				long val = (a[i][j] + k - x[i]) % k;
				if (val != y[j]) {
					out.printLine("NO");
					return;
				}
			}
		}
		out.printLine("YES");
		out.printLine(k);
		for (int i = 0; i < n; ++i) {
			if (i > 0) out.print(" ");
			out.print(x[i]);
		}
		out.printLine();
		for (int i = 0; i < m; ++i) {
			if (i > 0) out.print(" ");
			out.print(y[i]);
		}
		out.printLine();
    }

	private long findMod(long[][] a) {
		int n = a.length;
		int m = a[0].length;
		long mod = 0;
		for (int x1 = 0; x1 < n; ++x1) {
			for (int x2 = x1 + 1; x2 < n; ++x2) {
				for (int y1 = 0; y1 < m; ++y1) {
					for (int y2 = y1 + 1; y2 < m; ++y2) {
						long diff = Math.abs(a[x1][y1] + a[x2][y2] - a[x1][y2] - a[x2][y1]);
						if (diff != 0) {
							mod = Math.max(diff, mod);
						}
					}
				}
			}
		}
		if (mod == 0) {
			for (int i = 0; i < n; ++i) {
				mod = Math.max(mod, ArrayUtils.maxElement(a[i]) + 1);
			}
		}
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (a[i][j] >= mod) return UNDEFINED;
			}
		}
		return mod;
	}
}
