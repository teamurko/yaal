package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int[] wasReverse = new int[s.length];
		int n = in.readInt();
		for (int i = 0; i < n; ++i) {
			int j = in.readInt() - 1;
			wasReverse[j] ^= 1;
		}
		int isReverse = 0;
		for (int l = 0, r = s.length - 1; l < r; ++l, --r) {
			if (wasReverse[l] > 0) {
				isReverse ^= 1;
			}
			if (isReverse > 0) {
				char c = s[l];
				s[l] = s[r];
				s[r] = c;
			}
		}
		out.printLine(new String(s));
    }
}
