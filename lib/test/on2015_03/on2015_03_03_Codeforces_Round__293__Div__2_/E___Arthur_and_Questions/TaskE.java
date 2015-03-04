package on2015_03.on2015_03_03_Codeforces_Round__293__Div__2_.E___Arthur_and_Questions;



import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskE {
	private static final long INF = (long) 2e10;
	long[] a;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		a = new long[n];
		for (int i = 0; i < n; ++i) {
			String s = in.readString();
			if ("?".equals(s)) {
				a[i] = INF;
			} else {
				a[i] = Integer.parseInt(s);
			}
		}
		for (int i = 0; i < k; ++i) {
			List<Long> seq = new ArrayList<>();
			for (int j = i; j < n; j += k) {
				seq.add(a[j]);
			}
			if (!fill(seq)) {
				out.printLine("Incorrect sequence");
				return;
			}
			for (int j = i, index = 0; j < n; j += k, ++index) {
				if (a[j] == INF) {
					a[j] = seq.get(index);
				}
			}
		}
		for (int i = 0; i < n; ++i) {
			if (i > 0) out.print(" ");
			out.print(a[i]);
		}
		out.printLine();
	}

	private boolean fill(List<Long> seq) {
		int n = seq.size();
		int l = 0;
		while (l < n) {
			while (l < n && seq.get(l) != INF) ++l;
			if (l == n) break;
			long L = -INF;
			if (l > 0) {
				L = seq.get(l - 1) + 1;
			}
			int r = l + 1;
			while (r < n && seq.get(r) == INF) ++r;
			long R = INF;
			if (r < n) R = seq.get(r) - 1;
			if (!fill(seq, l, r - 1, L, R)) return false;
			l = r;
		}
		for (int i = 0; i < n - 1; ++i) {
			if (seq.get(i) >= seq.get(i + 1)) return false;
		}
		return true;
	}

	private boolean fill(List<Long> seq, int l, int r, long L, long R) {
		if (r - l + 1 > R - L + 1) return false;
		if (L >= 0) {
			for (int i = l; i <= r; ++i) {
				seq.set(i, L++);
			}
		} else if (R <= 0) {
			for (int i = r; i >= l; --i) {
				seq.set(i, R--);
			}
		} else {
			L = Math.max(L, -(r - l + 1));
			R = Math.min(R, r - l + 1);
			while (R - L > r - l) {
				if (R > -L) --R;
				else ++L;
			}
			assert(R - L == r - l);
			for (int i = l; i <= r; ++i) {
				seq.set(i, L++);
			}
		}
		return true;
	}
}
