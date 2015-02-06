package on2015_02.on2015_02_05_Codeforces_Round__264__Div__2_.A___Caisa_and_Sugar;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int s = in.readInt() * 100;
		int ans = -1;
		for (int i = 0; i < n; ++i) {
			int cost = in.readInt() * 100;
			cost += in.readInt();
			if (cost <= s) {
				ans = Math.max(ans, (100 - cost % 100) % 100);
			}
		}
		out.printLine(ans);
    }
}
