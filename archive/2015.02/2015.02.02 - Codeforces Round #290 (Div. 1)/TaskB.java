package net.spak;

import net.egork.collections.Pair;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    private static final int INF = (int) 1e8;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] cost = new int[n];
        int[] a = new int[n];
        int[][] primes = new int[n][];
        for (int i = 0; i < n; ++i) {
            Pair<Integer, int[]> res = reduce(in.readInt());
            a[i] = res.first;
            primes[i] = res.second;
        }
        for (int i = 0; i < n; ++i) cost[i] = in.readInt();

        int minCost = -1;
        for (int firstId = 0; firstId < n; ++firstId) {
            for (int i = 0; i < n; ++i) {
                if (i == firstId) continue;
                int mask = 0;
                for (int j = 0; j < primes[firstId].length; ++j) {
                    if (a[i] % primes[firstId][j] == 0) {
                        mask |= 1 << j;
                    }
                }
                masks[i] = mask;
            }
            int m = primes[firstId].length;
            for (int i = 0; i < (1 << m); ++i) d[i] = INF;
            d[(1 << m) - 1] = cost[firstId];
            for (int i = 0; i < n; ++i) {
                if (i == firstId) continue;
                for (int mask = 1; mask < (1 << m); ++mask) {
                    if (d[mask] == INF) continue;
                    if ((masks[i] & mask) == mask) continue;
                    int nmask = mask & masks[i];
                    d[nmask] = Math.min(d[nmask], d[mask] + cost[i]);
                }
            }
            if (d[0] != INF) {
                if (minCost == -1 || minCost > d[0]) minCost = d[0];
            }
        }
        out.printLine(minCost);
    }

    int[] masks = new int[500];
    int[] buf = new int[100];
    int[] d = new int[2000];
    private Pair<Integer, int[]> reduce(int n) {
        int bs = 0;
        for (int p = 2; p * p <= n; ++p) {
            if (n % p == 0) {
                while (n % p == 0) n /= p;
                buf[bs++] = p;
            }
        }
        if (n > 1) {
            buf[bs++] = n;
        }
        int res = 1;
        for (int i = 0; i < bs; ++i) res *= buf[i];
        int[] primes = new int[bs];
        for (int i = 0; i < bs; ++i) primes[i] = buf[i];
        return Pair.makePair(res, primes);
    }
}
