package on2015_04.on2015_04_17_CJ2015R1A.MushroomMonster;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MushroomMonster {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] m = new int[n];
		for (int i = 0; i < n; ++i) m[i] = in.readInt();
		long a = 0;
		long b = 0;
		int l = 0;
		while (l < n) {
			int r = l + 1;
			while (r < n && m[r - 1] >= m[r]) ++r;
			a += m[l] - m[r - 1];
			l = r;
		}
		int rate = 0;
		for (int i = 0; i + 1 < n; ++i) rate = Math.max(rate, m[i] - m[i + 1]);
		for (int i = 0; i + 1 < n; ++i) {
			b += Math.min(rate, m[i]);
		}
		out.printLine("Case #" + testNumber + ":", a, b);
    }
}
