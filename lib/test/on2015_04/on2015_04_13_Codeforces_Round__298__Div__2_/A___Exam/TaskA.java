package on2015_04.on2015_04_13_Codeforces_Round__298__Div__2_.A___Exam;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		List<Integer> ans = new ArrayList<>();
		ans.add(0);
		for (int i = 2; i < n; i += 2) {
			ans.add(i);
		}
		Collections.reverse(ans);
		--n;
		while (n % 2 == 0) --n;
		if (Math.abs(ans.get(ans.size() - 1) - n) != 1) {
			for (int i = n; i >= 1; i -= 2) {
				ans.add(i);
			}
		}
		out.printLine(ans.size());
		for (int i = 0; i < ans.size(); ++i) {
			if (i > 0) out.print(" ");
			out.print(ans.get(i) + 1);
		}
		out.printLine();
    }
}
