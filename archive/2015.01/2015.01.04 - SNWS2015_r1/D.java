package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        double ans = 0;
        for (int k = 1; k <= n; ++k) {
            ans = 1 + (n - k) * 1.0 / k + ans;
        }
        out.printLine(ans);
    }
}
