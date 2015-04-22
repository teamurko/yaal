package on2015_03.on2015_03_21_VK_Cup_2015___Round_1__unofficial_online_mirror__Div__1_only_.E___The_Art_of_Dealing_with_ATM;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		long[] nom = new long[n];
		Map<Long, Integer> minCount = new HashMap<>();
		for (int i = 0; i < n; ++i) {
			nom[i] = in.readLong();
			for (int j = 1; j <= k; ++j) {
				long key = j * nom[i];
				if (minCount.containsKey(key)) {
					minCount.put(key, Math.min(minCount.get(key), j));
				} else {
					minCount.put(key, j);
				}
			}
		}
		int q = in.readInt();
		for (int i = 0; i < q; ++i) {
			long x = in.readLong();
			int ans = 100;
			for (int j = 0; j < n; ++j) {
				for (int t = 1; t <= k; ++t) {
					long y = x - t * nom[j];
					if (y < 0) continue;
					if (y == 0) {
						ans = Math.min(ans, t);
					} else {
						if (minCount.containsKey(y)) {
							if (t + minCount.get(y) <= k) {
								ans = Math.min(ans, t + minCount.get(y));
							}
						}
					}

				}
			}
			if (ans == 100) ans = -1;
			out.printLine(ans);
		}
    }
}
