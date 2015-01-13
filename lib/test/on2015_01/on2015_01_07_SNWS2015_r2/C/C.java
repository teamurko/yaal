package on2015_01.on2015_01_07_SNWS2015_r2.C;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        if (n == 0 && k == 0) {
            throw new UnknownError();
        }
        int[][] A = new int[n][];
        for (int i = 0; i < n; ++i) {
            A[i] = ArrayUtils.createArray(k, 0);
            for (int j = 0; j < k; ++j) {
                A[i][j] = in.readInt();
            }
        }
        int[][] B = new int[k][];
        for (int j = 0; j < k; ++j) {
            B[j] = ArrayUtils.createArray(n, 0);
            for (int i = 0; i < n; ++i) {
                B[j][i] = in.readInt();
            }
        }
        int[][] c = mul(B, A, k, n, k);
        int[][] m = pow(c, n * n - 1);
        int[][] res = mul(mul(A, m, n, k, k), B, n, k, n);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += ArrayUtils.sumArray(res[i]);
        }
        out.printLine(ans);
    }

    int[][] pow(int[][] a, int n) {
        int k = a.length;
        int[][] r = new int[k][];
        for (int i = 0; i < k; ++i) {
            r[i] = ArrayUtils.createArray(k, 0);
            r[i][i] = 1;
        }
        while (n > 0) {
            if (n % 2 == 1) {
                r = mul(r, a, k, k, k);
            }
            n >>= 1;
            a = mul(a, a, k, k, k);
        }
        return r;
    }
    int[][] mul(int[][] a, int[][] b, int n, int k, int m) {
        int[][] r = new int[n][];
        for (int i = 0; i < n; ++i) {
            r[i] = ArrayUtils.createArray(m, 0);
            for (int j = 0; j < m; ++j) {
                for (int l = 0; l < k; ++l) {
                    r[i][j] += a[i][l] * b[l][j];
                }
                r[i][j] %= 6;
            }
        }
        return r;
    }
}
