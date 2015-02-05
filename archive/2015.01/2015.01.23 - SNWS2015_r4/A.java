package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long sum = 0;
        long mx = 0;
        for (int i = 0; i < n; ++i) {
            long x = in.readInt();
            mx = Math.max(mx, x);
            sum += x;
        }
        if (sum - mx < mx) {
            out.printLine(mx);
        } else {
            out.printLine((sum + 1) / 2);
        }
    }
}
