package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int v1 = in.readInt();
		int v2 = in.readInt();
		int t = in.readInt();
		int d = in.readInt();
		int ans = 0;
		int v = v1;
		while (t > 1) {
			ans += v;
			v += d;
			--t;
			v = Math.min(v, v2 + (t - 1) * d);
		}
		ans += v2;
		out.printLine(ans);
    }
}
