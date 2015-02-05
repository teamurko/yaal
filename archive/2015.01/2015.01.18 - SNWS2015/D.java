package net.spak;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class D {
	private static final int INF = 1000000000;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int mina = 0, maxa = 0;
		int d = 0;
		for (int it = 0; it < n; ++it) {
			int dn = in.readInt();
			int nmina = INF;
			int nmaxa = -INF;
			for (int i = 0; i <= 3; ++i) {
				if (Math.abs(i - d) == dn) {
					nmina = Math.min(nmina, Math.min(mina + i, mina + d));
					nmaxa = Math.max(nmaxa, Math.min(maxa + i, maxa + d));
				}
				if (Math.abs(i + d) == dn) {
					nmina = Math.min(nmina, Math.min(mina, mina + d + i));
					nmaxa = Math.max(nmaxa, Math.min(maxa, maxa + d + i));
				}
			}
			mina = nmina;
			maxa = nmaxa;
			if (mina == INF) {
				out.printLine(0);
				return;
			}
			d = dn;
		}
		int ans = maxa - mina + 1;
		if (d != 0) {
			ans *= 2;
		}
		out.printLine(ans);
	}
}
