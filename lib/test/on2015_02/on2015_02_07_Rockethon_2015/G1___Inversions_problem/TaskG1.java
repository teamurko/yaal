package on2015_02.on2015_02_07_Rockethon_2015.G1___Inversions_problem;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG1 {
	long cnt = 0;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		int[] p = new int[n];
		for (int i = 0; i < n; ++i) p[i] = in.readInt() - 1;
		calc(p, k);
		int s = n * (n - 1) / 2 + n;
		double ans = cnt;
		for (int i = 0; i < k; ++i) ans /= s;
		out.printLine(ans);
    }

	private void calc(int[] p, int k) {
		if (k == 0) {
			cnt += invCount(p);
			return;
		}
		for (int i = 0; i < p.length; ++i) {
			for (int j = 0; j <= i; ++j) {
				reverse(p, j, i);
				calc(p, k - 1);
				reverse(p, j, i);
			}
		}
	}

	private void reverse(int[] p, int l, int r) {
		if (l == r) return;
		while (l < r) {
			int t = p[l];
			p[l] = p[r];
			p[r] = t;
			++l;
			--r;
		}
	}

	private long invCount(int[] p) {
		int res = 0;
		for (int i = 0; i < p.length; ++i) {
			for (int j = 0; j < i; ++j) {
				if (p[j] > p[i]) ++res;
			}
		}
		return res;
	}
}
