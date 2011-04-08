package net.egork.timus;

import net.egork.arrays.Array;
import net.egork.collections.CollectionUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;

public class Task1138 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int s = in.readInt();
		int n = in.readInt();
		if (n > s) {
			out.println(0);
			return;
		}
		int[] maxSequence = new int[s + 1];
		maxSequence[n] = 1;
		for (int i = n; i < s; i++) {
			if (maxSequence[i] == 0)
				continue;
			int step = (int) (i / IntegerUtils.gcd(i, 100));
			for (int j = i + step; j <= s; j += step)
				maxSequence[j] = Math.max(maxSequence[j], maxSequence[i] + 1);
		}
		out.println(CollectionUtils.maxElement(Array.create(maxSequence)));
	}
}

