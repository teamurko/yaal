package net.egork.y2011.m4.d9.hugeeasycontest;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		char[][] field = in.readTable(n, n);
		int result = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (field[i][j] == '.')
					continue;
				boolean hasNonSunkPart = false;
				for (int k = 0; i + k < n && field[i + k][j] != '.'; k++) {
					hasNonSunkPart |= field[i + k][j] == 'x';
					field[i + k][j] = '.';
				}
				for (int k = 1; j + k < n && field[i][j + k] != '.'; k++) {
					hasNonSunkPart |= field[i][j + k] == 'x';
					field[i][j + k] = '.';
				}
				if (hasNonSunkPart)
					result++;
			}
		}
		out.println("Case " + testNumber + ": " + result);
	}
}

