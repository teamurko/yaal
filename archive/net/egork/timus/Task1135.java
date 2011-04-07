package net.egork.timus;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class Task1135 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		long left = 0;
		long result = 0;
		for (int i = 0; i < n; i++) {
			if (in.readCharacter() == '>')
				left++;
			else
				result += left;
		}
		out.println(result);
	}
}

