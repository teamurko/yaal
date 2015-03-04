package on2015_03.on2015_03_03_Codeforces_Round__293__Div__2_.B___Tanya_and_Postcard;



import net.egork.collections.map.Counter;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		char[] t = in.readString().toCharArray();
		Counter<Character> counter = new Counter<>();
		for (char c : t) {
			counter.add(c);
		}
		int uraCount = 0, opaCount = 0;
		boolean[] used = new boolean[s.length];
		for (int i = 0; i < s.length; ++i) {
			if (counter.get(s[i]) > 0) {
				counter.add(s[i], -1);
				++uraCount;
				used[i] = true;
			}
		}
		for (int i = 0; i < s.length; ++i) {
			if (!used[i]) {
				if (counter.get(Character.toLowerCase(s[i])) > 0) {
					counter.add(Character.toLowerCase(s[i]), -1);
					++opaCount;
				} else if (counter.get(Character.toUpperCase(s[i])) > 0) {
					counter.add(Character.toUpperCase(s[i]), -1);
					++opaCount;
				}
			}
		}
		out.printLine(uraCount + " " + opaCount);
    }
}
