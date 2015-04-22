package on2015_03.on2015_03_29_Codeforces_Round__297__Div__2_.A___Vitaliy_and_Pie;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int ans = 0;
		Map<Character, Integer> count = new HashMap<>();
		for (char c : in.readString().toCharArray()) {
			if (Character.isLowerCase(c)) {
				if (!count.containsKey(c)) {
					count.put(c, 0);
				}
				count.put(c, count.get(c) + 1);
			} else {
				c = Character.toLowerCase(c);
				if (count.containsKey(c) && count.get(c) > 0) {
					count.put(c, count.get(c) - 1);
				} else {
					++ans;
				}
			}
		}
		out.printLine(ans);
    }
}
