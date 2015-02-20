package on2015_02.on2015_02_09_Codeforces_Round__234__Div__2_.B___Inna_and_New_Matrix_of_Candies;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import sun.awt.UNIXToolkit;

import java.util.HashSet;
import java.util.Set;

public class TaskB {
	private static final int UNDEFINED = -1;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		Set<Integer> dist = new HashSet<>();
		for (int i = 0; i < n; ++i) {
			String row = in.readString();
			int candyPos = UNDEFINED;
			int gnomePos = UNDEFINED;
			for (int j = 0; j < m; ++j) {
				if (row.charAt(j) == 'G') gnomePos = j;
				else if (row.charAt(j) == 'S') candyPos = j;
			}
			if (candyPos < gnomePos) {
				out.printLine(-1);
				return;
			}
			if (candyPos > gnomePos) {
				dist.add(candyPos - gnomePos);
			}
		}
		out.printLine(dist.size());
    }
}
