package net.spak;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		boolean[] isPrime = IntegerUtils.generatePrimalityTable(1000007);
		int a = in.readInt();
		int b = in.readInt();
		int k = in.readInt();
		int l = 1;
		int r = b - a + 1;
		while (l < r) {
			int m = (l + r) >> 1;
			if (exists(isPrime, a, b, k, m)) {
				r = m;
			} else {
				l = m + 1;
			}
		}
		if (!exists(isPrime, a, b, k, l)) {
			out.printLine(-1);
		} else {
			out.printLine(l);
		}
    }

	private boolean exists(boolean[] isPrime, int a, int b, int k, int l) {
		int balance = 0;
		for (int i = 0; i < l; ++i) {
			if (isPrime[a + i]) {
				++balance;
			}
		}
		if (balance < k) return false;
		for (int i = a + 1; i + l - 1 <= b; ++i) {
			if (isPrime[i - 1]) --balance;
			if (isPrime[i + l - 1]) ++balance;
			if (balance < k) return false;
		}
		return true;
	}
}
