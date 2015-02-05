package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] b = new int[n];
		for (int i = 0; i < n; ++i) b[i] = in.readInt();
		List<String> seq = new ArrayList<>();
		seq.add("0");
		for (int i = 0; i < n; ++i) {
			seq.add(minNext(seq.get(i), b[i]));
		}
		for (String s : seq) {
			if (!"0".equals(s)) {
				out.printLine(s);
			}
		}
    }

	private String minNext(String decNum, int digitsSum) {
		int len = Math.max(decNum.length(), (digitsSum + 8) / 9);
		if (len > decNum.length()) {
			char[] res = new char[len];
			while (len > 1) {
				--len;
				int digit = Math.min(9, Math.max(0, digitsSum - 1));
				digitsSum -= digit;
				res[len] = toChar(digit);
			}
			res[0] = toChar(digitsSum);
			return new String(res);
		}
		int decNumDigitsSum = 0;
		for (int i = 0; i < len; ++i) {
			int digit = decNum.charAt(i) - '0';
			decNumDigitsSum += digit;
		}
		for (int i = len - 1; i >= 0; --i) {
			int digit = decNum.charAt(i) - '0';
			decNumDigitsSum -= digit;
			int remSum = digitsSum - decNumDigitsSum;
			if (remSum < digit) continue;
			for (int cur = digit + 1; cur < 10; ++cur) {
				if (cur > remSum) break;
				if (remSum - cur > 9 * (len - i - 1)) continue;
				char[] res = new char[len];
				for (int j = 0; j < i; ++j) res[j] = decNum.charAt(j);
				res[i] = toChar(cur);
				remSum -= cur;
				for (int j = len - 1; j > i; --j) {
					int dig = Math.min(9, Math.max(0, remSum));
					res[j] = toChar(dig);
					remSum -= dig;
				}
				return new String(res);
			}
		}
		++len;
		char[] res = new char[len];
		while (len > 1) {
			--len;
			int digit = Math.min(9, Math.max(0, digitsSum - 1));
			digitsSum -= digit;
			res[len] = toChar(digit);
		}
		res[0] = toChar(digitsSum);
		return new String(res);
	}

	private char toChar(int n) {
		return Character.forDigit(n, 10);
	}
}
