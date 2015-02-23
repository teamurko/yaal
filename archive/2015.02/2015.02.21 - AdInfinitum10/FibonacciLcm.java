package net.spak;

import net.egork.collections.Pair;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import sun.jvm.hotspot.jdi.IntegerTypeImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FibonacciLcm {
	private static final long MOD = 1000000007;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = new int[n];
		for (int i = 0; i < n; ++i) {
			a[i] = in.readInt();
		}
		Map<Pair<Long, Integer>, Long> count = new TreeMap<>();
		count.put(Pair.makePair(0L, 0), 1L);
		for (int e : a) {
			Map<Pair<Long, Integer>, Long> nextCount = new TreeMap<>();
			for (Map.Entry<Pair<Long, Integer>, Long> entry : count.entrySet()) {
				long g = IntegerUtils.gcd(e, entry.getKey().first);
				int odd = (entry.getKey().second + 1) % 2;
				Pair<Long, Integer> key = Pair.makePair(g, odd);
				if (!nextCount.containsKey(key)) {
					nextCount.put(key, 0L);
				}
				nextCount.put(key, nextCount.get(key) + entry.getValue());
				if (!nextCount.containsKey(entry.getKey())) {
					nextCount.put(entry.getKey(), entry.getValue());
				} else {
					nextCount.put(entry.getKey(), (entry.getValue() + nextCount.get(entry.getKey())) % (MOD - 1));
				}
			}
			count = nextCount;
		}
		long ans = 1;
		for (Map.Entry<Pair<Long, Integer>, Long> entry : count.entrySet()) {
			if (entry.getKey().first == 0) continue;
			long i = entry.getKey().first;
			long fib = calcFib(i);
			if (entry.getKey().second == 1) {
				ans = (ans * IntegerUtils.power(fib, entry.getValue(), MOD)) % MOD;
			} else {
				fib = IntegerUtils.reverse(fib, MOD);
				ans = (ans * IntegerUtils.power(fib, entry.getValue(), MOD)) % MOD;
			}
		}

		out.printLine(ans);
	}

	private long calcFib(long i) {
		long[][] a = new long[][]{{1, 1}, {1, 0}};
		return pow(a, i)[1][0];
	}

	private long[][] pow(long[][] a, long n) {
		if (n == 0) return new long[][]{{1, 0}, {0, 1}};
		long[][] b = pow(a, n >> 1);
		long[][] res = mul(b, b);
		if ((n & 1) > 0) {
			res = mul(res, a);
		}
		return res;
	}

	private long[][] mul(long[][] a, long[][] b) {
		long[][] c = new long[2][2];
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 2; ++j) {
				for (int k = 0; k < 2; ++k) {
					c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % MOD;
				}
			}
		}
		return c;
	}
}
