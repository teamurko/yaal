package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n1 = in.readInt();
		int n2 = in.readInt();
		if (n1 > n2) out.printLine("First");
		else out.printLine("Second");
    }
}
