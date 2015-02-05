package net.spak;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] song = in.readString().toCharArray();
		double[] harm = new double[song.length + 1];
		harm[0] = 0.0;
		for (int i = 1; i < harm.length; ++i) {
			harm[i] = harm[i - 1] + 1.0 / i;
		}
		double ans = 0;
		double weight = 0;
		for (int i = 0; i < song.length; ++i) {
			weight += harm[song.length - i];
			weight -= harm[i];
			if (isVowel(song[i])) {
				ans += weight;
			}
		}
		out.printLine(ans);
    }

	private boolean isVowel(char c) {
		final char[] vowels = "AEIOUY".toCharArray();
		for (char cand : vowels) {
			if (cand == c) return true;
		}
		return false;
	}
}
