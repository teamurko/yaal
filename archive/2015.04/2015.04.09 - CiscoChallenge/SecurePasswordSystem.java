package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SecurePasswordSystem {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long[] d10 = new long[14];
		d10[0] = 1;
		for (int i = 1; i < 14; ++i) {
			d10[i] = d10[i - 1] * 10;
		}
		int from = in.readInt();
		int to = in.readInt();
		long numPass = 0;
		for (int i = from; i <= to; ++i) {
			numPass += d10[i];
		}
		out.printLine(numPass > 1000000 ? "YES" : "NO");
    }
}
