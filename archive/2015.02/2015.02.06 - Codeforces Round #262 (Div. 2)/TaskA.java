package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int ans = 0;
		for (int i = 1; ; ++i) {
			if (n == 0) break;
			++ans;
			--n;
			if (i % m == 0) ++n;
		}
		out.printLine(ans);
    }
}
