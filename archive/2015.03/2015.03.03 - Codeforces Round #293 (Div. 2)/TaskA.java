package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String a = in.readString();
		String b = in.readString();
		a = inc(a);
		if (a == null || a.compareTo(b) >= 0) {
			out.printLine("No such string");
		} else {
			out.printLine(a);
		}
    }

	private String inc(String a) {
		char[] s = a.toCharArray();
		for (int i = s.length - 1; i >= 0; --i) {
			if (s[i] != 'z') {
				++s[i];
				for (int j = i + 1; j < s.length; ++j) {
					s[j] = 'a';
				}
				return new String(s);
			}
		}
		return null;
	}
}
