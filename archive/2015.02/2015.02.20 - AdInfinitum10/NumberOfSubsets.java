package net.spak;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class NumberOfSubsets {
	private final long mod = 1000000007;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long n = in.readLong();
		long m = IntegerUtils.power(2, n, mod - 1);
		m = (m - n % (mod - 1) + mod - 1) % (mod - 1);
		out.printLine(IntegerUtils.power(2, m, mod));
    }
}
