package on2015_01.on2015_01_11_Good_Bye_2014.E___New_Year_Domino;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
    public static final int INF = (int) 1e9;
    static class Domino {
        public final int x;
        public final int length;

        private Domino(int x, int length) {
            this.x = x;
            this.length = length;
        }

        static Domino from(int x, int length) {
            return new Domino(x, length);
        }
    }
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        Domino[] dominos = new Domino[n];
        int[] posToX = ArrayUtils.createArray(n, 0);
        for (int i = 0; i < n; ++i) {
            int x = in.readInt();
            int len = in.readInt();
            dominos[i] = Domino.from(x, len);
            posToX[i] = x;
        }
        IntervalTree rightMostIdTree = new LongIntervalTree(n) {
            @Override
            protected long joinValue(long left, long right) {
                return Math.max(left, right);
            }

            @Override
            protected long joinDelta(long was, long delta) {
                return Math.max(was, delta);
            }

            @Override
            protected long accumulate(long value, long delta, int length) {
                return Math.max(value, delta);
            }

            @Override
            protected long neutralValue() {
                return -INF;
            }

            @Override
            protected long neutralDelta() {
                return -INF;
            }
        };
        rightMostIdTree.init();
        IntervalTree rightMostXTree = new LongIntervalTree(n) {
            @Override
            protected long joinValue(long left, long right) {
                return Math.max(left, right);
            }

            @Override
            protected long joinDelta(long was, long delta) {
                return Math.max(was, delta);
            }

            @Override
            protected long accumulate(long value, long delta, int length) {
                return Math.max(value, delta);
            }

            @Override
            protected long neutralValue() {
                return -INF;
            }

            @Override
            protected long neutralDelta() {
                return -INF;
            }
        };
        rightMostXTree.init();
        final int LOGMAX = 20;
        int[][] next = new int[LOGMAX][n];
        long[][] cost = new long[LOGMAX][n];
        for (int i = n - 1; i >= 0; --i) {
            Domino d = dominos[i];
            int to = Arrays.binarySearch(posToX, d.x + d.length);
            if (to < 0) {
                to = -to - 1;
            }
            int mx = (int) rightMostIdTree.query(i + 1, to - 1);
            if (mx == -INF) {
                to = i + 1;
            } else {
                to = mx;
            }
            next[0][i] = to;
            if (to < n) {
                cost[0][i] = dominos[to].x - Math.max(rightMostXTree.query(i + 1, to - 1), d.x + d.length);
                assert(cost[0][i] > 0);
            }
            rightMostIdTree.update(i, i, next[0][i]);
            rightMostXTree.update(i, i, Math.max(d.x + d.length, rightMostXTree.query(i + 1, to - 1)));
        }
        for (int i = 1; i < LOGMAX; ++i) {
            for (int j = 0; j < n; ++j) {
                if (next[i - 1][j] != n) {
                    next[i][j] = next[i - 1][next[i - 1][j]];
                    cost[i][j] = cost[i - 1][j] + cost[i - 1][next[i - 1][j]];
                } else {
                    next[i][j] = next[i - 1][j];
                    cost[i][j] = cost[i - 1][j];
                }
            }
        }

        int q = in.readInt();
        for (int it = 0; it < q; ++it) {
            int from = in.readInt() - 1;
            int to = in.readInt() - 1;
            long ans = 0;
            for (int i = LOGMAX - 1; i >= 0; --i) {
                if (next[i][from] <= to) {
                    ans += cost[i][from];
                    from = next[i][from];
                }
            }
            out.printLine(ans);
        }
    }
}
