package on2015_04.on2015_04_10_CodeJamQual.StandingOvation;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class StandingOvation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		char[] s = in.readString().toCharArray();
		int ans = 0;
		int sum = 0;
		for (int i = 0; i <= n; ++i) {
			int add = Math.max(0, i - sum);
			ans += add;
			sum += add;
			sum += s[i] - '0';
		}
		out.printLine("Case #" + testNumber + ":", ans);
    }
}
