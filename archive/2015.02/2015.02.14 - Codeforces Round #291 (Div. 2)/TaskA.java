package net.spak;

import net.egork.collections.CollectionUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Collections;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long n = in.readLong();
		StringBuilder sb = new StringBuilder();
		while (n > 0) {
			if (n >= 10) {
				sb.append(Math.min(n % 10, 9 - n % 10));
			} else {
				if (n == 9) sb.append(9);
				else sb.append(Math.min(9 - n, n));
			}
			n /= 10;
		}
		out.printLine(sb.reverse().toString());
    }
}
