package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int budget = in.readInt();
		for (int i = 1; i <= 4; ++i) {
			int a = in.readInt();
			int b = in.readInt();
			int c = in.readInt();
			int d = in.readInt();
			a = Math.min(a, b);
			c = Math.min(c, d);
			if (a + c <= budget) {
				out.printLine(i + " " + a + " " + (budget - a));
				return;
			}
		}
		out.printLine(-1);
    }
}
