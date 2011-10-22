package approved.test402518479;

import net.egork.utils.test.Test;
import java.util.Collection;
import net.egork.utils.Exit;
import net.egork.utils.io.StreamInputReader;
import java.io.*;

import net.egork.utils.Solver;
public class Main {
	public static void main(String[] args) {
		try {
			System.setIn(new FileInputStream("a.in"));
			System.setOut(new PrintStream(new FileOutputStream("a.out")));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		net.egork.utils.io.old.InputReader in = new StreamInputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		run(in, out);
	}

	public static void run(net.egork.utils.io.old.InputReader in, PrintWriter out) {
		Solver solver = new TaskA();
		int testCount = in.readInt();
		for (int i = 1; i <= testCount; i++)
			solver.solve(i, in, out);
		Exit.exit(in, out);
	}
}


class MainChecker {
	public static String check(
		net.egork.utils.io.old.InputReader input, net.egork.utils.io.old.InputReader expectedOutput, net.egork.utils.io.old.InputReader actualOutput) {
		return new TaskAChecker().check(input, expectedOutput, actualOutput);
	}

	public static Collection<Test> generateTests() {
		return new TaskAChecker().generateTests();
	}
}

