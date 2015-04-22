package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int[] d = new int[m];
		for (int i = 0; i < m; ++i) {
			d[i] = in.readInt();
		}
		Arrays.sort(d);
		if (m == 0) {
			out.printLine("YES");
			return;
		}
		if (d[0] == 1 || d[m - 1] == n) {
			out.printLine("NO");
			return;
		}
		for (int i = 0; i + 2 < m; ++i) {
			if (d[i] + 1 == d[i + 1] && d[i + 1] + 1 == d[i + 2]) {
				out.printLine("NO");
				return;
			}
		}
		out.printLine("YES");
    }
}
