package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	int n;
	int m;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		n = in.readInt();
		m = in.readInt();
		int a = in.readInt() % 4;
		int b = in.readInt() % 2;
		int c = (4 - in.readInt() % 4) % 4;
		int p = in.readInt();
		int[] x = new int[p];
		int[] y = new int[p];
		for (int i = 0; i < p; ++i) {
			x[i] = in.readInt();
			y[i] = in.readInt();
		}
		for (int i = 0; i < a; ++i) {
			rotate(x, y);
		}
		if (b > 0) {
			reflectVertical(x, y, m);
		}
		for (int i = 0; i < c; ++i) {
			rotate(x, y);
		}

		for (int i = 0; i < p; ++i) {
			out.printLine(x[i] + " " + y[i]);
		}
    }

	private void rotate(int[] x, int[] y) {
		for (int i = 0; i < x.length; ++i) {
			int yy = n - x[i] + 1;
			int xx = y[i];
			x[i] = xx;
			y[i] = yy;
		}
		int c = n;
		n = m;
		m = c;
	}

	private void reflectVertical(int[] x, int[] y, int m) {
		for (int i = 0; i < x.length; ++i) {
			y[i] = m - y[i] + 1;
		}
	}
}
