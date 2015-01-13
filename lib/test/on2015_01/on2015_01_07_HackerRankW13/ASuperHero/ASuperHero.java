package on2015_01.on2015_01_07_HackerRankW13.ASuperHero;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;

public class ASuperHero {
    private static final int INF = (int) 1e8;
    private static final int UNDEFINED = -1;

    static final class Bot {
        int p;
        int b;
        int id;
    }
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        final int n = in.readInt();
        final int m = in.readInt();
        Bot[][] bots = new Bot[n][];
        for (int i = 0; i < n; ++i) {
            bots[i] = new Bot[m];
            for (int j = 0; j < m; ++j) {
                bots[i][j] = new Bot();
                bots[i][j].id = j;
                bots[i][j].p = in.readInt();
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                bots[i][j].b = in.readInt();
            }
            Arrays.sort(bots[i], new Comparator<Bot>() {
                @Override
                public int compare(Bot o1, Bot o2) {
                    return o1.b - o2.b;
                }
            });
        }
        int[][] minBullets = new int[n][];
        for (int i = 0; i < n; ++i) {
            minBullets[i] = ArrayUtils.createArray(m, INF);
        }
        for (int i = 0; i < m; ++i) {
            minBullets[0][bots[0][i].id] = bots[0][i].p;
        }
        for (int i = 1; i < n; ++i) {
            Bot[] bs = Arrays.copyOf(bots[i], m);
            Arrays.sort(bs, new Comparator<Bot>() {
                @Override
                public int compare(Bot o1, Bot o2) {
                    return o1.p - o2.p;
                }
            });
            int mn[] = ArrayUtils.createArray(m, INF);
            for (int j = m - 1; j >= 0; --j) {
                mn[j] = minBullets[i - 1][bots[i - 1][j].id];
                if (j + 1 < m) {
                    mn[j] = Math.min(mn[j], mn[j + 1]);
                }
            }
            int k = 0;
            int mnId = UNDEFINED;
            for (Bot bot : bs) {
                while (k < m && bots[i - 1][k].b < bot.p) {
                    Bot bt = bots[i - 1][k];
                    if (mnId == UNDEFINED || minBullets[i - 1][bt.id] - bt.b < minBullets[i - 1][bots[i - 1][mnId].id] - bots[i - 1][mnId].b) {
                        mnId = k;
                    }
                    ++k;
                }
                if (k < m) {
                    minBullets[i][bot.id] = Math.min(minBullets[i][bot.id], mn[k]);
                }
                if (mnId != UNDEFINED) {
                    int candidate = minBullets[i - 1][bots[i - 1][mnId].id] + bot.p - bots[i - 1][mnId].b;
                    minBullets[i][bot.id] = Math.min(minBullets[i][bot.id], candidate);
                }
            }
        }
        out.printLine(ArrayUtils.minElement(minBullets[n - 1]));
    }
}
