package on2015_01.on2015_01_08_FB15_q.NewYearResolution;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class NewYearResolution {
    class Food {
        final int p;
        final int c;
        final int f;

        public Food(int p, int c, int f) {
            this.p = p;
            this.c = c;
            this.f = f;
        }
    }
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int P = in.readInt();
        int C = in.readInt();
        int F = in.readInt();
        int n = in.readInt();
        Food[] food = new Food[n];
        for (int i = 0; i < n; ++i) {
            int p = in.readInt();
            int c = in.readInt();
            int f = in.readInt();
            food[i] = new Food(p, c, f);
        }
        boolean found = false;
        for (int mask = 0; mask < (1 << n); ++mask) {
            int p = 0, c = 0, f = 0;
            for (int i = 0; i < n; ++i) {
                if ((mask >> i) % 2 == 1) {
                    p += food[i].p;
                    f += food[i].f;
                    c += food[i].c;
                    if (p > P) break;
                    if (c > C) break;
                    if (f > F) break;
                }
            }
            if (p == P && c == C && f == F) {
                found = true;
                break;
            }
        }
        out.printLine(String.format("Case #%d: %s", testNumber, found ? "yes" : "no"));
    }
}
