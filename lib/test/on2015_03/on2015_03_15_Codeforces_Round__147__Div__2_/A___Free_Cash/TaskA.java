package on2015_03.on2015_03_15_Codeforces_Round__147__Div__2_.A___Free_Cash;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int ans = 0;
		int count = 0;
		int ph = 0, pm = 0;
		for (int i = 0; i < n; ++i) {
			int h = in.readInt();
			int m = in.readInt();
			if (h == ph && m == pm) {
				++count;
			} else {
				ph = h;
				pm = m;
				count = 1;
			}
			ans = Math.max(ans, count);
		}
		out.printLine(ans);
    }
}
