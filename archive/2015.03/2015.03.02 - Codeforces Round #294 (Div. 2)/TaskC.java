package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int ans = 0;
		for (int a = 0; a <= n; ++a) {
			if (2 * a > m) break;
			int mm = m - 2 * a;
			int lans = a + Math.min(mm, (n - a) / 2);
			ans = Math.max(ans, lans);
		}
		out.printLine(ans);
    }
}
