package on2015_03.on2015_03_02_Codeforces_Round__294__Div__2_.D___A_and_B_and_Interesting_Substrings;



import net.egork.collections.map.Counter;
import net.egork.collections.set.MultiSet;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TaskD {
	int[] weight;
	int[] string;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		weight = new int[26];
		for (int i = 0; i < 26; ++i) {
			weight[i] = in.readInt();
		}
		string = ordOf(in.readString());
		int n = string.length;
		long[] psum = new long[n + 1];
		for (int i = 0; i < n; ++i) {
			psum[i + 1] = psum[i] + weight[string[i]];
		}
		long ans = 0;
		for (int c = 0; c < 26; ++c) {
			int[] next = new int[n];
			for (int i = n - 1; i >= 0; --i) {
				if (c == string[i]) next[i] = i;
				else if (i + 1 == n) {
					next[i] = n;
				} else {
					next[i] = next[i + 1];
				}
			}
			Counter<Long> counter = new Counter<>();
			List<Long> sums = new ArrayList<>();
			int start = next[0];
			if (start + 1 >= n) continue;
			int end = next[start + 1];
			while (end < n) {
				counter.add(psum[end] - psum[start + 1]);
				sums.add(psum[end] - psum[start + 1]);
				if (end + 1 == n) break;
				end = next[end + 1];
			}
			long base = 0;
			for (long x : sums) {
				ans += counter.get(base);
				counter.add(x, -1);
				base = x + weight[c];
			}
		}
		out.printLine(ans);
    }

	private int[] ordOf(String s) {
		int[] res = new int[s.length()];
		int i = 0;
		for (char c : s.toCharArray()) {
			res[i++] = Character.getNumericValue(c) - Character.getNumericValue('a');
		}
		return res;
	}
}
