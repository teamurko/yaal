package on2015_02.on2015_02_10_Codeforces_Round__214__Div__2_.B___Dima_and_To_do_List;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		long[] sum = ArrayUtils.createArray(k, 0L);
		for (int i = 0; i < n; ++i) {
			sum[i % k] += in.readInt();
		}
		int ans = 0;
		for (int i = 1; i < k; ++i) {
			if (sum[ans] > sum[i]) ans = i;
		}
		out.printLine(ans + 1);
    }
}
