package on2015_04.on2015_04_13_Codeforces_Round__298__Div__2_.C___Polycarpus__Dice;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		long a = in.readLong();
		long[] d = new long[n];
		for (int i = 0; i < n; ++i) {
			d[i] = in.readLong();
		}
		long sum = 0;
		for (long x : d) {
			sum += x;
		}
		StringBuilder sb = new StringBuilder();
		for (long x : d) {
			// a = y + [n - 1,sum - x]
			long mx = a - n + 1;
			long mn  = a - sum + x;
			mn = Math.max(1, mn);
			mx = Math.min(mx, x);
			if (sb.length() > 0) sb.append(" ");
			sb.append(Math.max(0, x - (mx - mn + 1)));
		}
		out.printLine(sb);
    }
}
