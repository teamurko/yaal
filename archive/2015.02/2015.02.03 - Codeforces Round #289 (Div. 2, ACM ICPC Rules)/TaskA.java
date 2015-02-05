package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		long[][] a = new long[n][];
		for (int i = 0; i < n; ++i) {
			a[i] = new long[n];
		}
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (i == 0 || j == 0) a[i][j] = 1;
				else a[i][j] = a[i - 1][j] + a[i][j - 1];
			}
		}
		out.printLine(a[n - 1][n - 1]);
    }
}
