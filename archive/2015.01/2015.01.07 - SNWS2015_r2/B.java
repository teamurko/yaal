package net.spak;

import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long m = in.readLong();
        int n = in.readInt();
        int[] d10 = ArrayUtils.createArray(9, 1);
        for (int i = 1; i <= 8; ++i) {
            d10[i] = 10 * d10[i - 1];
        }
        long ans = 0;
        long cur = 0;
        int div = 1 << n;
        for (int i = 1; i < d10[n - 1] && i <= m; ++i) {
            int c = numDigits(i);
            cur = (cur * d10[c] + i) % d10[n];
            if (cur % div == 0) {
                ++ans;
            }
        }
        if (d10[n - 1] <= m) {
            ans += calc(d10[n - 1], m, div);
        }
        out.printLine(ans);
    }

    private long calc(long a, long b, int div) {
        return b / div - (a - 1) / div;
    }

    private int numDigits(int i) {
        int r = 0;
        while (i > 0) {
            ++r;
            i /= 10;
        }
        return r;
    }
}
