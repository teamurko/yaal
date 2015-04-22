package net.spak;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class InfiniteHouseOfPancakes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] p = new int[n];
		for (int i = 0; i < n; ++i) {
			p[i] = in.readInt();
		}
		int ans = 1000000000;
		int m = ArrayUtils.maxElement(p);
		for (int i = 1; i <= m; ++i) {
			int cnt = i;
			for (int k : p) {
				cnt += (k - 1) / i;
			}
			ans = Math.min(ans, cnt);
		}
		out.printLine("Case #" + testNumber + ":", ans);
    }
}
