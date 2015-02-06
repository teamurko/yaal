package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int s = in.readInt() * 100;
		int ans = -1;
		for (int i = 0; i < n; ++i) {
			int cost = in.readInt() * 100;
			cost += in.readInt();
			if (cost <= s) {
				ans = Math.max(ans, (100 - cost % 100) % 100);
			}
		}
		out.printLine(ans);
    }
}
