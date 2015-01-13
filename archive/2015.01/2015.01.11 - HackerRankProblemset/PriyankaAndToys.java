package net.spak;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class PriyankaAndToys {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] w = ArrayUtils.createArray(n, 0);
        for (int i = 0; i < n; ++i) {
            w[i] = in.readInt();
        }
        Arrays.sort(w);
        int ans = 0;
        int i = 0;
        while (i < n) {
            int cur = w[i];
            while (i < n && cur + 4 >= w[i]) ++i;
            ++ans;
        }
        out.printLine(ans);
    }
}
