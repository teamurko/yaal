package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] h = new int[n];
		for (int i = 0; i < n; ++i) {
			h[i] = in.readInt();
		}
		int l = 0, r = 1000000;
		while (l < r) {
			int m = (l + r) / 2;
			if (enough(h, m)) r = m;
			else l = m + 1;
		}
		out.printLine(l);
    }

	private boolean enough(int[] h, int balance) {
		int curHeight = 0;
		for (int height : h) {
			balance += curHeight - height;
			curHeight = height;
			if (balance < 0) return false;
		}
		return true;
	}
}
