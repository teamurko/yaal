package on2015_02.on2015_02_09_Codeforces_Round__234__Div__2_.A___Inna_and_Choose_Options;



import net.egork.collections.Pair;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String cards = in.readString();
		List<Pair<Integer, Integer>> ans = new ArrayList<>();
		for (int a : new int[]{1, 2, 3, 4, 6, 12}) {
			int b = 12 / a;
			boolean win = false;
			for (int i = 0; i < b; ++i) {
				boolean allX = true;
				for (int j = 0; j < a; ++j) {
					if (cards.charAt(b * j + i) != 'X') {
						allX = false;
						break;
					}
				}
				if (allX) {
					win = true;
					break;
				}
			}
			if (win) {
				ans.add(Pair.makePair(a, b));
			}
		}
		out.print(ans.size());
		for (Pair<Integer, Integer> p : ans) {
			out.print(" ");
			out.print(p.first + "x" + p.second);
		}
		out.printLine();
    }
}
