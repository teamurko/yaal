package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB2 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		long m = in.readLong();
		int[] ans = new int[n];
		int l = 0, r = n - 1;
		--m;
		for (int i = 0; i < n; ++i) {
			long cnt = (1L << (n - i - 1)) / 2;
			if (cnt > m) {
				ans[l++] = i;
			} else {
				ans[r--] = i;
				m -= cnt;
			}
		}
		for (int i = 0; i < n; ++i) {
			if (i > 0) out.print(" ");
			out.print(ans[i] + 1);
		}
		out.printLine();
    }
}
