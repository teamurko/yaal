package on2015_02.on2015_02_20_AdInfinitum10.AWierdFunction;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AWierdFunction {
	private static final int MAXN = 10000000;
	private static final long MAXR = 1000000000000L;
	private static final int MAXP = 10000000;
	int[] minPrimeDivisor = new int[MAXP];


	public void solve(int testNumber, InputReader in, OutputWriter out) {
		precalc();
		long[] nums = new long[MAXN];
		long[] phis = new long[MAXN];
		int numsCount = 0;
		for (long i = 1; i * (i + 1) <= MAXR * 2; ++i) {
			long phiValue = phiWierd((int) i);
			nums[numsCount] = i * (i + 1) >> 1;
			phis[numsCount] = phiValue;
			++numsCount;
		}
		nums = Arrays.copyOf(nums, numsCount);
		phis = Arrays.copyOf(phis, numsCount);
		for (int i = numsCount - 2; i >= 0; --i) {
			phis[i] += phis[i + 1];
		}
		int numTests = in.readInt();
		for (int testId = 0; testId < numTests; ++testId) {
			int l = Arrays.binarySearch(nums, in.readLong());
			if (l < 0) {
				l = -(l + 1);
			}
			int r = Arrays.binarySearch(nums, in.readLong());
			if (r >= 0) ++r;
			else {
				r = -(r + 1);
			}
			out.printLine(phis[l] - phis[r]);
		}
	}

	private long phiWierd(int n) {
		Map<Integer, Integer> count = new HashMap<>();
		factorize(n, count);
		factorize(n + 1, count);
		count.put(2, count.get(2) - 1);
		long res = 1;
		for (Map.Entry<Integer, Integer> kv : count.entrySet()) {
			if (kv.getValue() > 0) {
				res *= IntegerUtils.power(kv.getKey(), kv.getValue() - 1) * (kv.getKey() - 1);
			}
		}
		return res;
	}

	private void factorize(int n, Map<Integer, Integer> count) {
		for (int m = n; m > 1; ) {
			int p = minPrimeDivisor[m];
			if (p == 0) {
				p = m;
			}
			if (!count.containsKey(p)) {
				count.put(p, 0);
			}
			count.put(p, count.get(p) + 1);
			m /= p;
		}
	}

	private void precalc() {
		for (int p = 2; p < MAXP; ++p) {
			if (minPrimeDivisor[p] == 0) {
				for (int i = p + p; i < MAXP; i += p) {
					if (minPrimeDivisor[i] == 0) {
						minPrimeDivisor[i] = p;
					}
				}
			}
		}
	}
}
