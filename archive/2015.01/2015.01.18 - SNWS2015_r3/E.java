package net.spak;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        if (n == 1) {
            out.printLine(0);
            return;
        }
        int ans = -1;
        for (int p = 2; p * p <= n; ++p) {
            if (n % p > 0) continue;
            if (k % p > 0) {
                out.printLine(-1);
                return;
            }
            int cn = 0;
            while (n % p == 0) {
                n /= p;
                ++cn;
            }
            int ck = 0;
            while (k % p == 0) {
                k /= p;
                ++ck;
            }
            ans = Math.max(ans, (cn + ck - 1) / ck);
        }
        if (n > 1) {
            if (k % n > 0) {
                out.printLine(-1);
                return;
            }
            ans = Math.max(ans, 1);
        }
        out.printLine(ans);
    }
}
