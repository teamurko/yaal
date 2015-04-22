package on2015_03.on2015_03_19_Codeforces_Round__212__Div__2_.D___Fools_and_Foolproof_Roads;



import net.egork.collections.Pair;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int p = in.readInt();
		int q = in.readInt();
		IndependentSetSystem iss = new RecursiveIndependentSetSystem(n);
		Map<Integer, Long> idToWeight = new HashMap<>();
		for (int i = 0; i < n; ++i) idToWeight.put(i, 0L);
		int uu = -1;
		int vv = -1;
		for (int i = 0; i < m; ++i) {
			int u = in.readInt() - 1;
			int v = in.readInt() - 1;
			long w = in.readInt();
			uu = u;
			vv = v;
			u = iss.get(u);
			v = iss.get(v);
			if (u == v) {
				idToWeight.put(u, idToWeight.get(u) + w);
			} else {
				w += idToWeight.get(u) + idToWeight.get(v);
				iss.join(u, v);
				u = iss.get(u);
				idToWeight.put(u, w);
			}
		}
		if (q > iss.getSetCount()) {
			out.printLine("NO");
			return;
		}
		if (q == n && p > 0) {
			out.printLine("NO");
			return;
		}
		q = iss.getSetCount() - q;
		TreeSet<Pair<Long, Integer>> queue = new TreeSet<>();
		for (int v = 0; v < n; ++v) {
			int parent = iss.get(v);
			if (!idToWeight.containsKey(parent)) continue;
			long w = idToWeight.get(parent);
			idToWeight.remove(parent);
			queue.add(Pair.makePair(w, parent));
		}
		if (q > p) {
			out.printLine("NO");
			return;
		}
		out.printLine("YES");
		while (q > 0) {
			Pair<Long, Integer> first = queue.pollFirst();
			Pair<Long, Integer> second = queue.pollFirst();
			uu = first.second;
			vv = second.second;
			out.printLine(first.second + 1, second.second + 1);
			long w = Math.min(first.first + second.first + 1, 1000000000L);
			w += first.first + second.first;
			queue.add(Pair.makePair(w, first.second));
			--q;
			--p;
		}
		while (p > 0) {
			out.printLine(uu + 1, vv + 1);
			--p;
		}
    }
}
