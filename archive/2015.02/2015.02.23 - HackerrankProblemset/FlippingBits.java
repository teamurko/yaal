package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class FlippingBits {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long n = in.readLong();
		out.printLine(n ^ ((1L << 32) - 1));
    }
}