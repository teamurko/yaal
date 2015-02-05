package net.spak;

import net.egork.collections.Pair;

public class BuildingTowersEasy {
    public int maxHeight(int N, int[] x, int[] t) {
		for (int i = x.length - 2; i >= 0; --i) {
            for (int j = i + 1; j < x.length; ++j) {
                t[i] = Math.min(t[i], t[j] + x[j] - x[i]);
            }
        }
        int height = 0;
        int pos = 0;
        int res = 0;
        for (int i = 0; i < x.length; ++i) {
            if (x[i] == 1) continue;
            Pair<Integer, Integer> p = up(height, pos, t[i], x[i] - 1);
            res = Math.max(res, p.first);
            height = p.second;
            pos = x[i] - 1;
        }
        height += N - pos - 1;
        res = Math.max(res, height);
        return res;
    }

    private Pair<Integer, Integer> up(int height, int pos, int limit, int nextPos) {
        int x1 = (limit - height + nextPos - pos) / 2;
        int x2 = nextPos - pos;
        if (x2 <= x1) {
            return Pair.makePair(height + x2, height + x2);
        }
        return Pair.makePair(height + x1, limit);
    }
}
