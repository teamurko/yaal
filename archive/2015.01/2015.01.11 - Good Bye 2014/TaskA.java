package net.spak;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int target = in.readInt() - 1;
        int[] move = ArrayUtils.createArray(n - 1, 0);
        for (int i = 0; i < n - 1; ++i) {
            move[i] = in.readInt();
        }
        boolean[] used = ArrayUtils.createArray(n, false);
        used[0] = true;
        for (int i = 0; i < n - 1; ++i) {
            if (used[i]) {
                used[i + move[i]] = true;
            }
        }
        if (used[target]) out.printLine("YES");
        else out.printLine("NO");
    }
}
