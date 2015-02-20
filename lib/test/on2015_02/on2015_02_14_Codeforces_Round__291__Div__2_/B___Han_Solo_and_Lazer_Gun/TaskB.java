package on2015_02.on2015_02_14_Codeforces_Round__291__Div__2_.B___Han_Solo_and_Lazer_Gun;



import net.egork.collections.Pair;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		long x0 = in.readInt();
		long y0 = in.readInt();
		long[] x = new long[n];
		long[] y = new long[n];
		Set<Pair<Long, Long>> pts = new HashSet<>();
		for (int i = 0; i < n; ++i) {
			x[i] = in.readInt() - x0;
			y[i] = in.readInt() - y0;
			if (y[i] < 0 || (y[i] == 0 && x[i] < 0)) {
				x[i] = -x[i];
				y[i] = -y[i];
			}
			if (x[i] == 0 && y[i] == 0) continue;
			long d = IntegerUtils.gcd(Math.abs(x[i]), y[i]);
			x[i] /= d;
			y[i] /= d;
			pts.add(Pair.makePair(x[i], y[i]));
		}
		out.printLine(Math.max(1, pts.size()));
    }
}
