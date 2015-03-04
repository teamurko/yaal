package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = new int[n];
		int[] b = new int[n - 1];
		int[] c = new int[n - 2];
		for (int i = 0; i < n; ++i) {
			a[i] = in.readInt();
		}
		for (int i = 0; i < n - 1; ++i) {
			b[i] = in.readInt();
		}
		for (int i = 0; i < n - 2; ++i) {
			c[i] = in.readInt();
		}
		Arrays.sort(a);
		Arrays.sort(b);
		Arrays.sort(c);
		out.printLine(diff(a, b));
		out.printLine(diff(b, c));
	}

	private int diff(int[] a, int[] b) {
		for (int i = 0; i < b.length; ++i) {
			if (a[i] != b[i]) return a[i];
		}
		return a[a.length - 1];
	}

}
