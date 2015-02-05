package on2015_01.on2015_01_27_Codeforces_Round__288__Div__2_.A___Pasha_and_Pixels;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    int n;
    int m;
    boolean[][] f;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.readInt();
        m = in.readInt();
        f = new boolean[n][];
        for (int i = 0; i < n; ++i) {
            f[i] = ArrayUtils.createArray(m, false);
        }
        int k = in.readInt();
        for (int it = 0; it < k; ++it) {
            int x = in.readInt() - 1;
            int y = in.readInt() - 1;
            f[x][y] = true;
            if (fill(x, y) || fill(x - 1, y) || fill(x, y - 1) || fill(x - 1, y - 1)) {
                out.printLine(it + 1);
                return;
            }
        }
        out.printLine(0);
    }

    private boolean fill(int x, int y) {
        if (x < 0 || y < 0 || x + 1 >= n || y + 1 >= m) return false;
        return f[x][y] && f[x + 1][y] && f[x][y + 1] && f[x + 1][y + 1];
    }
}
