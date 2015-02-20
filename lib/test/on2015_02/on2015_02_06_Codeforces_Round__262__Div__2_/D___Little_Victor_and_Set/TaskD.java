package on2015_02.on2015_02_06_Codeforces_Round__262__Div__2_.D___Little_Victor_and_Set;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Random;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long l = in.readLong();
		long r = in.readLong();
		int k = in.readInt();
		Random random = new Random();
		if (r - l + 1 <= 10) {
			brute(l, r, k, out);
		} else {
			if (k == 1) {
				out.printLine(l);
				out.printLine(1);
				out.printLine(l);
			} else if (k  == 2) {
				while (l % 4 > 0) ++l;
				out.printLine(1);
				out.printLine(2);
				out.printLine(l + " " + (l + 1));
			} else if (k == 3) {
				long a = 3;
				long b = 2;
				long c = 1;
				while (c < l) {
					c <<= 1;
					++c;
					b <<= 1;
					++b;
					a <<= 1;
				}
				if (a <= r) {
					out.printLine(0);
					out.printLine(3);
					out.printLine(a + " " + b + " " + c);
					return;
				}
				while (l % 4 > 0) ++l;
				out.printLine(1);
				out.printLine(2);
				out.printLine(l + " " + (l + 1));
			} else {
				while (l % 4 > 0) ++l;
				out.printLine(0);
				out.printLine(4);
				out.printLine(l + " " + (l + 1) + " " + (l + 2) + " "  + (l + 3));
			}
		}
    }

	private void brute(long l, long r, int k, OutputWriter out) {
		int n = (int) (r - l + 1);
		long ans = -1;
		int bmask = -1;
		for (int mask = 1; mask < (1 << n); ++mask) {
			if (Integer.bitCount(mask) <= k) {
				long x = 0;
				for (int i = 0; i < n; ++i) {
					if ((mask & (1 << i)) > 0) {
						x ^= l + i;
					}
				}
				if (ans == -1 || ans > x) {
					ans = x;
					bmask = mask;
				}
			}
		}
		out.printLine(ans);
		out.printLine(Integer.bitCount(bmask));
		boolean first = true;
		for (int i = 0; i < n; ++i) {
			if ((bmask & (1 << i)) > 0) {
				if (!first) out.print(" ");
				first = false;
				out.print(l + i);
			}
		}
		out.printLine();
	}
}
