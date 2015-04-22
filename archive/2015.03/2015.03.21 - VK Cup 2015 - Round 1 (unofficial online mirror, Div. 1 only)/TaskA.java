package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int n = s.length;
		String ans = null;
		for (int shift = 0; shift < n; ++shift) {
			char[] t = rotated(s, shift);
			int balance = 0;
			int minBalance = 0;
			for (char c : t) {
				if (c == '(') ++balance;
				else --balance;
				minBalance = Math.min(minBalance, balance);
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < -minBalance; ++i) {
				sb.append('(');
			}
			balance = sb.length();
			for (char c : t) {
				if (c == '(') ++balance;
				else --balance;
				sb.append(c);
			}
			for (int i = 0; i < balance; ++i) sb.append(')');
			String cand = sb.toString();
			if (isLess(cand, ans)) {
				ans = cand;
			}
		}
		out.printLine(ans);
    }

	private boolean isLess(String a, String b) {
		if (b == null) return true;
		if (a.length() < b.length()) return true;
		if (a.length() > b.length()) return false;
		for (int i = 0; i < b.length(); ++i) {
			if (a.charAt(i) != b.charAt(i)) {
				return a.charAt(i) == '(';
			}
		}
		return false;
	}

	private char[] rotated(char[] s, int shift) {
		char[] ret = new char[s.length];
		for (int i = 0; i < s.length; ++i) {
			ret[i] = s[(i + shift) % s.length];
		}
		return ret;
	}
}
