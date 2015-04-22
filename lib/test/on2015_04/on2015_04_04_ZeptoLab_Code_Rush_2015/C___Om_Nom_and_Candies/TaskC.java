package on2015_04.on2015_04_04_ZeptoLab_Code_Rush_2015.C___Om_Nom_and_Candies;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long c = in.readLong();
		long hr = in.readLong();
		long hb = in.readLong();
		long wr = in.readLong();
		long wb = in.readLong();
		long ans = calc(hr, wr, hb, wb, c);
		ans = Math.max(ans, calc(hb, wb, hr, wr, c));
		out.printLine(ans);
    }

	private long calc(long hr, long wr, long hb, long wb, long c) {
		long n = c / wr;
		long ret = n * hr;
		ret += (c % wr / wb) * hb;
		for (int i = 0; i < 10000000; ++i) {
			if (i * wb > c) break;
			long w = c - i * wb;
			ret = Math.max(ret, i * hb + (w / wr) * hr);
		}
		return ret;
	}
}
