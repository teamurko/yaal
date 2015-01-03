package on2015_01.on2015_01_02_HackerRankProblemset.SpecialMultiple;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class SpecialMultiple {
    private static final int UNDEFINED = -1;
    private static final int[] DIGITS = {0, 9};

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        final int mod = in.readInt();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] used = ArrayUtils.createArray(mod, false);
        int[] prev = ArrayUtils.createArray(mod, UNDEFINED);
        int[] addedDigit = ArrayUtils.createArray(mod, UNDEFINED);
        int source = 9 % mod;
        queue.add(source);
        used[source] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            if (u == 0) break;
            for (int digit : DIGITS) {
                int v = (u * 10 + digit) % mod;
                if (!used[v]) {
                    used[v] = true;
                    queue.add(v);
                    prev[v] = u;
                    addedDigit[v] = digit;
                }
            }
        }
        assert(used[0]);
        List<Integer> path = new ArrayList<>();
        int v = 0;
        while (v != source) {
            int u = prev[v];
            path.add(addedDigit[v]);
            v = u;
        }
        path.add(9);
        Collections.reverse(path);
        for (int digit : path) {
            out.print(digit);
        }
        out.printLine();
    }
}
