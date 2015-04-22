package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = (1 << (n + 1)) - 1;
		int[] a = new int[m];
		for (int i = 1; i < m; ++i) {
			a[i] = in.readInt();
		}
		long[] count = new long[m];
		long[] add = new long[m];
		for (int i = m / 2 - 1; i >= 0; --i) {
			int l = (i << 1) + 1;
			int r = (i + 1) << 1;
			long mx = Math.max(count[l] + a[l], count[r] + a[r]);
			add[i] = mx - count[l] - a[l];
			add[i] += mx - count[r] - a[r];
			count[i] = mx;
			add[i] += add[l];
			add[i] += add[r];
		}
		out.printLine(add[0]);
    }
}
