package net.spak;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SherlockAndDivisors {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        testNumber = in.readInt();
        for (int it = 0; it < testNumber; ++it) {
            int n = in.readInt();
            int ans = 0;
            for (int i = 1; i * i <= n; ++i) {
                if (n % i > 0) continue;
                if (i % 2 == 0) ++ans;
                if (i * i != n && n / i % 2 == 0) ++ans;
            }
            out.printLine(ans);
        }
    }

    private int phi(int[] primes, int n) {
        int ret = 1;
        for (int prime : primes) {
            if (prime * prime > n) break;
            if (n % prime > 0) continue;
            int mul = 1;
            while (n % prime == 0) {
                mul *= prime;
                n /= prime;
            }
            mul /= prime;
            mul *= prime - 1;
            ret *= mul;
        }
        if (n > 1) {
            ret *= n - 1;
        }
        return ret;
    }
}
