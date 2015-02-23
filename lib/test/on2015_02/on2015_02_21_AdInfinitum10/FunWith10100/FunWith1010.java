package on2015_02.on2015_02_21_AdInfinitum10.FunWith10100;





import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.function.Function;

public class FunWith1010 {
	final static int MOD = 2000003;
	final static int[] factorial = new int[MOD];
	static {
		factorial[0] = factorial[1] = 1;
		for (int i = 2; i < MOD; ++i) {
			factorial[i] = (int) ((factorial[i - 1] * (long) i) % MOD);
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long n = in.readLong();
		long m = in.readLong();
		long ans = (m - n + 1) % MOD * IntegerUtils.power(IntegerUtils.power(2, n - 1, MOD) - 1, 3, MOD) % MOD;
		ans = (ans + MOD) % MOD;
		ans = (ans - n + MOD) % MOD;
		--n;
		ans = (ans + 3 * (n + 2) % MOD * IntegerUtils.power(2, n - 1, MOD)) % MOD;
		ans = (ans - 3 * (n + 2) % MOD * IntegerUtils.power(2, 2 * n - 1, MOD)) % MOD;
		ans = (ans + MOD) % MOD;
		ans = (ans + 3 * IntegerUtils.lucasBinomialCoefficient(2 * n, n, MOD, new Function<Integer, Long>() {
			@Override
			public Long apply(Integer i) {
				if (i < 0) return 0L;
				return (long) factorial[i];
			}
		}) * n % MOD * IntegerUtils.reverse(2, MOD)) % MOD;
		ans = (ans + MOD) % MOD;
		ans = (ans + (n + 2) % MOD * IntegerUtils.power(2, 3 * n - 1, MOD)) % MOD;
		ans = (ans - IntegerUtils.lucasBinomialCoefficient(2 * n, n, MOD, new Function<Integer, Long>() {
			@Override
			public Long apply(Integer i) {
				if (i < 0) return 0L;
				return (long) factorial[i];
			}
		}) * 3 * n % MOD * IntegerUtils.reverse(4, MOD) % MOD
					* IntegerUtils.power(2, n, MOD)) % MOD;
		ans = (ans + MOD) % MOD;


		out.printLine(ans);
    }
}
