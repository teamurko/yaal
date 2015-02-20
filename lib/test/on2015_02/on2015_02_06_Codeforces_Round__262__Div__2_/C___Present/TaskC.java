package on2015_02.on2015_02_06_Codeforces_Round__262__Div__2_.C___Present;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int w = in.readInt();
		int[] a = new int[n];
		for (int i = 0; i < n; ++i) {
			a[i] = in.readInt();
		}
		int l = ArrayUtils.minElement(a);
		int r = l + m;
		while (l < r) {
			int mid = (l + r + 1) / 2;
			if (enough(mid, a, w, m)) {
				l = mid;
			} else {
				r = mid - 1;
			}
		}
		out.printLine(l);
    }

	int[] cnt;
	private boolean enough(int h, int[] a, int w, int m) {
		int n = a.length;
		if (cnt == null) {
			cnt = new int[n];
		}
		for (int i = 0; i < n; ++i) cnt[i] = 0;
		int bal = 0;
		for (int i = 0; i < n; ++i) {
			bal += cnt[i];
			if (a[i] + bal >= h) {
				continue;
			}
			int delta = h - a[i] - bal;
			if (delta > m) {
				return false;
			}
			m -= delta;
			if (i + w < n) {
				cnt[i + w] -= delta;
			}
			bal += delta;
		}
		return true;
	}
}
