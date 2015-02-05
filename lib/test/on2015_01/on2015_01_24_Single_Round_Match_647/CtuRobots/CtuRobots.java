package on2015_01.on2015_01_24_Single_Round_Match_647.CtuRobots;



import net.egork.collections.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CtuRobots {
    public double maxDist(int B, int[] cost, int[] cap) {
        List<Pair<Integer, Integer>> a = new ArrayList<>(cost.length);
        int n = cost.length;
        for (int i = 0; i < n; ++i) {
            a.add(Pair.makePair(cap[i], cost[i]));
        }
        Collections.sort(a, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return o1.first - o2.first;
            }
        });
        for (int i = 0; i < n; ++i) {
            cost[i] = a.get(i).second;
            cap[i] = a.get(i).first;
        }
        double[][] d = new double[n + 1][];
        for (int i = 0; i <= n; ++i) {
            d[i] = new double[B + 1];
            for (int j = 0; j <= B; ++j) d[i][j] = -2D;
        }
        d[0][0] = 0.0D;
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            int nmx = mx;
            for (int b = 0; b <= mx; ++b) {
                if (d[i][b] < -1D) continue;
                d[i + 1][b] = Math.max(d[i + 1][b], d[i][b]);
                if (b + cost[i] > B) continue;
                nmx = Math.max(nmx, b + cost[i]);
                d[i + 1][b + cost[i]] = Math.max(d[i + 1][b + cost[i]], d[i][b] / 3 + cap[i] * 0.5);
            }
            mx = nmx;
        }
        double res = 0.0;
        for (int b = 0; b <= B; ++b) {
            res = Math.max(res, d[n][b]);
        }
		return res;
    }
}
