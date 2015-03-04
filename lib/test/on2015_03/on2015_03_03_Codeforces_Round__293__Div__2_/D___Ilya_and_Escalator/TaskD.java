package on2015_03.on2015_03_03_Codeforces_Round__293__Div__2_.D___Ilya_and_Escalator;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		double p = in.readDouble();
		int t = in.readInt();
		double[][] dp = new double[t + 1][n + 1];
		dp[0][0] = 1.0;
		for (int it = 0; it < t; ++it) {
			for (int i = 0; i <= n; ++i) {
				dp[it + 1][i] += dp[it][i] * (1 - p);
				dp[it + 1][Math.min(i + 1, n)] += dp[it][i] * p;
			}
		}
		double e = 0;
		for (int i = 1; i <= n; ++i) {
			e += dp[t][i] * i;
		}
		out.printLine(e);
    }
}
