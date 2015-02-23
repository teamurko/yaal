package on2015_02.on2015_02_20_AdInfinitum10.CheeseAndRandomToppings;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class CheeseAndRandomToppings {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long n = in.readLong();
		long m = in.readLong();
		long mod = in.readLong();
		long[] primes = factorize(mod);
		long[] rem = new long[primes.length];
		for (int i = 0; i < primes.length; ++i) {
			rem[i] = calcRem(n, m, primes[i]);
		}
		long ans = restore(rem, primes, mod);
		out.printLine(ans);
    }

	private long restore(long[] a, long[] ps, long n) {
		long res = 0;
		for (int i = 0; i < a.length; ++i) {
			res += a[i] * (n / ps[i]) * IntegerUtils.reverse(n / ps[i], ps[i]) % n;
		}
		return res % n;
	}

	private long calcRem(long n, long m, long p) {
		long[] coefN = calcSeries(n, p);
		long[] coefM = calcSeries(m, p);
		long res = 1;
		for (int i = 0; i < Math.max(coefM.length, coefN.length); ++i) {
			long n1 = (i < coefN.length ? coefN[i] : 0);
			long m1 = (i < coefM.length ? coefM[i] : 0);
			res = (res * calcBinCoef((int) n1, (int) m1, p)) % p;
		}
		return res;
	}

	private long calcBinCoef(int n, int m, long p) {
		if (m > n) return 0;
		int[] primeCount = new int[n + 1];
		for (int i = n; i > n - m; --i) {
			int c = i;
			for (int j = 2; j <= c; ++j) {
				while (c % j == 0) {
					primeCount[j]++;
					c /= j;
				}
			}
		}
		for (int i = 1; i <= m; ++i) {
			int c = i;
			for (int j = 2; j <= c; ++j) {
				while (c % j == 0) {
					primeCount[j]--;
					c /= j;
				}
			}
		}
		long res = 1;
		for (int i = 2; i <= n; ++i) {
			res = (res * IntegerUtils.power(i, primeCount[i], p)) % p;
		}
		return res;
	}

	private long[] calcSeries(long n, long p) {
		long[] res = new long[100];
		int i = 0;
		while (n > 0) {
			res[i++] = n % p;
			n /= p;
		}
		return Arrays.copyOf(res, i);
	}

	private long[] factorize(long n) {
		long[] res = new long[100];
		int i = 0;
		for (int p = 2; p < 50; ++p) {
			if (n % p == 0) {
				res[i++] = p;
				n /= p;
			}
		}
		assert(n == 1);
		return Arrays.copyOf(res, i);
	}
}
