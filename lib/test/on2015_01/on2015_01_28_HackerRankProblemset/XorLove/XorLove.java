package on2015_01.on2015_01_28_HackerRankProblemset.XorLove;



import net.egork.collections.CollectionUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class XorLove {
    private long mod = 1000000007;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        final int logmax = 21;
        int[][] sum = new int[logmax][n];
        for (int i = 0; i < n; ++i) {
            int a = in.readInt();
            for (int j = 0; j < logmax; ++j) {
                sum[j][i] = (a >> j) & 1;
            }
        }
        for (int j = 0; j < logmax; ++j) {
            for (int i = 1; i < n; ++i) {
                sum[j][i] += sum[j][i - 1];
            }
        }
        int m = in.readInt();
        for (int it = 0; it < m; ++it) {
            int k = in.readInt();
            int l = in.readInt() - 1;
            int r = in.readInt() - 1;
            long ans = 0;
            for (int i = 0; i < logmax; ++i) {
                long oneCount = getSum(sum[i], l, r);
                long zeroCount = r - l + 1 - oneCount;
                if (((k >> i) & 1) == 0) {
                    ans += oneCount * zeroCount % mod * (1 << i) % mod;
                } else {
                    ans += oneCount * (oneCount - 1) / 2 % mod * (1 << i) % mod;
                    ans += zeroCount * (zeroCount - 1) / 2 % mod * (1 << i) % mod;
                }
                ans %= mod;
            }
            out.printLine(ans);
        }
    }

    private int getSum(int[] sum, int l, int r) {
        int ret = sum[r];
        if (l > 0) ret -= sum[l - 1];
        return ret;
    }
}
