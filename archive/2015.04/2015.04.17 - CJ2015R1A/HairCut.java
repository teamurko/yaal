package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class HairCut {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int bc = in.readInt();
		int n = in.readInt();
		int[] b = new int[bc];
		for (int i = 0; i < bc; ++i) b[i] = in.readInt();
		long lt = 0;
		long rt = n * 100000L;
		while (lt < rt) {
			long t = (lt + rt + 1) >> 1;
			if (isServed(t, b, n)) {
				rt = t - 1;
			} else {
				lt = t;
			}
		}
		int k = n;
		for (int m : b) {
			long sub = (lt + m - 1) / m;
			assert(sub < k);
			k -= sub;
		}
		int ans = -1;
		for (int i = 0; i < bc; ++i) {
			if (lt % b[i] == 0) {
				--k;
				if (k == 0) ans = i;
			}
		}
		assert(ans >= 0);
		out.printLine("Case #" + testNumber + ":", ans + 1);
    }

	private boolean isServed(long t, int[] b, int n) {
		for (int m : b) {
			long sub = (t + m - 1) / m;
			if (sub >= n) return true;
			n -= sub;
		}
		return false;
	}
}
