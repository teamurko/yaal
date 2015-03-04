package on2015_03.on2015_03_02_Codeforces_Round__294__Div__2_.A___A_and_B_and_Chess;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		Map<Character, Integer> weight = new HashMap<>();
		weight.put('q', 9);
		weight.put('r', 5);
		weight.put('b', 3);
		weight.put('n', 3);
		weight.put('p', 1);

		int whiteWeight = 0;
		int blackWeight = 0;
		for (int i = 0; i < 8; ++i) {
			String line = in.readString();
			for (char c : line.toCharArray()) {
				if (c == '.' || c == 'k' || c == 'K') continue;
				if (Character.toUpperCase(c) == c) {
					whiteWeight += weight.get(Character.toLowerCase(c));
				} else {
					blackWeight += weight.get(c);
				}
			}
		}
		if (whiteWeight > blackWeight) {
			out.printLine("White");
		} else if (whiteWeight == blackWeight) {
			out.printLine("Draw");
		} else {
			out.printLine("Black");
		}
	}
}
