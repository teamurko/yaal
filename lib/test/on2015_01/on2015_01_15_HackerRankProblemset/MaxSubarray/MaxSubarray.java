package on2015_01.on2015_01_15_HackerRankProblemset.MaxSubarray;



import net.egork.collections.CollectionUtils;
import net.egork.collections.filter.Filter;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class MaxSubarray {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = ArrayUtils.createArray(n, 0);
        for (int i = 0; i < n; ++i) a[i] = in.readInt();
        out.printLine(maxCont(a), maxNonCont(a));
    }

    private int maxCont(int[] a) {
        int res = ArrayUtils.maxElement(a);
        int s = 0;
        for (int x : a) {
            s += x;
            if (s < 0) s = 0;
            else {
                res = Math.max(res, s);
            }
        }
        return res;
    }

    private int maxNonCont(int[] a) {
        int res = 0;
        boolean found = false;
        for (int x : a) {
            if (x >= 0) {
                found = true;
                res += x;
            }
        }
        if (!found) return ArrayUtils.maxElement(a);
        else return res;
    }
}
