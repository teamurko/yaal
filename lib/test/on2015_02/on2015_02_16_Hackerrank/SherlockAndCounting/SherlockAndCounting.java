package on2015_02.on2015_02_16_Hackerrank.SherlockAndCounting;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SherlockAndCounting {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long n = in.readLong();
		long k = in.readLong();
		long limit = n * k;
		long l = 1, r = n / 2;
		while (l < r) {
			long m = (l + r + 1) >> 1;
			if (m * (n - m) <= limit) {
				l = m;
			} else {
				r = m - 1;
			}
		}
		long ans = 2 * r;
		if (r * 2 == n) --ans;
		out.printLine(ans);
    }
}
