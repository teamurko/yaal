package on2015_03.on2015_03_29_Codeforces_Round__297__Div__2_.C___Ilya_and_Sticks;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	private static final int N = 1000007;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] len = new int[N];
		for (int i = 0; i < n; ++i) {
			++len[in.readInt()];
		}
		int[] pairCount = new int[N];
		for (int i = N - 1; i > 0; --i) {
			pairCount[i] += len[i] / 2;
			len[i] %= 2;
			if (len[i] > 0 && len[i - 1] > 0) {
				--len[i];
				--len[i - 1];
				++pairCount[i - 1];
			}
		}
		long area = 0;
		for (int i = N - 1; i > 0; --i) {
			long num = pairCount[i] / 2;
			area += i * num * i;
			pairCount[i] %= 2;
			if (pairCount[i] > 0) {
				int j = i - 1;
				while (j > 0 && pairCount[j] == 0) --j;
				if (j > 0) {
					area += i * (long) j;
					--pairCount[j];
				}
				i = j + 1;
			}
		}
		out.printLine(area);
    }
}
