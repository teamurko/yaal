package net.spak;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = new int[n];
		int[] b = new int[n];
		int[] c = new int[n];
		for (int i = 0; i < n; ++i) {
			a[i] = in.readInt();
		}
		for (int i = 0; i < n; ++i) {
			b[i] = in.readInt();
		}
		for (int i = 0; i < n; ++i) {
			c[i] = in.readInt();
		}
		long[][] d = new long[2][n];
		ArrayUtils.fill(d, 0);
		d[0][0] = a[0];
		d[1][0] = b[0];
		for (int i = 1; i < n; ++i) {
			d[0][i] = Math.max(d[0][i], d[0][i - 1] + b[i]);
			d[0][i] = Math.max(d[0][i], d[1][i - 1] + a[i]);
			d[1][i] = Math.max(d[1][i], d[0][i - 1] + c[i]);
			d[1][i] = Math.max(d[1][i], d[1][i - 1] + b[i]);
		}
		out.printLine(d[0][n - 1]);
    }
}
