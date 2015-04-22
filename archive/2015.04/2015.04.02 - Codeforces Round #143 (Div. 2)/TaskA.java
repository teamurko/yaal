package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int ans = 0;
		for (int i = 0; i < n; ++i) {
			if (in.readInt() + in.readInt() + in.readInt() > 1) {
				++ans;
			}
		}
		out.printLine(ans);
    }
}
