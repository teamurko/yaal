package net.spak;

import net.egork.collections.CollectionUtils;
import net.egork.collections.function.Function;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskB {
	final int M = 82;
	int a, b, c;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = 9;
		int[][] d = new int[n][];
		for (int i = 0; i < n; ++i) {
			d[i] = ArrayUtils.createArray(M, 0);
		}
		for (int i = 1; i < 10; ++i) {
			d[0][i] = 1;
		}
		for (int i = 0; i + 1 < n; ++i) {
			for (int sum = 1; sum < M; ++sum) {
				if (d[i][sum] == 0) continue;
				for (int digit = 0; digit < 10; ++digit) {
					d[i + 1][sum + digit] += d[i][sum];
				}
			}
		}
		a = in.readInt();
		b = in.readInt();
		c = in.readInt();
		int l = 1 - c, r = 999999999 - c;
		l = IntegerUtils.trueDivide(l + b - 1, b);
		r = IntegerUtils.trueDivide(r, b);

		l = rootUp(l, a);
		r = rootDown(r, a);
		l = Math.max(l, 1);
		r = Math.min(r, 81);


		List<Integer> ans = new ArrayList<>();
		for (int x = l; x <= r; ++x) {
			long k = b * IntegerUtils.power(x, a) + c;
			if (digitRoot(k) == x && k > 0 && k < 1000000000) {
				ans.add((int) k);
			}
		}
		out.printLine(ans.size());
		Collections.sort(ans);
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (Integer m : ans) {
			if (!first) {
				sb.append(" ");
			}
			first = false;
			sb.append(m);
		}
		out.printLine(sb);
	}

	private int digitRoot(long k) {
		int res = 0;
		while (k > 0) {
			res += k % 10;
			k /= 10;
		}
		return res;
	}

	private int rootDown(int n, int i) {
		if (i == 1) {
			return n;
		}
		int k = 0;
		while (IntegerUtils.power(k, i) < n) ++k;
		return k;
	}

	private int rootUp(int n, int i) {
		if (i == 1) {
			return n;
		}
		int k = 0;
		while (IntegerUtils.power(k + 1, i) < n) ++k;
		return k;
	}

	private void calc(int l, int r, long n, int ds, List<Integer> ans) {
		if (n > 999999999) return;
		if (ds > r) return;
		if (ds >= l) {
			long x = b * IntegerUtils.power(ds, a) + c;
			if (x == n) {
				ans.add((int) n);
			}
		}
		for (int digit = 0; digit < 10; ++digit) {
			if (ds == 0 && digit == 0) continue;
			calc(l, r, n * 10 + digit, ds + digit, ans);
		}
	}
}
