package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	int[] zFunction(String s) {
		int n = s.length();
		int[] z = new int[n];
		for (int i = 1, l = 0, r = 0; i < n; ++i) {
			if (i <= r)
				z[i] = Math.min(r - i + 1, z[i - l]);
			while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i]))
				++z[i];
			if (i + z[i] - 1 > r) {
				l = i;
				r = i + z[i] - 1;
			}
		}
		return z;
	}
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		if (k == 1) {
			for (int i = 0; i < n; ++i) {
				out.print('1');
			}
			out.printLine();
			return;
		}
		String s = in.readString();
		int[] z = zFunction(s);
		int[] balance = new int[n];
		for (int i = 1; i < n; ++i) {
			if (z[i] < (k - 1) * (long) i) continue;
			int l = z[i] - (k - 1) * i;
			l = Math.min(l, i);
			balance[i * k - 1]++;
			if (i * k + l < n) {
				balance[i * k + l]--;
			}
		}
		long bal = 0;
		for(int i = 0; i < n; ++i) {
			bal += balance[i];
			if (bal > 0) {
				out.print('1');
			} else {
				out.print('0');
			}
		}
		out.printLine();
    }
}
