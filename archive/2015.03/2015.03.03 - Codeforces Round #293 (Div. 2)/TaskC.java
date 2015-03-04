package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int k = in.readInt();
		int[] a = new int[n];
		int[] pos = new int[n];
		for (int i = 0; i < n; ++i) {
			a[i] = in.readInt() - 1;
			pos[a[i]] = i;
		}
		long ans = 0;
		for (int q = 0; q < m; ++q) {
			int i = in.readInt() - 1;
			ans += pos[i] / k + 1;
			if (pos[i] > 0) {
				swap(a, pos, pos[i] - 1, pos[i]);
			}
		}
		out.printLine(ans);
    }

	private void swap(int[] a, int[] pos, int i, int j) {
		int c = a[i];
		a[i] = a[j];
		a[j] = c;
		pos[a[i]] = i;
		pos[a[j]] = j;
	}
}
