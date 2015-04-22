package on2015_04.on2015_04_02_Codeforces_Round__143__Div__2_.B___Magic__Wizardry_and_Wonders;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int d = in.readInt();
		int l = in.readInt();
		Stack<Integer> stack = new Stack<>();
		stack.add(d);
		int[] ans = new int[n];
		for (int i = 1; i < n; ++i) {
			int k = stack.pop();
			if (k < 0) {
				stack.add(1);
				stack.add(-k + 1);
				ans[i - 1] = 1;
				ans[i] = -k + 1;
			} else if (k >= l) {
				stack.add(l);
				stack.add(l - k);
				ans[i - 1] = l;
				ans[i] = l - k;
			} else {
				if (k == 0) {
					stack.add(1);
					stack.add(1);
					ans[i - 1] = 1;
					ans[i] = 1;
				} else {
					stack.add(l);
					stack.add(l - k);
					ans[i - 1] = l;
					ans[i] = l - k;
				}
			}
		}
		if (ans[n - 1] <= 0 || ans[n - 1] > l) {
			out.printLine(-1);
			return;
		}
		for (int i = 0; i < n; ++i) {
			if (i > 0) out.print(" ");
			out.print(ans[i]);
		}
		out.printLine();
    }
}
