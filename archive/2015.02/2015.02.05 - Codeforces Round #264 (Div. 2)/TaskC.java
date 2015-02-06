package net.spak;

import net.egork.collections.CollectionUtils;
import net.egork.collections.map.Counter;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class TaskC {
	private static final long UNDEFINED = -1L;
	final int[] dx = {1, 1, -1, -1};
	final int[] dy = {1, -1, 1, -1};
	long[][] ct;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		ct = new long[n][];

		Counter<Integer> sCounter = new Counter<>();
		Counter<Integer> dCounter = new Counter<>();
		for (int i = 0; i < n; ++i) {
			ct[i] = new long[n];
			for (int j = 0; j < n; ++j) {
				ct[i][j] = in.readInt();
				sCounter.add(i + j, ct[i][j]);
				dCounter.add(i - j, ct[i][j]);
			}
		}
		int ox = -1, oy = -1;
		long om = -1;
		int ex = -1, ey = -1;
		long em = -1;

		for (int x = 0; x < n; ++x) {
			for (int y = 0; y < n; ++y) {
				long sum = -ct[x][y];
				sum += sCounter.get(x + y);
				sum += dCounter.get(x - y);
				if ((x + y) % 2 == 0) {
					if (sum > em) {
						em = sum;
						ex = x;
						ey = y;
					}
				} else {
					if (sum > om) {
						om = sum;
						ox = x;
						oy = y;
					}
				}
			}
		}
		out.printLine(om + em);
		out.printLine(String.format("%d %d %d %d", ox + 1, oy + 1, ex + 1, ey + 1));
    }
}
