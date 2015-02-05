package on2015_02.on2015_02_03_Codeforces_Round__289__Div__2__ACM_ICPC_Rules_.B___Painting_Pebbles;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		int[] a = ArrayUtils.createArray(n, 0);
		int[][] color = new int[n][];
		for (int i = 0; i < n; ++i) {
			a[i] = in.readInt();
			color[i] = new int[a[i]];
		}
		int mx = ArrayUtils.maxElement(a);
		int mn = ArrayUtils.minElement(a);
		while (k > 0 && mx > 1 + mn) {
			for (int i = 0; i < n; ++i) {
				if (a[i] == mx) {
					--a[i];
					color[i][a[i]] = k;
				}
			}
			--k;
			--mx;
		}
		if (k == 0) {
			out.printLine("NO");
			return;
		}
		for (int i = 0; i < n; ++i) {
			while (a[i] > 0) {
				--a[i];
				color[i][a[i]] = k;
			}
		}
		out.printLine("YES");
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < color[i].length; ++j) {
				if (j > 0) out.print(" ");
				out.print(color[i][j]);
			}
			out.printLine();
		}
    }
}
