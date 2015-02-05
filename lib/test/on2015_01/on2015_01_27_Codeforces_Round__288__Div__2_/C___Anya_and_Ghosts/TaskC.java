package on2015_01.on2015_01_27_Codeforces_Round__288__Div__2_.C___Anya_and_Ghosts;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TaskC {
    int m, t, r;
    int[] w;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        m = in.readInt();
        t = in.readInt();
        r = in.readInt();
        w = new int[m];
        for (int i = 0; i < m; ++i) {
            w[i] = in.readInt();
        }
        TreeSet<Integer> startTimes = new TreeSet<>();
        int ans = 0;
        for (int i = m - 1; i >= 0; --i) {
            while (!startTimes.isEmpty() && startTimes.descendingIterator().next() >= w[i]) {
                startTimes.remove(startTimes.descendingIterator().next());
            }
            int k = startTimes.size();
            int b = w[i] - t;
            for (int j = 0; j < r - k; ++j) {
                while (startTimes.contains(b) && b < w[i]) ++b;
                if (startTimes.contains(b) || b == w[i]) {
                    out.printLine(-1);
                    return;
                }
                startTimes.add(b);
                ++ans;
            }
        }
        out.printLine(ans);
    }
}
