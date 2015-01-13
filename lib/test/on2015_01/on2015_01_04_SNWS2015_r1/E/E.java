package on2015_01.on2015_01_04_SNWS2015_r1.E;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int s = in.readInt();
        int k = in.readInt();
        boolean[][] f = new boolean[n][];
        for (int i = 0; i < n; ++i) {
            f[i] = ArrayUtils.createArray(m, false);
        }
        for (int i = 0; i < k; ++i) {
            int x = 0;
            int y = 0;
            String path = in.readString();
            f[x][y] = true;
            for (int j = 0; j < path.length(); ++j) {
                char c = path.charAt(j);
                if (c == 'r') ++y;
                if (c == 'u') --x;
                if (c == 'd') ++x;
                if (c == 'l') --y;
                f[x][y] = true;
            }
        }
        int ans = 0;
        boolean[][] used = new boolean[n][];
        for (int i = 0; i < n; ++i) {
            used[i] = ArrayUtils.createArray(m, false);
        }
        for (int x = 0; x < n; ++x) {
            for (int y = 0; y < m; ++y) {
                int sq = dfs(x, y, n, m, f, used);
                if (sq >= s) {
                    ++ans;
                }
            }
        }
        out.printLine(ans);
    }
    int dfs(int x, int y, int n, int m, boolean[][] f, boolean[][] used) {
        if (x < 0 || y < 0 || x >= n || y >= m || used[x][y] || f[x][y]) return 0;
        used[x][y] = true;
        int ret = 1;
        ret += dfs(x + 1, y, n, m, f, used);
        ret += dfs(x - 1, y, n, m, f, used);
        ret += dfs(x, y - 1, n, m, f, used);
        ret += dfs(x, y + 1, n, m, f, used);
        return ret;
    }
}
