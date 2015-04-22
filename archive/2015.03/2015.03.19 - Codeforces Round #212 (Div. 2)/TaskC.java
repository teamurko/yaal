package net.spak;

import net.egork.collections.FenwickTree;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] p = new int[n];
		for (int i = 0; i < n; ++i) {
			p[i] = in.readInt();
		}
		int mn = (int) 1e8;
		int cnt = 0;
		int invCount = 0;
		for (int i = 0; i < n; ++i) {
			FenwickTree t = new FenwickTree(n);
			for (int j = i + 1; j < n; ++j) {
				if (p[i] > p[j]) ++invCount;
				int diff = getDiff(t, p[i], p[j], n);
				if (p[i] > p[j]) --diff;
				else ++diff;
				if (diff < mn) {
					mn = diff;
					cnt = 1;
				} else if (diff == mn) {
					++cnt;
				}
				t.add(p[j], 1);
			}
		}
		out.printLine(invCount + mn, cnt);
    }

	private int getDiff(FenwickTree t, int i, int j, int n) {
		long ret = t.get(i + 1, n - 1) - t.get(0, i - 1);
		ret += t.get(0, j - 1) - t.get(j + 1, n - 1);
		return (int) ret;
	}
}
