package on2015_03.on2015_03_15_Codeforces_Round__147__Div__2_.B___Young_Table;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskB {
	class Exchange {
		final int x1, y1, x2, y2;

		Exchange(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] c = new int[n];
		for (int i = 0; i < n; ++i) {
			c[i] = in.readInt();
		}
		int[][] a = new int[n][];
		for (int i = 0; i < n; ++i) {
			a[i] = new int[c[i]];
			for (int j = 0; j < c[i]; ++j) {
				a[i][j] = in.readInt();
			}
		}
		List<Exchange> ans = new ArrayList<>();
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < c[i]; ++j) {
				int im = i, jm = j;
				for (int i1 = i; i1 < n; ++i1) {
					for (int j1 = 0; j1 < c[i1]; ++j1) {
						if (i1 == i && j1 <= j) continue;
						if (a[i1][j1] < a[im][jm]) {
							im = i1;
							jm = j1;
						}
					}
				}
				if (im != i || jm != j) {
					int cc = a[i][j];
					a[i][j] = a[im][jm];
					a[im][jm] = cc;
					ans.add(new Exchange(i, j, im, jm));
				}
			}
		}
		out.printLine(ans.size());
		for (Exchange exchange : ans) {
			out.printLine(exchange.x1 + 1, exchange.y1 + 1, exchange.x2 + 1, exchange.y2 + 1);
		}
    }
}
