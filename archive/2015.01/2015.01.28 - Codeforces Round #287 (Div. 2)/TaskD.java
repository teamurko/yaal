package net.spak;

import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int mod = in.readInt();
        int[][] d = new int[n + 1][];
        int[] pow10 = new int[n + 2];
        pow10[0] = 1 % k;
        for (int i = 0; i <= n; ++i) {
            d[i] = ArrayUtils.createArray(k, 0);
            pow10[i + 1] = pow10[i] * 10 % k;
        }
        for (int r = 1; r < 10; ++r) {
            ++d[1][r % k];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < k; ++j) {
                if (d[i][j] > 0) {
                    for (int digit = 0; digit < 10; ++digit) {
                        if (digit == 0 && i + 1 == n) continue;
                        int r = (pow10[i] * digit + j) % k;
                        if (r > 0) {
                            d[i + 1][r] += d[i][j];
                            if (d[i + 1][r] >= mod) {
                                d[i + 1][r] -= mod;
                            }
                        }
                    }
                }
            }
            for (int digit = 1; digit < 10; ++digit) {
                int r = pow10[i] * digit % k;
                if (r > 0) {
                    d[i + 1][r] += 1;
                    if (d[i + 1][r] >= mod) {
                        d[i + 1][r] -= mod;
                    }
                }
            }
        }
        long ans = IntegerUtils.power(10, n - 1, mod) * 9 % mod;
        for (int r = 1; r < k; ++r) {
            ans -= d[n][r];
        }
        ans = ((ans % mod) + mod) % mod;
        out.printLine(ans);
    }
}
