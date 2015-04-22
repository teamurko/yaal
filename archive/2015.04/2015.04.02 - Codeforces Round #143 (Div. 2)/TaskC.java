package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		long k = in.readInt();
		long[] a = new long[n];
		for (int i = 0; i < n; ++i) {
			a[i] = in.readLong();
		}
		Arrays.sort(a);
		int maxOc = 1;
		long maxArg = a[0];
		long incSum = 0;
		int l = n - 1;
		for (int i = n - 1; i > 0; --i) {
			while (l > 0 && incSum + a[i] - a[l - 1] <= k) {
				incSum += a[i] - a[l - 1];
				--l;
			}
			if (maxOc <= i - l + 1) {
				maxOc = i - l + 1;
				maxArg = a[i];
			}
			incSum -= (i - l) * (a[i] - a[i - 1]);
		}
		if (maxOc == 1) {
			maxArg = a[0];
		}
		out.printLine(maxOc, maxArg);
	}
}
